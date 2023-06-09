package io.kenxue.devops.infrastructure.repositoryimpl.machine.database.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.kenxue.devops.coreclient.dto.machine.MachineInfoPageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.machine.database.dataobject.MachineInfoDO;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Mapper
public interface MachineInfoMapper extends BaseMapper<MachineInfoDO> {

    /**
     * @param iPage
     * @param qry
     * @return
     */
    List<MachineInfoDO> queryPage(IPage iPage, @Param("qry") MachineInfoPageQry qry);
}
