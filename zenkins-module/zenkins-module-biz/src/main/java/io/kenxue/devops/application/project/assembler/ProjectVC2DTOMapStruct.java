package io.kenxue.devops.application.project.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectVersionCommitDictDTO;
import io.kenxue.devops.domain.domain.project.ProjectVersionCommitDict;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/13/2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectVC2DTOMapStruct {
    ProjectVC2DTOMapStruct INSTANCE= Mappers.getMapper(ProjectVC2DTOMapStruct.class);

    @Mappings({})
    ProjectVersionCommitDictDTO toDTO(ProjectVersionCommitDict projectInfo);
    @Mappings({})
    ProjectVersionCommitDict toDomain(ProjectVersionCommitDictDTO projectInfoDTO);
}
