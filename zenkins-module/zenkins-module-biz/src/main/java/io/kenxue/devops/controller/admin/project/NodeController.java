package io.kenxue.devops.controller.admin.project;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import io.kenxue.devops.coreclient.dto.project.command.*;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.project.NodeInfoListQry;
import io.kenxue.devops.coreclient.dto.project.RollbackVersionsQry;
import io.kenxue.devops.service.NodeInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@RestController
@Tag(name = "系统管理 - 流水线节点模块", description = "节点管理")
@RequestMapping("/system/project/node")
public class NodeController {
    @Resource
    private NodeInfoService nodeInfoService;

    @GetMapping("/list")
    @Operation(summary = "获取节点列表")
    public Response list(
        @ModelAttribute
        @Valid NodeInfoListQry query) {
            return nodeInfoService.queryNodeInfoList(query);
    }

    @GetMapping("/detail")
    @Operation(summary = "获取节点详情")
    public Response detail(
        @ModelAttribute
        @Valid NodeInfoGetQry query) {
        return nodeInfoService.queryNodeInfoDetail(query);
    }

    @GetMapping("/rollback/list")
    @Operation(summary = "获取回滚版本")
    public Response listRobllback(
        @ModelAttribute
        @Valid RollbackVersionsQry qry) {
        return nodeInfoService.listRollbackVersion(qry);
    }

    @PostMapping("/build")
    @Operation(summary = "构建节点")
    public Response add(
        @RequestBody
        @Valid BuildNodeCmd cmd) {
        return nodeInfoService.build(cmd);
    }
    @PostMapping("/deploy")
    @Operation(summary = "部署节点")
    public Response deploy(
        @RequestBody
        @Valid DeployNodeCmd cmd) {
        return nodeInfoService.deploy(cmd);
    }

    @PostMapping("/rollback")
    @Operation(summary = "回滚")
    public Response rollback(
        @RequestBody
        @Valid NodeInfoRollbackCmd cmd) {
        return nodeInfoService.rollback(cmd);
    }

    @PostMapping("/recycle")
    @Operation(summary = "回收")
    public Response recycle(
        @RequestBody
        @Valid NodeInfoRecycleCmd cmd) {
        return nodeInfoService.recycle(cmd);
    }

    @PostMapping("/expansion")
    @Operation(summary = "扩缩容")
    public Response expansion(
        @RequestBody
        @Valid NodeInfoExpansionCmd cmd) {
        return nodeInfoService.expansion(cmd);
    }

}
