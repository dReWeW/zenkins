package io.kenxue.devops.application.project.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.google.common.base.Enums;
import com.google.common.collect.Maps;

import io.kenxue.devops.application.project.assembler.NodeInfo2DTOAssembler;
import io.kenxue.devops.application.project.enums.ClusterServerEnus;
import io.kenxue.devops.application.project.enums.JenkinsParams;
import io.kenxue.devops.application.project.enums.NodeEnum;
import io.kenxue.devops.application.project.nginx.NginxHelper;
import io.kenxue.devops.application.redis.JenkinsRedisDAO;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.NodeGroupDTO;
import io.kenxue.devops.coreclient.dto.project.NodeInfoListQry;
import io.kenxue.devops.coreclient.dto.project.RollbackVersionsQry;
import io.kenxue.devops.coreclient.dto.project.command.*;
import io.kenxue.devops.coreclient.dto.project.node.NodeInfoDTO;
import io.kenxue.devops.domain.domain.project.NodeInfo;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.domain.domain.project.ProjectVersionCommitDict;
import io.kenxue.devops.domain.repository.project.NodeInfoRepository;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import io.kenxue.devops.domain.repository.project.ProjectVCRepository;
import io.kenxue.devops.service.JenkinsApiService;
import io.kenxue.devops.service.NodeInfoService;
import io.kenxue.devops.service.ProjectInfoAppService;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@Service
@Slf4j
public class NodeInfoServiceImpl implements NodeInfoService {
    @Resource
    private ProjectInfoRepository projectInfoRepository;
    @Resource
    private NodeInfoRepository nodeInfoRepository;
    @Resource
    private NodeInfo2DTOAssembler nodeInfo2DTOAssembler;
    @Resource
    private JenkinsApiService jenkinsApiService;
    @Resource
    private JenkinsRedisDAO jenkinsRedisDAO;
    @Resource
    private ProjectVCRepository projectVCRepository;
    @Resource
    private ProjectInfoAppService projectInfoAppService;

    private static String jobName = "test_flow_fastwave";

    @Override
    public MultiResponse<NodeGroupDTO> queryNodeInfoList(NodeInfoListQry qry) {
        List<NodeInfo> nodeInfos = nodeInfoRepository.list(qry);
        return MultiResponse.of(nodeInfo2DTOAssembler.convertNodeInfoToNodeGroup(nodeInfo2DTOAssembler.toDTOList(
            nodeInfos)));
    }

    @Override
    public Response listRollbackVersion(RollbackVersionsQry qry) {
        ProjectInfo projectInfo = projectInfoRepository.getById(qry.getProjectId());
        if (Objects.isNull(projectInfo)) {
            return MultiResponse.error("project is not exist");
        }
        return MultiResponse.of(jenkinsApiService.rollbackVersions(projectInfo));
    }

    @Override
    public Response build(BuildNodeCmd cmd) {
        // todo
        ProjectInfo projectInfo = projectInfoRepository.getById(cmd.getProjectId());
        if (Objects.isNull(projectInfo)) {
            return Response.error("project is not exist");
        }
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.create();
        nodeInfo.setJobName(jobName);
        nodeInfo.setStatus(NodeEnum.StatusEnum.PROCESSING.name());
        nodeInfo.setProjectId(cmd.getProjectId());
        nodeInfo.setBranch(cmd.getBranch());
        nodeInfo.setCommit(cmd.getCommit());
        nodeInfo.setEnv(NodeEnum.BUILD.name());
        nodeInfo.setProjectId(cmd.getProjectId());
        nodeInfo.setCommitMessage(projectInfoAppService.queryCommitMessage(projectInfo.getRepoOwner(),
            projectInfo.getCode(),
            cmd.getBranch()));
        nodeInfo.setFeatureId(cmd.getFeatureId());
        Map<String, String> param = prepareBuildParam(cmd, projectInfo);
        nodeInfoRepository.create(nodeInfo);
        jenkinsApiService.buildParamJob(jobName, param, nodeInfo.getId());
        return Response.success();
    }

    @Override
    public Response deploy(DeployNodeCmd cmd) {
        ProjectInfo projectInfo = projectInfoRepository.getById(cmd.getProjectId());
        NodeInfo nodeInfo = nodeInfoRepository.getById(cmd.getBuildId());
        if (Objects.isNull(projectInfo)) {
            return Response.error("project is not exist");
        }
        if (Objects.isNull(nodeInfo)) {
            return Response.error("build node is not exist");
        }
        NodeInfo newNodeInfo = new NodeInfo(nodeInfo);
        newNodeInfo.setStatus(NodeEnum.StatusEnum.PROCESSING.name());
        Map<String, String> param = prepareDeployParam(cmd, projectInfo, newNodeInfo);
        newNodeInfo.create();
        nodeInfoRepository.create(newNodeInfo);
        jenkinsApiService.buildParamJob(jobName, param, newNodeInfo.getId());
        // 增加版本号
        if (cmd.getEnv().equals(NodeEnum.PROD.name())) {
            ProjectVersionCommitDict projectVersionCommitDict = new ProjectVersionCommitDict();
            projectVersionCommitDict.setProjectId(cmd.getProjectId());
            projectVersionCommitDict.setVersion(cmd.getVersion());
            projectVersionCommitDict.setCommit(newNodeInfo.getCommit());
            projectVersionCommitDict.create();
            projectVCRepository.create(projectVersionCommitDict);
        }
        return Response.success();
    }

    @Override
    public Response recycle(NodeInfoRecycleCmd cmd) {
        // todo
        ProjectInfo projectInfo = projectInfoRepository.getById(cmd.getProjectId());
        NodeInfo nodeInfo = nodeInfoRepository.getById(cmd.getNodeId());
        if (Objects.isNull(projectInfo)) {
            return Response.error("project is not exist");
        }
        if (Objects.isNull(nodeInfo)) {
            return Response.error("build node is not exist");
        }
        nodeInfo.setStatus(NodeEnum.StatusEnum.PROCESSING.name());
        Map<String, String> param = prepareRecycleParam(cmd, nodeInfo, projectInfo);
        nodeInfoRepository.update(nodeInfo);
        // 拷贝nodeinfo
        jenkinsApiService.buildParamJob(jobName, param, nodeInfo.getId());
        return Response.success();
    }

    @Override
    public Response expansion(NodeInfoExpansionCmd cmd) {
        ProjectInfo projectInfo = projectInfoRepository.getById(cmd.getProjectId());
        NodeInfo nodeInfo = nodeInfoRepository.getById(cmd.getNodeId());
        if (Objects.isNull(projectInfo)) {
            return Response.error("project is not exist");
        }
        if (Objects.isNull(nodeInfo)) {
            return Response.error("build node is not exist");
        }
        nodeInfo.setStatus(NodeEnum.StatusEnum.PROCESSING.name());
        Map<String, String> param = prepareExpansionParam(cmd, nodeInfo, projectInfo);
        nodeInfoRepository.update(nodeInfo);
        jenkinsApiService.buildParamJob(jobName, param, nodeInfo.getId());
        return Response.success();
    }

    @Override
    public SingleResponse<NodeInfoDTO> queryNodeInfoDetail(NodeInfoGetQry query) {
        return SingleResponse.of(nodeInfo2DTOAssembler.toDTO(nodeInfoRepository.getById(query.getNodeId())));
    }

    @Override
    public Response rollback(NodeInfoRollbackCmd cmd) {
        ProjectInfo projectInfo = projectInfoRepository.getById(cmd.getProjectId());
        NodeInfo nodeInfo = nodeInfoRepository.getById(cmd.getNodeId());
        if (Objects.isNull(projectInfo)) {
            return Response.error("project is not exist");
        }
        if (Objects.isNull(nodeInfo)) {
            return Response.error("build node is not exist");
        }
        nodeInfo.setStatus(NodeEnum.StatusEnum.PROCESSING.name());
        Map<String, String> param = prepareRollbackParam(cmd, nodeInfo, projectInfo);
        nodeInfoRepository.update(nodeInfo);
        jenkinsApiService.buildParamJob(jobName, param, nodeInfo.getId());
        return Response.success();
    }

    private Map<String, String> prepareExpansionParam(NodeInfoExpansionCmd cmd, NodeInfo nodeInfo,
        ProjectInfo projectInfo) {
        Map<String, String> param = Maps.newHashMap();
        // 部署节点配置，默认为单节点部署
        ClusterServerEnus oldAllocation =
            Enums.getIfPresent(ClusterServerEnus.class, nodeInfo.getDeployServers()).or(ClusterServerEnus.SINGLE);
        ClusterServerEnus newAllocation =
            Enums.getIfPresent(ClusterServerEnus.class, cmd.getAllocation()).or(ClusterServerEnus.SINGLE);
        String[] finalServers = newAllocation.getAllocation().split(",");
        String port = String.valueOf(generatePort());

        String nginxServerName = nodeInfo.getNginxServername();
        String nginxConfScript = NginxHelper.getNginxConfScript(finalServers, port, nginxServerName);
        param.put(JenkinsParams.SELECT_OPTION_TYPE.getName(), JenkinsParams.JenkinsStage.EXPANSION.getName());
        param.put(JenkinsParams.DEL_SERVERS.getName(), oldAllocation.getAllocation());
        param.put(JenkinsParams.ADD_SERVERS.getName(), newAllocation.getAllocation());
        param.put(JenkinsParams.PROJECT_NAME.getName(), projectInfo.getCode());
        param.put(JenkinsParams.COMMIT_TAG.getName(), nodeInfo.getCommit());
        param.put(JenkinsParams.PORT.getName(), port);
        param.put(JenkinsParams.CONTAINER_NAME.getName(), nodeInfo.getContainerName());
        param.put(JenkinsParams.NGINX_CONF_FILE.getName(), nodeInfo.getNginxConfFile());
        param.put(JenkinsParams.NGINX_CONF_SCRIPT.getName(), nginxConfScript);

        nodeInfo.setDeployServers(newAllocation.name());
        return param;
    }

    private Map<String, String> prepareRecycleParam(NodeInfoRecycleCmd cmd, NodeInfo nodeInfo,
        ProjectInfo projectInfo) {
        Map<String, String> param = Maps.newHashMap();
        ClusterServerEnus allocation =
            Enums.getIfPresent(ClusterServerEnus.class, nodeInfo.getDeployServers()).or(ClusterServerEnus.SINGLE);
        param.put(JenkinsParams.SELECT_OPTION_TYPE.getName(), JenkinsParams.JenkinsStage.RECYCLE.getName());
        param.put(JenkinsParams.SELECT_SSH_NAME.getName(), allocation.getAllocation());
        param.put(JenkinsParams.PROJECT_NAME.getName(), projectInfo.getCode());
        param.put(JenkinsParams.CONTAINER_NAME.getName(), nodeInfo.getContainerName());
        param.put(JenkinsParams.NGINX_RECYCLE_CONF_FILE.getName(), nodeInfo.getNginxConfFile());

        return param;

    }

    private Map<String, String> prepareRollbackParam(NodeInfoRollbackCmd cmd, NodeInfo nodeInfo,
        ProjectInfo projectInfo) {
        Map<String, String> param = Maps.newHashMap();
        String port = String.valueOf(generatePort());
        ClusterServerEnus allocation =
            Enums.getIfPresent(ClusterServerEnus.class, nodeInfo.getDeployServers()).or(ClusterServerEnus.SINGLE);
        String[] servers = allocation.getIp().split(",");
        String commitTag =
            projectVCRepository.getByProjectIdAndVersion(projectInfo.getId(), cmd.getVersion()).getCommit();
        String nginxConfFile = nodeInfo.getNginxConfFile();
        String nginxServerName = nodeInfo.getNginxServername();
        String nginxConfScript = NginxHelper.getNginxConfScript(servers, port, nginxServerName);
        param.put(JenkinsParams.SELECT_OPTION_TYPE.getName(), JenkinsParams.JenkinsStage.ROLLBACK.getName());
        param.put(JenkinsParams.SELECT_SSH_NAME.getName(), allocation.getAllocation());
        param.put(JenkinsParams.PROJECT_NAME.getName(), projectInfo.getCode());
        param.put(JenkinsParams.CONTAINER_NAME.getName(), nodeInfo.getContainerName());
        param.put(JenkinsParams.ROLLBACK_OLD_TAG.getName(), nodeInfo.getCommit());
        param.put(JenkinsParams.ROLLBACK_NEW_TAG.getName(), commitTag);
        param.put(JenkinsParams.PORT.getName(), port);
        param.put(JenkinsParams.NGINX_RECYCLE_CONF_FILE.getName(), nodeInfo.getNginxConfFile());
        param.put(JenkinsParams.NGINX_CONF_FILE.getName(), nginxConfFile);
        param.put(JenkinsParams.NGINX_CONF_SCRIPT.getName(), nginxConfScript);

        nodeInfo.setNginxConfFile(nginxConfFile);
        nodeInfo.setNginxServername(nginxServerName);

        return param;
    }

    private Map<String, String> prepareDeployParam(DeployNodeCmd cmd, ProjectInfo projectInfo, NodeInfo nodeInfo) {
        Map<String, String> param = Maps.newHashMap();
        // 部署节点配置，默认为单节点部署
        String port = String.valueOf(generatePort());
        ClusterServerEnus allocation =
            Enums.getIfPresent(ClusterServerEnus.class, cmd.getAllocation()).or(ClusterServerEnus.SINGLE);
        String[] servers = allocation.getIp().split(",");
        // nginx配置
        String nginxPrefix = NginxHelper.getUniqueNginxPrefix(projectInfo.getCode());
        String nginxConfFile = NginxHelper.getNginxFileName(nginxPrefix, cmd.getEnv());
        String nginxServerName = NginxHelper.getNginxServerName(nginxPrefix, cmd.getEnv());
        String nginxConfScript = NginxHelper.getNginxConfScript(servers, port, nginxServerName);

        String containerName = nginxPrefix + "-" + cmd.getEnv();

        param.put(JenkinsParams.SELECT_OPTION_TYPE.getName(), JenkinsParams.JenkinsStage.DEPLOY.getName());
        param.put(JenkinsParams.SELECT_SSH_NAME.getName(), allocation.getAllocation());
        param.put(JenkinsParams.PROJECT_NAME.getName(), projectInfo.getCode());
        param.put(JenkinsParams.COMMIT_TAG.getName(), nodeInfo.getCommit());
        param.put(JenkinsParams.CONTAINER_NAME.getName(), containerName);
        param.put(JenkinsParams.PORT.getName(), port);
        param.put(JenkinsParams.NGINX_CONF_FILE.getName(), nginxConfFile);
        param.put(JenkinsParams.NGINX_CONF_SCRIPT.getName(), nginxConfScript);
        if (cmd.getEnv().equals("PROD")) {
            param.put(JenkinsParams.VERSION_TAG.getName(), cmd.getVersion());
            param.put(JenkinsParams.PROD.getName(), "true");
        }

        nodeInfo.setNginxConfFile(nginxConfFile);
        nodeInfo.setNginxServername(nginxServerName);
        nodeInfo.setDeployServers(allocation.name());
        nodeInfo.setContainerName(containerName);
        nodeInfo.setEnv(cmd.getEnv());
        nodeInfo.setBuildId(cmd.getBuildId().intValue());

        return param;
    }

    private Map<String, String> prepareBuildParam(BuildNodeCmd cmd, ProjectInfo projectInfo) {
        Map<String, String> param = Maps.newHashMap();
        // 部署节点配置，默认为单节点部署
        param.put(JenkinsParams.SELECT_OPTION_TYPE.getName(), JenkinsParams.JenkinsStage.BUILD.getName());
        param.put(JenkinsParams.PROJECT_NAME.getName(), projectInfo.getCode());
        param.put(JenkinsParams.COMMIT_TAG.getName(), cmd.getCommit());
        param.put(JenkinsParams.BRANCH.getName(), cmd.getBranch());
        param.put(JenkinsParams.PROJECT_REPOSITORY_URL.getName(), projectInfo.getRepoUrl());

        return param;
    }

    public Integer generatePort() {
        Integer port;
        do {
            port = jenkinsRedisDAO.getAllocatePort();
            jenkinsRedisDAO.increaseAllocatePort();
        } while (jenkinsRedisDAO.hasPort(port));
        // 设置占用
        jenkinsRedisDAO.addPort(port);
        return port;
    }
}
