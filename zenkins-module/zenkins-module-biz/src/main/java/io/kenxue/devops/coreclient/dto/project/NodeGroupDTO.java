package io.kenxue.devops.coreclient.dto.project;

import java.util.ArrayList;
import java.util.List;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import io.kenxue.devops.coreclient.dto.project.node.NodeInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
@Data
@AllArgsConstructor
public class NodeGroupDTO extends CommonDTO {
    public NodeGroupDTO(){
        buildNodes = new ArrayList<>();
        devNodes = new ArrayList<>();
        qaNodes = new ArrayList<>();
        prodNodes = new ArrayList<>();
    }

    List<NodeInfoDTO> buildNodes;
    List<NodeInfoDTO> devNodes;
    List<NodeInfoDTO> qaNodes;
    List<NodeInfoDTO> prodNodes;
}

