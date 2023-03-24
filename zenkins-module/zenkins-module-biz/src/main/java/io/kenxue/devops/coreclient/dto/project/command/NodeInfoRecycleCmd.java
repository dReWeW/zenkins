package io.kenxue.devops.coreclient.dto.project.command;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeInfoRecycleCmd extends CommonCommand {
    Long projectId;
    Long nodeId;
}
