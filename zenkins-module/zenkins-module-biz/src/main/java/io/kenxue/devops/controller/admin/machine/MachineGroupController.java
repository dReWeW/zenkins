package io.kenxue.devops.controller.admin.machine;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.*;
import io.kenxue.devops.service.MachineGroupAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@RestController
@Tag(name = "服务器分组模块",description = "包含新增/列表/删除")
@RequestMapping("/system/machinegroup")
public class MachineGroupController {
    @Resource
    private MachineGroupAppService machineGroupAppService;

    @PostMapping("/add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid MachineGroupAddCmd machineGroupAddCmd) {
        return machineGroupAppService.add(machineGroupAddCmd);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除",method = "DELETE")
    public Response delete(@RequestBody @Valid MachineGroupDeleteCmd machineGroupDeleteCmd){
        return machineGroupAppService.delete(machineGroupDeleteCmd);
    }

    @GetMapping("/page")
    @Operation(summary = "列表",method = "GET")
    public Response page(@ModelAttribute @Valid MachineGroupPageQry machineGroupPageQry){
        return machineGroupAppService.page(machineGroupPageQry);
    }

    @GetMapping("/list")
    @Operation(summary = "列表",method = "GET")
    public Response list(@ModelAttribute @Valid MachineGroupListQry machineGroupListQry){
        return machineGroupAppService.list(machineGroupListQry);
    }

    @GetMapping("/info")
    @Operation(summary = "详情",method = "GET")
    public Response info(@ModelAttribute @Valid MachineGroupGetQry machineGroupGetQry){
        return machineGroupAppService.getById(machineGroupGetQry);
    }

    @PutMapping("/update")
    @Operation(summary = "更新",method = "PUT")
    public Response update(@RequestBody MachineGroupUpdateCmd machineGroupUpdateCmd){
        return machineGroupAppService.update(machineGroupUpdateCmd);
    }
}
