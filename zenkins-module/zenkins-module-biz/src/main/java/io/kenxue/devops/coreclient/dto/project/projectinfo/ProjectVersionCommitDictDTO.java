package io.kenxue.devops.coreclient.dto.project.projectinfo;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/12/2023
 */
@Data
public class ProjectVersionCommitDictDTO extends CommonDTO {
    private String version;
    private String commit;
    private Long projectId;
}
