package io.kenxue.devops.application.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.swing.text.html.Option;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.gitee.api.model.Branch;
import com.gitee.api.model.RepositoriesCommit;

import io.kenxue.devops.application.project.assembler.ProjectInfo2DTOAssembler;
import io.kenxue.devops.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.ProjectInfoGetQry;
import io.kenxue.devops.coreclient.dto.project.ProjectInfoListQry;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectBranchDTO;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import io.kenxue.devops.service.GiteeOpenApiService;
import io.kenxue.devops.service.ProjectInfoAppService;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Service
@Slf4j
public class ProjectInfoAppServiceImpl implements ProjectInfoAppService {

    @Resource
    private ProjectInfoRepository projectInfoRepository;
    @Resource
    private ProjectInfo2DTOAssembler projectInfo2DTOAssembler;
    @Resource
    private GiteeOpenApiService giteeOpenApiService;

    @Override
    public SingleResponse<ProjectInfo> getById(ProjectInfoGetQry query) {
        ProjectInfo projectInfo = projectInfoRepository.getById(query.getId());
        // TODO
        return null;
    }

    @Override
    public MultiResponse<ProjectBranchDTO> branchList(ProjectInfoListQry projectInfoListQry) {
        // 打印入参
        log.debug("projectInfoListQry:{}", JSON.toJSONString(projectInfoListQry));
        ProjectInfo projectInfo = projectInfoRepository.getById(projectInfoListQry.getId());
        List<ProjectBranchDTO> projectBranchDTOList =
            giteeOpenApiService.getAllRepositoryBranches(projectInfo.getRepoOwner(), projectInfo.getCode()).stream()
                .map(projectBranch -> {
                    ProjectBranchDTO projectBranchDTO = new ProjectBranchDTO();
                    projectBranchDTO.setName(projectBranch.getName());
                    projectBranchDTO.setCommit(projectBranch.getCommit().getSha());
                    projectBranchDTO.setBranchUrl(StringUtils.join(projectInfo.getRepoUrl(),
                        "/tree/",
                        projectBranch.getName()));
                    return projectBranchDTO;
                }).collect(Collectors.toList());
        // 打印出参
        log.debug("projectBranchDTOList:{}", JSON.toJSONString(projectBranchDTOList));
        return MultiResponse.of(projectBranchDTOList);
    }

    @Override
    public Response add(ProjectInfoDTO projectInfoDTO) {
        log.debug("projectInfoDTO:{}", JSON.toJSONString(projectInfoDTO));
        ProjectInfo projectInfo = projectInfo2DTOAssembler.toDomain(projectInfoDTO);
        projectInfo.create(UserThreadContext.get());
        projectInfoRepository.create(projectInfo);
        log.debug("projectInfo:{}", JSON.toJSONString(projectInfo));
        return Response.success();
    }

    @Override
    public String queryCommitMessage(String owner, String repo, String branch){
        Branch branchDTO = giteeOpenApiService.getSingleBranch(owner,repo,branch);
        assert branchDTO != null;
        RepositoriesCommit repositoriesCommit = giteeOpenApiService.getSingleCommit(owner,repo,branchDTO.getCommit().getSha());
        assert repositoriesCommit != null;
        return Optional.ofNullable(repositoriesCommit.getCommit().getMessage()).orElse("");
    }

}
