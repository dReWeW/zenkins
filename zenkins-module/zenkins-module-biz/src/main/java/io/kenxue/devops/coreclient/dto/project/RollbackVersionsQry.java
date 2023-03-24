package io.kenxue.devops.coreclient.dto.project;

import javax.validation.constraints.NotNull;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/11/2023
 */
@Data
public class RollbackVersionsQry extends CommonCommand {
    @NotNull
    private Long projectId;
}
