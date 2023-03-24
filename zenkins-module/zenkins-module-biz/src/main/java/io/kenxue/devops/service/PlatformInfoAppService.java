package io.kenxue.devops.service;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.platform.*;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoListQry;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoPageQry;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
public interface PlatformInfoAppService {
    Response add(PlatformInfoAddCmd cmd);
    Response update(PlatformInfoUpdateCmd cmd);
    Response delete(PlatformInfoDeleteCmd userDeleteCmd);
    SingleResponse<PlatformInfoDTO> getById(PlatformInfoGetQry qry);
    MultiResponse<PlatformInfoDTO> list(PlatformInfoListQry qry);
    PageResponse<PlatformInfoDTO> page(PlatformInfoPageQry userPageQry);
}
