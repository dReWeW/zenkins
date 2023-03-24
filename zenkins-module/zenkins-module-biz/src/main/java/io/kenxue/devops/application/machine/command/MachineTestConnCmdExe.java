package io.kenxue.devops.application.machine.command;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import io.kenxue.devops.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoAddCmd;
import io.kenxue.devops.coreclient.exception.code.SSHErrorCode;

/**
 * 服务器节点测试连接
 *
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineTestConnCmdExe {

    @Resource
    private MachineInfo2DTOAssembler machineInfo2DTOAssembler;

    public Response execute(MachineInfoAddCmd cmd) {

        MachineInfo machineInfo = machineInfo2DTOAssembler.toDomain(cmd.getMachineInfoDTO());

        try {
            machineInfo.testConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.error(SSHErrorCode.CONNECTION_ERROR.getCode(), SSHErrorCode.CONNECTION_ERROR.getDesc()+e.getMessage());
        }
        return Response.success();
    }
}
