package io.kenxue.devops.domain.domain.platform;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatformInfo extends CommonEntity {
    private String name;
    private String code;
    private Boolean status;
}
