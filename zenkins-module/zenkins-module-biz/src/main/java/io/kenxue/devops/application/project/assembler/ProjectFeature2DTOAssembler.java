package io.kenxue.devops.application.project.assembler;

import java.util.List;

import org.springframework.stereotype.Component;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureDTO;
import io.kenxue.devops.domain.domain.project.ProjectFeature;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
@Component
public class ProjectFeature2DTOAssembler implements Assembler<ProjectFeatureDTO, ProjectFeature> {

    @Override
    public ProjectFeatureDTO toDTO(ProjectFeature dO) {
        return ProjectFeature2DTOMapStruct.INSTANCE.toDTO(dO);
    }

    @Override
    public ProjectFeature toDomain(ProjectFeatureDTO dTO) {
        return ProjectFeature2DTOMapStruct.INSTANCE.toDomain(dTO);
    }

    @Override
    public List<ProjectFeatureDTO> toDTOList(List<ProjectFeature> projectFeatures) {
        return ProjectFeature2DTOMapStruct.INSTANCE.toDTOList(projectFeatures);
    }

    @Override
    public List<ProjectFeature> toDomainList(List<ProjectFeatureDTO> dTOList) {
        return ProjectFeature2DTOMapStruct.INSTANCE.toDomainList(dTOList);
    }

    @Override
    public Page<ProjectFeatureDTO> toDTOPage(Page<ProjectFeature> page) {
        return new Page<>(page.getCurrent(),
            page.getSize(),
            page.getTotal(),
            ProjectFeature2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
