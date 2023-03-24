package io.kenxue.devops.infrastructure.repositoryimpl.project;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeatureListQry;
import io.kenxue.devops.coreclient.dto.project.projectfeature.ProjectFeaturePageQry;
import io.kenxue.devops.domain.domain.project.ProjectFeature;
import io.kenxue.devops.domain.repository.project.ProjectFeatureRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.ProjectFeatureMapper;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/20/2023
 */
@Repository
public class ProjectFeatureRepositoryImpl implements ProjectFeatureRepository {

    @Resource
    private ProjectFeatureMapper projectFeatureMapper;
    @Override
    public void create(ProjectFeature projectFeature) {
        projectFeatureMapper.insert(projectFeature);
    }

    @Override
    public void update(ProjectFeature projectFeature) {
        projectFeatureMapper.updateById(projectFeature);
    }

    @Override
    public ProjectFeature getById(Long id) {
        return projectFeatureMapper.selectById(id);
    }

    @Override
    public List<ProjectFeature> list(ProjectFeatureListQry qry) {
        QueryWrapper<ProjectFeature> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", qry.getProjectId());
        return projectFeatureMapper.selectList(queryWrapper);
    }

    @Override
    public Page<ProjectFeature> page(ProjectFeaturePageQry qry) {
        QueryWrapper<ProjectFeature> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", qry.getProjectId());
        IPage<ProjectFeature> page =
            projectFeatureMapper.selectPage(new PageDTO<>(qry.getPageIndex(), qry.getPageSize()), queryWrapper);
        return Page.of(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }
}
