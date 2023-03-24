package io.kenxue.devops.application.platform.assembler;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import io.kenxue.devops.coreclient.dto.platform.PlatformInfoDTO;
import io.kenxue.devops.domain.domain.platform.PlatformInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Mapper(componentModel = "spring")
public interface PlatformInfo2DTOMapStruct {
    PlatformInfo2DTOMapStruct INSTANCE = Mappers.getMapper(PlatformInfo2DTOMapStruct.class);

    @Mappings({})
    PlatformInfoDTO toDTO(PlatformInfo platformInfo);

    @Mappings({})
    PlatformInfo toDomain(PlatformInfoDTO platformInfoDTO);

    List<PlatformInfoDTO> toDTOList(List<PlatformInfo> platformInfoList);

    List<PlatformInfo> toDomainList(List<PlatformInfoDTO> platformInfoDTOList);
}
