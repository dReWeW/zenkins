package io.kenxue.devops.infrastructure.repositoryimpl.project;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.NodeInfoListQry;
import io.kenxue.devops.coreclient.dto.project.NodeInfoPageQry;
import io.kenxue.devops.domain.domain.project.NodeInfo;
import io.kenxue.devops.domain.repository.project.NodeInfoRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.NodeInfoMapper;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@Repository
public class NodeInfoRepositoryImpl implements NodeInfoRepository {
    @Resource
    private NodeInfoMapper nodeInfoMapper;

    @Override
    public int create(NodeInfo nodeInfo) {
        return nodeInfoMapper.insert(nodeInfo);
    }

    @Override
    public void update(NodeInfo nodeInfo) {
        nodeInfoMapper.updateById(nodeInfo);
    }

    @Override
    public NodeInfo getById(Long id) {
        return nodeInfoMapper.selectById(id);
    }

    @Override
    public List<NodeInfo> list(NodeInfoListQry nodeInfoListQry) {
        QueryWrapper<NodeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", nodeInfoListQry.getProjectId());
        queryWrapper.eq("feature_id", nodeInfoListQry.getFeatureId());
        return nodeInfoMapper.selectList(queryWrapper);
    }

    @Override
    public Page<NodeInfo> page(NodeInfoPageQry qry) {
        QueryWrapper<NodeInfo> queryWrapper = new QueryWrapper<>();
        IPage<NodeInfo> page =
            nodeInfoMapper.selectPage(new PageDTO<>(qry.getPageIndex(), qry.getPageSize()), queryWrapper);
        return Page.of(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    @Override
    public void updateNodeStatus(Long nodeId, String status) {
        NodeInfo nodeInfo = getById(nodeId);
        nodeInfo.setStatus(status);
        update(nodeInfo);
    }
}
