package io.kenxue.devops.coreclient.dto.jenkins;

import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.QueueItem;
import com.offbytwo.jenkins.model.QueueReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/13/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JenkinsJobItem {
    private Long nodeId;
    private QueueItem queueItem;
}
