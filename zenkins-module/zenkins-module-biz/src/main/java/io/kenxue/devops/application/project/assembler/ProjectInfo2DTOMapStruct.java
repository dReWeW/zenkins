package io.kenxue.devops.application.project.assembler;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.devops.domain.domain.project.ProjectInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectInfo2DTOMapStruct {
    ProjectInfo2DTOMapStruct INSTANCE = Mappers.getMapper(ProjectInfo2DTOMapStruct.class);

    //toDTO
    @Mappings({})
    ProjectInfoDTO toDTO(ProjectInfo projectInfo);

    //toDomain
    @Mappings({})
    ProjectInfo toDomain(ProjectInfoDTO projectInfoDTO);

    //toDTOList
    List<ProjectInfoDTO> toDTOList(List<ProjectInfo> projectInfoList);

    //toDomainList
    List<ProjectInfo> toDomainList(List<ProjectInfoDTO> projectInfoDTOList);

}
