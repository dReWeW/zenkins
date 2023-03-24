package io.kenxue.devops.service;

import java.util.List;
import java.util.Map;

import io.kenxue.devops.application.project.log.JenkinsLogListener;
import io.kenxue.devops.domain.domain.project.NodeInfo;
import io.kenxue.devops.domain.domain.project.ProjectInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/1/2023
 */
public interface JenkinsApiService {
    List<String> rollbackVersions(ProjectInfo projectInfo);
    void buildParamJob(String job, Map<String,String> param, Long nodeId);

    void updateJobStatus();

    void updateJobStatus(Long nodeId);

    JenkinsLogListener getListener(NodeInfo nodeInfo);
    String getLog(NodeInfo nodeInfo);
}
