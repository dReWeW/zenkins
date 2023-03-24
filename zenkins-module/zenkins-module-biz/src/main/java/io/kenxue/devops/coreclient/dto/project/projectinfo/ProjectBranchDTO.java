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
public class ProjectBranchDTO {
    private String name;
    private String branchUrl;
    private String commit;
}
