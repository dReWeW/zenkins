package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.kenxue.devops.domain.domain.project.ProjectVersionCommitDict;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/12/2023
 */
@Mapper
public interface ProjectVCMapper extends BaseMapper<ProjectVersionCommitDict> {
}
