package io.kenxue.devops.domain.repository.project;

import java.util.List;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.ProjectInfoListQry;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoPageQry;
import io.kenxue.devops.domain.domain.project.ProjectInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
public interface ProjectInfoRepository {
    void create(ProjectInfo projectInfo);
    void update(ProjectInfo projectInfo);
    ProjectInfo getById(Long id);
    List<ProjectInfo> list(ProjectInfoListQry projectInfoListQry);
    Page<ProjectInfo> page(ProjectInfoPageQry qry);
//    void delete(ProjectInfoDeleteCmd projectInfo);

}
