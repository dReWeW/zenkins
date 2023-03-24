package io.kenxue.devops.infrastructure.repositoryimpl.machine.database.convertor;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import io.kenxue.devops.domain.domain.machine.MachineOfGroup;
import io.kenxue.devops.infrastructure.repositoryimpl.machine.database.dataobject.MachineOfGroupDO;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MachineOfGroup2DOMapStruct {

    MachineOfGroup2DOMapStruct INSTANCE = Mappers.getMapper(MachineOfGroup2DOMapStruct.class);

    @Mappings({})
    MachineOfGroupDO toDO(MachineOfGroup machineOfGroup);

    @Mappings({})
    MachineOfGroup toDomain(MachineOfGroupDO machineOfGroupDO);

    List<MachineOfGroupDO> toDOList(List<MachineOfGroup> machineOfGroupList);

    List<MachineOfGroup> toDomainList(List<MachineOfGroupDO> machineOfGroupDOList);
}
