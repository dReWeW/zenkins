package cn.iocoder.yudao.module.infra.websocket;

import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import io.kenxue.devops.application.common.socket.handler.WebSocketCenterHandler;
import io.kenxue.devops.application.common.socket.handler.WebSocketInterceptor;

/**
 * websocket 配置
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Resource
    private WebSocketCenterHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        System.out.println("webSocketHandler = " + webSocketHandler);
        //socket通道
        //指定处理器和路径
        webSocketHandlerRegistry
            .addHandler(webSocketHandler, "/ws/**")
            .addInterceptors(new WebSocketInterceptor())
            .setAllowedOrigins("*");
    }
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
