package io.kenxue.devops.application.jenkins.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gitee.api.model.repo.TagResponse;
import com.gitee.api.util.DockerRepoApiHelper;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.client.JenkinsHttpClient;
import com.offbytwo.jenkins.model.*;

import io.kenxue.devops.application.jenkins.util.JenkinsConnect;
import io.kenxue.devops.application.project.enums.JenkinsParams;
import io.kenxue.devops.application.project.enums.NodeEnum;
import io.kenxue.devops.application.project.log.JenkinsLogListener;
import io.kenxue.devops.application.redis.JenkinsRedisDAO;
import io.kenxue.devops.domain.domain.project.NodeInfo;
import io.kenxue.devops.domain.domain.project.ProjectFeature;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.domain.repository.project.NodeInfoRepository;
import io.kenxue.devops.domain.repository.project.ProjectFeatureRepository;
import io.kenxue.devops.service.JenkinsApiService;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/1/2023
 */

@Service
@Slf4j
public class JenkinsApiServiceImpl implements JenkinsApiService, InitializingBean {
    private JenkinsServer jenkinsServer;
    // http 客户端对象
    private JenkinsHttpClient jenkinsHttpClient;
    private static Map<String, JenkinsLogListener> jenkinsLogListenerMap = new ConcurrentHashMap<>();
    private static final String LISTENER_MAP_KEY_FORMAT = "%s-%s";
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Resource
    private JenkinsRedisDAO jenkinsRedisDAO;
    @Resource
    private NodeInfoRepository nodeInfoRepository;
    @Resource
    private ProjectFeatureRepository projectFeatureRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        jenkinsServer = JenkinsConnect.connection();
        jenkinsHttpClient = JenkinsConnect.getClient();
    }

    @Override
    public List<String> rollbackVersions(ProjectInfo projectInfo) {
        return Optional.ofNullable(DockerRepoApiHelper.getTags("versions", projectInfo.getCode()))
            .map(TagResponse::getTags).orElse(Lists.newArrayList());
    }

    /**
     * 执行带参数 Job build
     */
    @Async
    public void buildParamJob(String job, Map<String, String> param, Long nodeId) {
        try {
            // 执行 build 任务
            QueueReference queueReference = jenkinsServer.getJob(job).build(param, true);
            // 获取QueueItem直到build开始
            QueueItem queueItem = jenkinsServer.getQueueItem(queueReference);
            while (queueItem.getExecutable() == null) {
                Thread.sleep(500);
                queueItem = jenkinsServer.getQueueItem(queueReference);
            }
            // 更新build号
            NodeInfo nodeInfo = nodeInfoRepository.getById(nodeId);
            nodeInfo.setBuildNumber(queueItem.getExecutable().getNumber());
            nodeInfoRepository.update(nodeInfo);
            // 添加listener
            JenkinsLogListener jenkinsLogListener = new JenkinsLogListener(nodeId);
            // 1秒拉一次 十分钟超时
            // 添加build监听器
            QueueItem finalQueueItem = queueItem;
            executorService.execute(() -> {
                try {
                    jenkinsServer.getBuild(finalQueueItem).details().streamConsoleOutput(jenkinsLogListener, 1, 600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            jenkinsLogListenerMap.put(String.format(LISTENER_MAP_KEY_FORMAT,
                job,
                queueItem.getExecutable().getNumber()), jenkinsLogListener);
            // @deprecated 不再通过redis更新工作状态
            //            JenkinsJobItem jenkinsJobItem = new JenkinsJobItem(nodeId, queueItem);
            //            jenkinsRedisDAO.addJob(JSON.toJSONString(jenkinsJobItem));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateJobStatus() {
        List<String> jobList = jenkinsRedisDAO.getJobList();
        jobList.forEach(job -> {
            JSONObject jenkinsJobItem = JSON.parseObject(job);
            Long nodeId = jenkinsJobItem.getLong("nodeId");
            updateJobStatus(nodeId);
            jenkinsRedisDAO.removeJob(job);
        });
    }

    @Override
    public void updateJobStatus(Long nodeId) {
        NodeInfo nodeInfo = nodeInfoRepository.getById(nodeId);
        assert nodeInfo != null;
        String listenerKey = String.format(LISTENER_MAP_KEY_FORMAT, nodeInfo.getJobName(), nodeInfo.getBuildNumber());
        if (jenkinsLogListenerMap.containsKey(listenerKey)) {
            JenkinsLogListener jenkinsLogListener = jenkinsLogListenerMap.get(listenerKey);
            if (jenkinsLogListener.isFinished()) {
                jenkinsLogListenerMap.remove(listenerKey);
            }
        }
        try {
            Build build =
                jenkinsServer.getJob(nodeInfo.getJobName()).getBuildByNumber(nodeInfo.getBuildNumber().intValue());
            BuildResult result = build.details().getResult();
            String optionType = build.details().getParameters().get(JenkinsParams.SELECT_OPTION_TYPE.getName());
            String status = NodeEnum.StatusEnum.convert(result, optionType).name();
            nodeInfoRepository.updateNodeStatus(nodeId, status);
            // 更新feature统计状态
            updateFeature(status, nodeInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateFeature(String status, NodeInfo nodeInfo) {
        ProjectFeature projectFeature = projectFeatureRepository.getById(nodeInfo.getFeatureId());
        assert projectFeature != null;
        if (nodeInfo.getEnv().equals("BUILD")) {
            projectFeature.setBuildCount(projectFeature.getBuildCount() + 1);
            projectFeature.setLastBuild(new Date());
        }
        if (nodeInfo.getEnv().equals("QA")) {
            projectFeature.setQaCount(projectFeature.getQaCount() + 1);
            projectFeature.setLastQaStatus(status);
        }
        if (nodeInfo.getEnv().equals("PROD")) {
            projectFeature.setPublishCount(projectFeature.getPublishCount() + 1);
            projectFeature.setLastPublishStatus(status);
        }
        projectFeatureRepository.update(projectFeature);
    }

    @Override
    public JenkinsLogListener getListener(NodeInfo nodeInfo) {
        return jenkinsLogListenerMap.get(String.format(LISTENER_MAP_KEY_FORMAT,
            nodeInfo.getJobName(),
            nodeInfo.getBuildNumber()));
    }

    @Override
    public String getLog(NodeInfo nodeInfo) {
        try {
            Build build =
                jenkinsServer.getJob(nodeInfo.getJobName()).getBuildByNumber(nodeInfo.getBuildNumber().intValue());
            return build.details().getConsoleOutputText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
