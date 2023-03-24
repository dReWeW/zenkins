package io.kenxue.devops.application.project.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import io.kenxue.devops.application.project.assembler.ProjectVC2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.command.ProjectVCAddCmd;
import io.kenxue.devops.domain.domain.project.ProjectVersionCommitDict;
import io.kenxue.devops.domain.repository.project.ProjectVCRepository;
import io.kenxue.devops.service.ProjectVCService;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/13/2023
 */
@Service
public class ProjectVCServiceImpl implements ProjectVCService {
    @Resource
    private ProjectVCRepository projectVCRepository;
    @Resource
    private ProjectVC2DTOAssembler projectVC2DTOAssembler;

    @Override
    public Response add(ProjectVCAddCmd projectVCAddCmd) {
        ProjectVersionCommitDict projectVersionCommitDict = projectVC2DTOAssembler.toDomain(projectVCAddCmd.getProjectVCDTO());
        projectVersionCommitDict.create();
        projectVCRepository.create(projectVersionCommitDict);
        return Response.success();
    }
}
