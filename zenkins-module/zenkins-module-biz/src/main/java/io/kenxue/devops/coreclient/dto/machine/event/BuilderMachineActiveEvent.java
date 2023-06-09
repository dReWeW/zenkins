package io.kenxue.devops.coreclient.dto.machine.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;

import io.kenxue.devops.coreclient.dto.machine.MachineInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author haolongz
 */
@Data
@AllArgsConstructor
public class BuilderMachineActiveEvent implements DomainEventI {
    private MachineInfoDTO machineInfoDTO;
}
