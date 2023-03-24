package io.kenxue.devops.application.machine.command.query;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import io.kenxue.devops.application.machine.assembler.MachineInfo2DTOAssembler;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.domain.repository.machine.MachineInfoRepository;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoDTO;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoPageQry;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Component
public class MachineInfoPageQryExe {

    @Resource
    private MachineInfoRepository machineInfoRepository;
    @Resource
    private MachineInfo2DTOAssembler machineInfo2DTOAssembler;

    public PageResponse<MachineInfoDTO> execute(MachineInfoPageQry qry) {
        Page<MachineInfo> page = machineInfoRepository.page(qry);
        return PageResponse.of(machineInfo2DTOAssembler.toDTOPage(page));
    }
}
