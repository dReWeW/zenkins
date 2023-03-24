package io.kenxue.devops.domain.domain.project;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeInfo extends CommonEntity {
    private String jobName;
    private Integer buildId;
    private String status;
    private String commit;
    private String branch;
    private Long projectId;
    private Long featureId;
    private String deployServers;
    private String nginxServername;
    private String nginxConfFile;
    private String env;
    private String containerName;
    private Long buildNumber;
    private String commitMessage;

    public NodeInfo(NodeInfo nodeInfo){
        super();
        this.jobName = nodeInfo.getJobName();
        this.buildId = nodeInfo.getBuildId();
        this.status = nodeInfo.getStatus();
        this.commit = nodeInfo.getCommit();
        this.branch = nodeInfo.getBranch();
        this.projectId = nodeInfo.getProjectId();
        this.featureId = nodeInfo.getFeatureId();
        this.deployServers = nodeInfo.getDeployServers();
        this.nginxServername = nodeInfo.getNginxServername();
        this.nginxConfFile = nodeInfo.getNginxConfFile();
        this.env = nodeInfo.getEnv();
        this.containerName = nodeInfo.getContainerName();
        this.buildNumber = nodeInfo.getBuildNumber();
        this.commitMessage = nodeInfo.getCommitMessage();
    }
}
