package io.kenxue.devops.coreclient.dto.platform.platforminfo;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Data
public class PlatformInfoListQry extends CommonCommand {
    private Long id;
    private String name;
    private String code;
    private Boolean status;
}
