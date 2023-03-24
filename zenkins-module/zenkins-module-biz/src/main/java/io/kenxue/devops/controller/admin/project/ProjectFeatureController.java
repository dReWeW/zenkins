package io.kenxue.devops.controller.admin.project;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.ProjectFeatureGetQry;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureDTO;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureListQry;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeaturePageQry;
import io.kenxue.devops.service.ProjectFeatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
@RestController
@RequestMapping("/system/project/feature")
@Tag(name = "项目模块 - feature模块")
public class ProjectFeatureController {
    @Resource
    private ProjectFeatureService projectFeatureService;

    @GetMapping("/get")
    @Operation(summary = "获取单个feature信息")
    public Response get(
        @ModelAttribute
        @Valid ProjectFeatureGetQry qry) {
        return projectFeatureService.getById(qry.getFeatureId());
    }

    @GetMapping("/list")
    @Operation(summary = "获取feature列表")
    public Response list(
        @ModelAttribute
        @Valid ProjectFeatureListQry qry) {
        return projectFeatureService.list(qry);
    }

    @GetMapping("/page")
    @Operation(summary = "获取feature分页")
    public Response page(
        @ModelAttribute
        @Valid ProjectFeaturePageQry qry) {
        return projectFeatureService.page(qry);
    }

    @PostMapping("/add")
    @Operation(summary = "添加feature")
    public Response add(
        @RequestBody
        @Valid ProjectFeatureDTO projectFeatureDTO) {
        return projectFeatureService.create(projectFeatureDTO);
    }

}
