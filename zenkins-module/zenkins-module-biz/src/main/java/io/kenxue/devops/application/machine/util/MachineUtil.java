package io.kenxue.devops.application.machine.util;

import java.io.InputStream;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import io.kenxue.devops.application.common.util.JschUtil;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import io.kenxue.devops.sharedataobject.common.command.CommandConst;
import io.kenxue.devops.sharedataobject.machine.enums.LsbReleaseEnum;
import lombok.SneakyThrows;

public class MachineUtil {
    @SneakyThrows
    public static LsbReleaseEnum getRelease(MachineInfo machineInfo){
        Session session = JschUtil.getSession(machineInfo);
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        StringBuilder command = new StringBuilder(CommandConst.LSB_RELEASE);
        channel.setCommand(command.toString());
        InputStream in=channel.getInputStream();
        channel.connect();
        StringBuilder sb = new StringBuilder();
        byte[] tmp=new byte[1024];
        while(true){
            while(in.available()>0){
                int i=in.read(tmp, 0, 1024);
                if(i<0)break;
                String response = new String(tmp, 0, i);
                System.out.print(response);
                sb.append(response);
            }
            if(channel.isClosed()){
                if(in.available()>0) continue;
                System.out.println("exit-status: "+channel.getExitStatus());
                break;
            }
        }
        channel.disconnect();
        session.disconnect();
        String response  = sb.toString().replace("\t"," ").replace("\n","");
        LsbReleaseEnum releaseEnum = LsbReleaseEnum.get(response);
        return releaseEnum;
    }
}
