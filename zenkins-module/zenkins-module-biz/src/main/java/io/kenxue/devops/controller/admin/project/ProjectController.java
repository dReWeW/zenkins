package io.kenxue.devops.controller.admin.project;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.ProjectInfoListQry;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoDTO;
import io.kenxue.devops.service.ProjectInfoAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@RestController
@RequestMapping("/system/project")
@Tag(name = "项目模块 - 应用面板")
public class ProjectController {
    @Resource
    private ProjectInfoAppService projectInfoAppService;
    @PostMapping("/add")
    @Operation(summary = "添加项目")
    public Response add(@RequestBody @Valid ProjectInfoDTO projectInfoDTO) {
        return projectInfoAppService.add(projectInfoDTO);
    }

    @GetMapping("/branch/list")
    @Operation(summary = "获取远程仓库分支列表")
    public Response branchList(
        @ModelAttribute
        @Valid ProjectInfoListQry projectInfoListQry) {
        return projectInfoAppService.branchList(projectInfoListQry);
    }
}
