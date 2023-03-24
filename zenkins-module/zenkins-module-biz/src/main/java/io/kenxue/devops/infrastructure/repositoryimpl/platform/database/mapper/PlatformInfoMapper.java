package io.kenxue.devops.infrastructure.repositoryimpl.platform.database.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.kenxue.devops.domain.domain.platform.PlatformInfo;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/5/2023
 */
@Mapper
public interface PlatformInfoMapper extends BaseMapper<PlatformInfo> {

}
