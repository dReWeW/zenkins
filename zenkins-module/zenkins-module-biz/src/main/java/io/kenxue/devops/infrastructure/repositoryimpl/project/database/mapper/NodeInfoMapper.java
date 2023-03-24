package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.kenxue.devops.domain.domain.project.NodeInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/8/2023
 */
@Mapper
public interface NodeInfoMapper extends BaseMapper<NodeInfo> {
}
