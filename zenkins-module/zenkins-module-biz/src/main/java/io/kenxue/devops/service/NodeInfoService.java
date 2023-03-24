package io.kenxue.devops.service;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.NodeGroupDTO;
import io.kenxue.devops.coreclient.dto.project.NodeInfoListQry;
import io.kenxue.devops.coreclient.dto.project.RollbackVersionsQry;
import io.kenxue.devops.coreclient.dto.project.command.*;
import io.kenxue.devops.coreclient.dto.project.node.NodeInfoDTO;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
public interface NodeInfoService {
    MultiResponse<NodeGroupDTO> queryNodeInfoList(NodeInfoListQry qry);
    Response listRollbackVersion(RollbackVersionsQry qry);
    Response build(BuildNodeCmd cmd);
    Response rollback(NodeInfoRollbackCmd cmd);

    Response deploy(DeployNodeCmd cmd);

    Response recycle(NodeInfoRecycleCmd cmd);

    Response expansion(NodeInfoExpansionCmd cmd);

    SingleResponse<NodeInfoDTO> queryNodeInfoDetail(NodeInfoGetQry query);
}
