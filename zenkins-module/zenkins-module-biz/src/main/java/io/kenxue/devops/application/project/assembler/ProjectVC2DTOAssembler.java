package io.kenxue.devops.application.project.assembler;

import java.util.List;
import org.springframework.stereotype.Component;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectVersionCommitDictDTO;
import io.kenxue.devops.domain.domain.project.ProjectVersionCommitDict;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/13/2023
 */
@Component
public class ProjectVC2DTOAssembler implements Assembler<ProjectVersionCommitDictDTO, ProjectVersionCommitDict> {

    @Override
    public ProjectVersionCommitDictDTO toDTO(ProjectVersionCommitDict dO) {
        return ProjectVC2DTOMapStruct.INSTANCE.toDTO(dO);
    }

    @Override
    public ProjectVersionCommitDict toDomain(ProjectVersionCommitDictDTO dTO) {
        return ProjectVC2DTOMapStruct.INSTANCE.toDomain(dTO);
    }

    @Override
    public List<ProjectVersionCommitDictDTO> toDTOList(List<ProjectVersionCommitDict> projectVersionCommitDicts) {
        return null;
    }

    @Override
    public List<ProjectVersionCommitDict> toDomainList(List<ProjectVersionCommitDictDTO> dTOList) {
        return null;
    }

    @Override
    public Page<ProjectVersionCommitDictDTO> toDTOPage(Page<ProjectVersionCommitDict> page) {
        return null;
    }
}
