package io.kenxue.devops.controller.admin.project;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.command.ProjectVCAddCmd;
import io.kenxue.devops.service.ProjectVCService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/13/2023
 */
@RestController
@RequestMapping("/system/projectvc")
@Tag(name = "项目模块 - 版本控制面板")
public class ProjectVCController {
    @Resource
    private ProjectVCService projectVCService;

    @PostMapping
    @Operation(summary = "添加版本")
    public Response add(@RequestBody
    @Valid ProjectVCAddCmd projectVCAddCmd) {
        return projectVCService.add(projectVCAddCmd);
    }

}
