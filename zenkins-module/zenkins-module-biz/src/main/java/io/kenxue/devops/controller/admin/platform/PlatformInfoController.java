package io.kenxue.devops.controller.admin.platform;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.platform.PlatformInfoAddCmd;
import io.kenxue.devops.coreclient.dto.platform.PlatformInfoDTO;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoListQry;
import io.kenxue.devops.service.PlatformInfoAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@RestController
@RequestMapping("/system/platform")
@Tag(name = "管理后台 - 平台管理")
public class PlatformInfoController {
    @Resource
    private PlatformInfoAppService platformInfoAppService;

    @PostMapping("/add")
    @Operation(summary = "添加",method = "POST")
    public Response add(@RequestBody @Valid PlatformInfoAddCmd platformInfoAddCmd) {
        return platformInfoAppService.add(platformInfoAddCmd);
    }

    @GetMapping("/list")
    @Operation(summary = "列表",method = "GET")
    public MultiResponse<PlatformInfoDTO> list(@ModelAttribute @Valid PlatformInfoListQry platformInfoListQry) {
        return platformInfoAppService.list(platformInfoListQry);
    }

}
