package io.kenxue.devops.coreclient.dto.project.command;

import javax.validation.constraints.NotNull;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/12/2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeployNodeCmd extends CommonCommand {
    @NotNull
    private Long projectId;
    @NotNull
    private Long buildId;
    @NotNull
    private String allocation;
    @NotNull
    private Boolean stable;
    @NotNull
    private String env;
    private String version;
}
