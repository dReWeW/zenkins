package io.kenxue.devops.application.common.util;

import java.util.Properties;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import io.kenxue.devops.domain.domain.machine.MachineInfo;
import lombok.SneakyThrows;

public class JschUtil {

    private static JSch jsch = new JSch();

    @SneakyThrows
    public static Session getSession(MachineInfo machineInfo) {
        Session session = jsch.getSession(machineInfo.getAccessUsername(), machineInfo.getIp(), machineInfo.getPort());
        // 设置密码
        session.setPassword(machineInfo.getAccessPassword());
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        // 为Session对象设置properties
        session.setConfig(config);
        // 设置timeout时间
        session.setTimeout(50000);
        // 通过Session建立链接
        session.connect();
        return session;
    }
}
