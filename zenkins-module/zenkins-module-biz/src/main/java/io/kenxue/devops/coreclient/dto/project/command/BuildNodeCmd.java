package io.kenxue.devops.coreclient.dto.project.command;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@Data
public class BuildNodeCmd extends CommonCommand {
    private Long projectId;
    private String branch;
    private String commit;
    private Long featureId;
}
