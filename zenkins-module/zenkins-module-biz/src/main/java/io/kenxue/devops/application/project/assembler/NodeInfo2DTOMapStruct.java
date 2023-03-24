package io.kenxue.devops.application.project.assembler;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import io.kenxue.devops.coreclient.dto.project.node.NodeInfoDTO;
import io.kenxue.devops.domain.domain.project.NodeInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NodeInfo2DTOMapStruct {
    NodeInfo2DTOMapStruct INSTANCE = Mappers.getMapper(NodeInfo2DTOMapStruct.class);

    //toDTO
    @Mappings({})
    NodeInfoDTO toDTO(NodeInfo projectInfo);

    //toDomain
    @Mappings({})
    NodeInfo toDomain(NodeInfoDTO projectInfoDTO);

    //toDTOList
    List<NodeInfoDTO> toDTOList(List<NodeInfo> projectInfoList);

    //toDomainList
    List<NodeInfo> toDomainList(List<NodeInfoDTO> projectInfoDTOList);
}
