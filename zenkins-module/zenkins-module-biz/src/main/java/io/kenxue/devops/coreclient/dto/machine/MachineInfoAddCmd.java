package io.kenxue.devops.coreclient.dto.machine;


import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Data
@Accessors(chain = true)
public class MachineInfoAddCmd extends CommonCommand {
    @NotNull
    private MachineInfoDTO machineInfoDTO;
}
