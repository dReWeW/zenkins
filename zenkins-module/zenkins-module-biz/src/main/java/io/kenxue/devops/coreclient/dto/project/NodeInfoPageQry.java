package io.kenxue.devops.coreclient.dto.project;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import io.kenxue.devops.coreclient.dto.project.node.NodeInfoDTO;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@Data
public class NodeInfoPageQry extends PageQuery {
    private NodeInfoDTO nodeInfoDTO;
}
