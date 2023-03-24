package io.kenxue.devops.application.project.assembler;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureDTO;
import io.kenxue.devops.domain.domain.project.ProjectFeature;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectFeature2DTOMapStruct {
    ProjectFeature2DTOMapStruct INSTANCE= Mappers.getMapper(ProjectFeature2DTOMapStruct.class);
    @Mappings({})
    ProjectFeatureDTO toDTO(ProjectFeature projectFeature);
    @Mappings({})
    ProjectFeature toDomain(ProjectFeatureDTO projectFeatureDTO);
    List<ProjectFeatureDTO> toDTOList(List<ProjectFeature> projectFeatureList);
    List<ProjectFeature> toDomainList(List<ProjectFeatureDTO> projectFeatureDTOList);
}
