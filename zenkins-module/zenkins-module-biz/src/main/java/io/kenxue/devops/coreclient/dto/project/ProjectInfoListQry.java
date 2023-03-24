package io.kenxue.devops.coreclient.dto.project;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Data
@Accessors(chain = true)
public class ProjectInfoListQry extends CommonCommand {
    private Long id;
    private String name;
    private String repoUrl;
    private String repoOwner;
}
