package io.kenxue.devops.service;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.command.ProjectVCAddCmd;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/13/2023
 */
public interface ProjectVCService {
    Response add(ProjectVCAddCmd projectVCAddCmd);
}
