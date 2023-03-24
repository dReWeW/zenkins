package cn.iocoder.yudao.framework.websocket.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import javax.annotation.Resource;

import io.kenxue.devops.application.common.socket.handler.WebSocketCenterHandler;

/**
 * WebSocket 自动配置
 *
 * @author xingyu4j
 */
@AutoConfiguration
// 允许使用 yudao.websocket.enable=false 禁用websocket
@ConditionalOnProperty(prefix = "yudao.websocket", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(WebSocketProperties.class)
public class YudaoWebSocketAutoConfiguration {
    @Resource
    private WebSocketCenterHandler websocketCenterHandler;
    @Bean
    @ConditionalOnMissingBean
    public WebSocketConfigurer webSocketConfigurer(List<HandshakeInterceptor> handshakeInterceptor,
                                                   WebSocketHandler webSocketHandler,
                                                   WebSocketProperties webSocketProperties) {
        System.out.println("111 websocketCenterHandler = " + websocketCenterHandler);

        return registry -> registry
                .addHandler(webSocketHandler, webSocketProperties.getPath())
//                .addHandler(websocketCenterHandler, "/ws/**")
                .addInterceptors(handshakeInterceptor.toArray(new HandshakeInterceptor[0]));
    }
}
