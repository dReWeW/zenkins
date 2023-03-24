package io.kenxue.devops.domain.repository.machine;

import java.util.List;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupDeleteCmd;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupListQry;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupPageQry;
import io.kenxue.devops.domain.domain.machine.MachineOfGroup;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
public interface MachineOfGroupRepository {
    void create(MachineOfGroup machineOfGroup);
    void update(MachineOfGroup machineOfGroup);
    MachineOfGroup getById(Long id);
    List<MachineOfGroup> list(MachineOfGroupListQry machineOfGroupListQry);
    Page<MachineOfGroup> page(MachineOfGroupPageQry qry);
    void delete(MachineOfGroupDeleteCmd machineOfGroup);
}
