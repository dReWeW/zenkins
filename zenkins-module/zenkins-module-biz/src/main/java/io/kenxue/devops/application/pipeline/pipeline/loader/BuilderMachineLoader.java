package io.kenxue.devops.application.pipeline.pipeline.loader;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import io.kenxue.devops.application.common.loader.Loader;
import io.kenxue.devops.coreclient.dto.machine.MachineInfoListQry;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.domain.repository.machine.MachineInfoRepository;
import io.kenxue.devops.sharedataobject.machine.enums.GroupEnum;

/**
 * 构建服务器加载器
 * @author haolongz
 */
@Service
public class BuilderMachineLoader implements Loader<String,MachineInfo> {

    @Resource
    private MachineInfoRepository machineInfoRepository;

    @Override
    public MachineInfo load() {
        return null;
    }

    @Override
    public MachineInfo load(String key) {
        return null;
    }

    @Override
    public List<MachineInfo> loadAll() {
        return machineInfoRepository.list(new MachineInfoListQry().setGroupUuid(GroupEnum.BUILDER_MACHINE.getUuid()));
    }

    @Override
    public List<MachineInfo> loadAll(List<String> keys) {
        return null;
    }
}
