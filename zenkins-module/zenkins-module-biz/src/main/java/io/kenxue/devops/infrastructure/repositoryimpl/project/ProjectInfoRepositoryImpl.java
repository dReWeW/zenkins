package io.kenxue.devops.infrastructure.repositoryimpl.project;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.ProjectInfoListQry;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoPageQry;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.domain.repository.project.ProjectInfoRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper.ProjectInfoMapper;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Repository
public class ProjectInfoRepositoryImpl implements ProjectInfoRepository {
    @Resource
    private ProjectInfoMapper projectInfoMapper;

    @Override
    public void create(ProjectInfo projectInfo) {
        projectInfoMapper.insert(projectInfo);
    }

    @Override
    public void update(ProjectInfo projectInfo) {
        projectInfoMapper.updateById(projectInfo);
    }

    @Override
    public ProjectInfo getById(Long id) {
        return projectInfoMapper.selectById(id);
    }

    @Override
    public List<ProjectInfo> list(ProjectInfoListQry projectInfoListQry) {
        return projectInfoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Page<ProjectInfo> page(ProjectInfoPageQry qry) {
        QueryWrapper<ProjectInfo> queryWrapper = new QueryWrapper<>();
        IPage<ProjectInfo> page =
            projectInfoMapper.selectPage(new PageDTO<>(qry.getPageIndex(), qry.getPageSize()), queryWrapper);
        return Page.of(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }
}
