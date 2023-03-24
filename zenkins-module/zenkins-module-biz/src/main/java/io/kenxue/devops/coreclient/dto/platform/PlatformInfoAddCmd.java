package io.kenxue.devops.coreclient.dto.platform;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Data
@Accessors(chain = true)
public class PlatformInfoAddCmd extends CommonCommand {
    @NotNull
    private PlatformInfoDTO platformInfoDTO;
}
