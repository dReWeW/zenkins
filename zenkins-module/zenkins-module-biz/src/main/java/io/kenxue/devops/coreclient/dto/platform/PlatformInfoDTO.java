package io.kenxue.devops.coreclient.dto.platform;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Data
public class PlatformInfoDTO extends CommonDTO {
    private String name;
    private String code;
    private Boolean status;
}
