package io.kenxue.devops.infrastructure.repositoryimpl.platform;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoListQry;
import io.kenxue.devops.coreclient.dto.platform.platforminfo.PlatformInfoPageQry;
import io.kenxue.devops.domain.domain.platform.PlatformInfo;
import io.kenxue.devops.domain.repository.platform.PlatformInfoRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.platform.database.mapper.PlatformInfoMapper;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Repository
public class PlatformInfoRepositoryImpl implements PlatformInfoRepository {
    @Resource
    private PlatformInfoMapper platformInfoMapper;
    @Override
    public void create(PlatformInfo platformInfo) {
        platformInfoMapper.insert(platformInfo);
    }

    @Override
    public void update(PlatformInfo platformInfo) {
        platformInfoMapper.updateById(platformInfo);
    }

    @Override
    public PlatformInfo getById(Long id) {
        return platformInfoMapper.selectById(id);
    }

    @Override
    public void delete(Long id) {
        platformInfoMapper.delete(new QueryWrapper<PlatformInfo>().eq("id", id));
    }

    @Override
    public List<PlatformInfo> list(PlatformInfoListQry platformInfoListQry) {
        return platformInfoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Page<PlatformInfo> page(PlatformInfoPageQry qry) {
        QueryWrapper<PlatformInfo> queryWrapper = new QueryWrapper<>();
        IPage<PlatformInfo> page =
            platformInfoMapper.selectPage(new PageDTO<>(qry.getPageIndex(), qry.getPageSize()), queryWrapper);
        return Page.of(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());


    }
}
