package io.kenxue.devops.coreclient.dto.project.node;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
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
public class NodeInfoDTO extends CommonDTO {
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
    // 重写compareTo方法，按照创建时间倒序排列
}
