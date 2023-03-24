package io.kenxue.devops.application.platform.assembler;

import java.util.List;

import org.springframework.stereotype.Component;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.platform.PlatformInfoDTO;
import io.kenxue.devops.domain.domain.platform.PlatformInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Component
public class PlatformInfo2DTOAssembler implements Assembler<PlatformInfoDTO, PlatformInfo> {
    @Override
    public PlatformInfoDTO toDTO(PlatformInfo dO) {
        return PlatformInfo2DTOMapStruct.INSTANCE.toDTO(dO);
    }

    @Override
    public PlatformInfo toDomain(PlatformInfoDTO dTO) {
        return PlatformInfo2DTOMapStruct.INSTANCE.toDomain(dTO);
    }

    @Override
    public List<PlatformInfoDTO> toDTOList(List<PlatformInfo> platformInfos) {
        return PlatformInfo2DTOMapStruct.INSTANCE.toDTOList(platformInfos);
    }

    @Override
    public List<PlatformInfo> toDomainList(List<PlatformInfoDTO> dTOList) {
        return PlatformInfo2DTOMapStruct.INSTANCE.toDomainList(dTOList);
    }

    @Override
    public Page<PlatformInfoDTO> toDTOPage(Page<PlatformInfo> page) {
        return new Page<>(page.getCurrent(),
            page.getSize(),
            page.getTotal(),
            PlatformInfo2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
