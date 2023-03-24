package io.kenxue.devops.application.machine.terminal;

import org.springframework.web.socket.WebSocketSession;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;

import lombok.Data;

/**
* @Description: ssh连接信息
*/
@Data
public class SSHConnectInfo {
    private WebSocketSession webSocketSession;
    private JSch jSch;
    private Channel channel;
}
