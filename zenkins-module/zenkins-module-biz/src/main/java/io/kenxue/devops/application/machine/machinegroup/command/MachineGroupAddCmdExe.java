package io.kenxue.devops.application.machine.machinegroup.command;

import org.springframework.stereotype.Component;

import io.kenxue.devops.application.machine.machinegroup.assembler.MachineGroup2DTOAssembler;
import io.kenxue.devops.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.MachineGroupAddCmd;
import io.kenxue.devops.domain.domain.machine.MachineGroup;
import io.kenxue.devops.domain.repository.machine.MachineGroupRepository;

import javax.annotation.Resource;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroupAddCmdExe {

    @Resource
    private MachineGroupRepository machineGroupRepository;
    @Resource
    private MachineGroup2DTOAssembler machineGroup2DTOAssembler;

    public Response execute(MachineGroupAddCmd cmd) {
        MachineGroup machineGroup = machineGroup2DTOAssembler.toDomain(cmd.getMachineGroupDTO());
        machineGroup.create(UserThreadContext.get());
        machineGroupRepository.create(machineGroup);
        return Response.success();
    }
}
