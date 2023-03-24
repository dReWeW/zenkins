package io.kenxue.devops.domain.repository.project;

import java.util.List;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureListQry;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeaturePageQry;
import io.kenxue.devops.domain.domain.project.ProjectFeature;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
public interface ProjectFeatureRepository {
    void create(ProjectFeature projectFeature);
    void update(ProjectFeature projectFeature);
    ProjectFeature getById(Long id);
    List<ProjectFeature> list(ProjectFeatureListQry projectId);
    Page<ProjectFeature> page(ProjectFeaturePageQry projectId);
}
