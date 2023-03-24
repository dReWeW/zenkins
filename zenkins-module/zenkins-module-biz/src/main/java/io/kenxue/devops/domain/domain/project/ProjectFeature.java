package io.kenxue.devops.domain.domain.project;

import java.util.Date;

import io.kenxue.devops.domain.common.CommonEntity;
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
public class ProjectFeature extends CommonEntity {
    String name;
    Long projectId;
    Long buildCount;
    Date lastBuild;
    Long qaCount;
    String lastQaStatus;
    Long publishCount;
    String lastPublishStatus;
}
