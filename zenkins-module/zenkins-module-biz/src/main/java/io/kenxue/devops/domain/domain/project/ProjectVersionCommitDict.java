package io.kenxue.devops.domain.domain.project;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/12/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectVersionCommitDict extends CommonEntity {
    private String version;
    private String commit;
    private Long projectId;
}
