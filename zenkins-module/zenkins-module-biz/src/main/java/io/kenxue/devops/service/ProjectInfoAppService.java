package io.kenxue.devops.service;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.ProjectInfoGetQry;
import io.kenxue.devops.coreclient.dto.project.ProjectInfoListQry;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectBranchDTO;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.devops.domain.domain.project.ProjectInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
public interface ProjectInfoAppService {
    SingleResponse<ProjectInfo> getById(ProjectInfoGetQry query);
    MultiResponse<ProjectBranchDTO> branchList(ProjectInfoListQry projectInfoListQry);
    Response add(ProjectInfoDTO projectInfoDTO);

    String queryCommitMessage(String owner, String repo, String branch);
}
