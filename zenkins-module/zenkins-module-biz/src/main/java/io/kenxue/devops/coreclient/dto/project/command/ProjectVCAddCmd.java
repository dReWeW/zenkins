package io.kenxue.devops.coreclient.dto.project.command;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectVersionCommitDictDTO;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/13/2023
 */
@Data
public class ProjectVCAddCmd extends CommonCommand {
    private ProjectVersionCommitDictDTO projectVCDTO;
}
