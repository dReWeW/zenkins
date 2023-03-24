package io.kenxue.devops.application.project.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import io.kenxue.devops.application.project.assembler.ProjectFeature2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureDTO;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureListQry;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeaturePageQry;
import io.kenxue.devops.domain.domain.project.ProjectFeature;
import io.kenxue.devops.domain.repository.project.ProjectFeatureRepository;
import io.kenxue.devops.service.ProjectFeatureService;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
@Service
public class ProjectFeatureServiceImpl implements ProjectFeatureService {
    @Resource
    private ProjectFeatureRepository projectFeatureRepository;
    @Resource
    private ProjectFeature2DTOAssembler projectFeature2DTOAssembler;

    @Override
    public SingleResponse<ProjectFeatureDTO> getById(Long id) {
        return SingleResponse.of(projectFeature2DTOAssembler.toDTO(projectFeatureRepository.getById(id)));
    }

    @Override
    public Response create(ProjectFeatureDTO projectFeatureDTO) {
        ProjectFeature projectFeature = projectFeature2DTOAssembler.toDomain(projectFeatureDTO);
        projectFeature.create();
        projectFeatureRepository.create(projectFeature);
        return Response.success();
    }

    @Override
    public MultiResponse<ProjectFeatureDTO> list(ProjectFeatureListQry qry) {
        List<ProjectFeatureDTO> projectInfoDTOList =
            projectFeature2DTOAssembler.toDTOList(projectFeatureRepository.list(qry));
        projectInfoDTOList.sort((o1, o2) -> o2.getGmtCreate().compareTo(o1.getGmtCreate()));
        return MultiResponse.of(projectInfoDTOList);
    }

    @Override
    public PageResponse<ProjectFeatureDTO> page(ProjectFeaturePageQry qry) {
        Page<ProjectFeatureDTO> projectInfoDTOPage =
            projectFeature2DTOAssembler.toDTOPage(projectFeatureRepository.page(qry));
        return PageResponse.of(projectInfoDTOPage);
    }

    @Override
    public void update(ProjectFeatureDTO projectFeatureDTO) {
        projectFeatureRepository.update(projectFeature2DTOAssembler.toDomain(projectFeatureDTO));
    }
}
