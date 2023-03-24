package io.kenxue.devops.coreclient.dto.project.projectfeature;

import java.util.Date;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
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
public class ProjectFeatureDTO extends CommonDTO {
    private Long projectId;
    private String name;
    private Long buildCount;
    private Date lastBuild;
    private Long qaCount;
    private String lastQaStatus;
    private Long publishCount;
    private String lastPublishStatus;
}
