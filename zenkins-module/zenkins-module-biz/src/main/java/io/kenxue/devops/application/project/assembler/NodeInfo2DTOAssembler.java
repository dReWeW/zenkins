package io.kenxue.devops.application.project.assembler;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.NodeGroupDTO;
import io.kenxue.devops.coreclient.dto.project.node.NodeInfoDTO;
import io.kenxue.devops.domain.domain.project.NodeInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@Component
public class NodeInfo2DTOAssembler implements Assembler<NodeInfoDTO, NodeInfo> {
    @Override
    public NodeInfoDTO toDTO(NodeInfo dO) {
        return NodeInfo2DTOMapStruct.INSTANCE.toDTO(dO);
    }

    @Override
    public NodeInfo toDomain(NodeInfoDTO dTO) {
        return NodeInfo2DTOMapStruct.INSTANCE.toDomain(dTO);
    }

    @Override
    public List<NodeInfoDTO> toDTOList(List<NodeInfo> nodeInfos) {
        return NodeInfo2DTOMapStruct.INSTANCE.toDTOList(nodeInfos);
    }

    @Override
    public List<NodeInfo> toDomainList(List<NodeInfoDTO> dTOList) {
        return NodeInfo2DTOMapStruct.INSTANCE.toDomainList(dTOList);
    }

    @Override
    public Page<NodeInfoDTO> toDTOPage(Page<NodeInfo> page) {
        return new Page<>(page.getCurrent(),
            page.getSize(),
            page.getTotal(),
            NodeInfo2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }

    public List<NodeGroupDTO> convertNodeInfoToNodeGroup(List<NodeInfoDTO> nodeInfoList) {
        // 按照buildId分组
        Map<Integer, List<NodeInfoDTO>> buildIdGroupMap = nodeInfoList.stream()
            .collect(Collectors.groupingBy(nodeInfoDTO -> Optional.ofNullable(nodeInfoDTO.getBuildId())
                .orElse(nodeInfoDTO.getId().intValue())));
        // 将每个分组内的NodeInfoDTO再次按照env分组，并转换为NodeGroupDTO
        List<NodeGroupDTO> nodeGroupDTOS = buildIdGroupMap.values().stream().map(nodeInfoDTOS -> {
            NodeGroupDTO nodeGroup = new NodeGroupDTO();
            nodeInfoDTOS.forEach(nodeInfo -> {
                switch (nodeInfo.getEnv()) {
                    case "BUILD":
                        nodeGroup.getBuildNodes().add(nodeInfo);
                        break;
                    case "DEV":
                        nodeGroup.getDevNodes().add(nodeInfo);
                        break;
                    case "QA":
                        nodeGroup.getQaNodes().add(nodeInfo);
                        break;
                    case "PROD":
                        nodeGroup.getProdNodes().add(nodeInfo);
                        break;
                    default:
                        // 处理不属于四个环境的NodeInfoDTO
                        break;
                }
            });
            return nodeGroup;
        }).collect(Collectors.toList());
        // 遍历nodeGroupDTOS，将每个NodeGroupDTO内的buildNodes,devNodes,qaNodes,prodNodes按照创建时间倒序排列
        nodeGroupDTOS.forEach(nodeGroup -> {
            nodeGroup.getBuildNodes().sort(Comparator.comparing(CommonDTO::getGmtCreate));
            nodeGroup.getDevNodes().sort(Comparator.comparing(CommonDTO::getGmtCreate));
            nodeGroup.getQaNodes().sort(Comparator.comparing(CommonDTO::getGmtCreate));
            nodeGroup.getProdNodes().sort(Comparator.comparing(CommonDTO::getGmtCreate));
        });
        nodeGroupDTOS.sort((o1, o2) -> o2.getBuildNodes().get(0).getGmtCreate()
            .compareTo(o1.getBuildNodes().get(0).getGmtCreate()));
        return nodeGroupDTOS;

    }

}
