package io.kenxue.devops.infrastructure.repositoryimpl.machine.database.convertor;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.infrastructure.repositoryimpl.machine.database.dataobject.MachineInfoDO;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MachineInfo2DOMapStruct {

    MachineInfo2DOMapStruct INSTANCE = Mappers.getMapper(MachineInfo2DOMapStruct.class);

    @Mappings({})
    MachineInfoDO toDO(MachineInfo machineInfo);

    @Mappings({})
    MachineInfo toDomain(MachineInfoDO machineInfoDO);

    List<MachineInfoDO> toDOList(List<MachineInfo> machineInfoList);

    List<MachineInfo> toDomainList(List<MachineInfoDO> machineInfoDOList);
}
