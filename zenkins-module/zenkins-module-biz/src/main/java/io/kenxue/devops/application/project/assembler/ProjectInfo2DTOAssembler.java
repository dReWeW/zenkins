package io.kenxue.devops.application.project.assembler;

import java.util.List;
import org.springframework.stereotype.Component;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.devops.domain.domain.project.ProjectInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Component
public class ProjectInfo2DTOAssembler implements Assembler<ProjectInfoDTO, ProjectInfo> {

    @Override
    public ProjectInfoDTO toDTO(ProjectInfo dO) {
        return ProjectInfo2DTOMapStruct.INSTANCE.toDTO(dO);
    }

    @Override
    public ProjectInfo toDomain(ProjectInfoDTO dTO) {
        return ProjectInfo2DTOMapStruct.INSTANCE.toDomain(dTO);
    }

    @Override
    public List<ProjectInfoDTO> toDTOList(List<ProjectInfo> projectInfos) {
        return ProjectInfo2DTOMapStruct.INSTANCE.toDTOList(projectInfos);
    }

    @Override
    public List<ProjectInfo> toDomainList(List<ProjectInfoDTO> dTOList) {
        return ProjectInfo2DTOMapStruct.INSTANCE.toDomainList(dTOList);
    }

    @Override
    public Page<ProjectInfoDTO> toDTOPage(Page<ProjectInfo> page) {
        return new Page<>(page.getCurrent(),
            page.getSize(),
            page.getTotal(),
            ProjectInfo2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
