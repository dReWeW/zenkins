package io.kenxue.devops.coreclient.dto.project.projectfeature;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFeaturePageQry extends PageQuery {
    private Long projectId;
}
