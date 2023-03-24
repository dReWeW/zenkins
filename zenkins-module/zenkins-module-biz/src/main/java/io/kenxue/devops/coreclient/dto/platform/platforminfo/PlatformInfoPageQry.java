package io.kenxue.devops.coreclient.dto.platform.platforminfo;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import io.kenxue.devops.coreclient.dto.platform.PlatformInfoDTO;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Data
public class PlatformInfoPageQry extends PageQuery {
    private PlatformInfoDTO platformInfoDTO;
}
