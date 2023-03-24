package io.kenxue.devops.domain.repository.machine;

import java.util.List;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoListQry;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoPageQry;
import io.kenxue.devops.domain.domain.machine.MachineInfo;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
public interface MachineInfoRepository {
    void create(MachineInfo machineInfo);
    void update(MachineInfo machineInfo);
    MachineInfo getById(Long id);
    List<MachineInfo> list(MachineInfoListQry machineInfoListQry);
    Page<MachineInfo> page(MachineInfoPageQry qry);
    MachineInfo getByUuid(String uuid);

    List<MachineInfo> getByUuid(List<String> uuids);
}
