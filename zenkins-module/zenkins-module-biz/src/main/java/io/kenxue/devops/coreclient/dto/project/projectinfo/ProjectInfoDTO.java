package io.kenxue.devops.coreclient.dto.project.projectinfo;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Data
public class ProjectInfoDTO extends CommonDTO {
    private String name;
    private String code;
    private String functionary;
    private Boolean status;
    private String platform;
    private String groupId;
    private String remark;
    private String repoUrl;
    private String repoOwner;
}
