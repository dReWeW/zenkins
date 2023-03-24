package io.kenxue.devops.domain.repository.platform;

import java.util.List;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoListQry;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoPageQry;
import io.kenxue.devops.domain.domain.platform.PlatformInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
public interface PlatformInfoRepository {
    void create(PlatformInfo platformInfo);
    void update(PlatformInfo platformInfo);
    PlatformInfo getById(Long id);
    void delete(Long id);
    List<PlatformInfo> list(PlatformInfoListQry platformInfoListQry);
    Page<PlatformInfo> page(PlatformInfoPageQry qry);

}
