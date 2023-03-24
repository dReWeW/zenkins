package io.kenxue.devops.domain.repository.project;

import java.util.List;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.NodeInfoListQry;
import io.kenxue.devops.coreclient.dto.project.NodeInfoPageQry;
import io.kenxue.devops.domain.domain.project.NodeInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
public interface NodeInfoRepository {
    int create(NodeInfo nodeInfo);
    void update(NodeInfo nodeInfo);
    NodeInfo getById(Long id);
    List<NodeInfo> list(NodeInfoListQry nodeInfoListQry);
    Page<NodeInfo> page(NodeInfoPageQry qry);

    void updateNodeStatus(Long nodeId, String status);
    //    void delete(NodeInfoDeleteCmd nodeInfo);

}
