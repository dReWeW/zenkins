package io.kenxue.devops.coreclient.dto.project;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFeatureGetQry extends CommonCommand {
    private Long featureId;
}
