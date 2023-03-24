package io.kenxue.devops.infrastructure.repositoryimpl.project;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.kenxue.devops.domain.domain.project.ProjectVersionCommitDict;
import io.kenxue.devops.domain.repository.project.ProjectVCRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.ProjectVCMapper;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/12/2023
 */
@Repository
public class ProjectVCRepositoryImpl implements ProjectVCRepository {
    @Resource
    private ProjectVCMapper projectVCMapper;

    @Override
    public void create(ProjectVersionCommitDict projectVersionCommitDict) {
        projectVCMapper.insert(projectVersionCommitDict);
    }

    @Override
    public void update(ProjectVersionCommitDict projectVersionCommitDict) {
        projectVCMapper.updateById(projectVersionCommitDict);
    }

    @Override
    public ProjectVersionCommitDict getById(Long id) {
        return projectVCMapper.selectById(id);
    }

    @Override
    public ProjectVersionCommitDict getByProjectIdAndCommit(Long projectId, String version) {
        QueryWrapper<ProjectVersionCommitDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("commit", version);
        return projectVCMapper.selectOne(queryWrapper);
    }

    @Override
    public ProjectVersionCommitDict getByProjectIdAndVersion(Long projectId, String version) {
        QueryWrapper<ProjectVersionCommitDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("version", version);
        return projectVCMapper.selectOne(queryWrapper);
    }
}
