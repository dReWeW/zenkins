package io.kenxue.devops.service;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureDTO;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureListQry;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeaturePageQry;
import io.kenxue.devops.domain.domain.project.ProjectFeature;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
public interface ProjectFeatureService {
    SingleResponse<ProjectFeatureDTO> getById(Long id);
    Response create(ProjectFeatureDTO projectFeatureDTO);
    MultiResponse<ProjectFeatureDTO> list(ProjectFeatureListQry qry);
    PageResponse<ProjectFeatureDTO> page(ProjectFeaturePageQry qry);
    void update(ProjectFeatureDTO projectFeatureDTO);
}
