package io.kenxue.devops.application.machine.machineofgroup.command;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import io.kenxue.devops.domain.repository.machine.MachineOfGroupRepository;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupDeleteCmd;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Component
public class MachineOfGroupDeleteCmdExe {
    @Resource
    private MachineOfGroupRepository machineOfGroupRepository;

    public Response execute(MachineOfGroupDeleteCmd cmd) {
        machineOfGroupRepository.delete(cmd);
        return Response.success();
    }
}
