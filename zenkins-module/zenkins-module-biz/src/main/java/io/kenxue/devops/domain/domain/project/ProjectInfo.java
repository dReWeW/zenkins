package io.kenxue.devops.domain.domain.project;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfo extends CommonEntity {
    private String name;
    private String code;
    private String functionary;
    private Boolean status;
    private String platform;
    private String groupId;
    private String remark;
    private String repoUrl;
    private String repoOwner;
}
