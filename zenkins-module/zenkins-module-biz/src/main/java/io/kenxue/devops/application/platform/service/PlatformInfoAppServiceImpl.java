package io.kenxue.devops.application.platform.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.kenxue.devops.application.platform.assembler.PlatformInfo2DTOAssembler;
import io.kenxue.devops.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.platform.*;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoListQry;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoPageQry;
import io.kenxue.devops.domain.domain.platform.PlatformInfo;
import io.kenxue.devops.domain.repository.platform.PlatformInfoRepository;
import io.kenxue.devops.service.PlatformInfoAppService;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Service
public class PlatformInfoAppServiceImpl implements PlatformInfoAppService {
    @Resource
    private PlatformInfoRepository platformInfoRepository;
    @Resource
    private PlatformInfo2DTOAssembler platformInfo2DTOAssembler;

    @Override
    public Response add(PlatformInfoAddCmd cmd) {
        PlatformInfo platformInfo = platformInfo2DTOAssembler.toDomain(cmd.getPlatformInfoDTO());
        platformInfo.create(UserThreadContext.get());
        platformInfoRepository.create(platformInfo);
        return Response.success();
    }

    @Override
    public Response update(PlatformInfoUpdateCmd cmd) {
        PlatformInfo platformInfo = platformInfo2DTOAssembler.toDomain(cmd.getPlatformInfoDTO());
        platformInfo.create(UserThreadContext.get());
        platformInfoRepository.update(platformInfo);
        return Response.success();
    }

    @Override
    public Response delete(PlatformInfoDeleteCmd userDeleteCmd) {
        for (Long id : userDeleteCmd.getIds()) {
            platformInfoRepository.delete(id);
        }
        return Response.success();
    }

    @Override
    public SingleResponse<PlatformInfoDTO> getById(PlatformInfoGetQry qry) {
        return SingleResponse.of(platformInfo2DTOAssembler.toDTO(platformInfoRepository.getById(qry.getId())));
    }

    @Override
    public MultiResponse<PlatformInfoDTO> list(PlatformInfoListQry qry) {
        return MultiResponse.of(platformInfo2DTOAssembler.toDTOList(platformInfoRepository.list(qry)));
    }

    @Override
    public PageResponse<PlatformInfoDTO> page(PlatformInfoPageQry userPageQry) {
        return PageResponse.of(platformInfo2DTOAssembler.toDTOPage(platformInfoRepository.page(userPageQry)));
    }
}
