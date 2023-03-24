package io.kenxue.devops.coreclient.dto.project.command;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import io.kenxue.devops.coreclient.dto.project.node.NodeInfoDTO;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/11/2023
 */
@Data
public class NodeInfoRollbackCmd extends CommonCommand {
    private Long nodeId;
    private Long projectId;
    private String version;
}
