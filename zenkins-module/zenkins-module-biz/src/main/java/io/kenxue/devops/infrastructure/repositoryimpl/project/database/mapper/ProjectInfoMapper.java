package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.kenxue.devops.domain.domain.project.ProjectInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Mapper
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {
}
