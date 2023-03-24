package io.kenxue.devops.application.common.socket.message;

import org.springframework.web.socket.WebSocketSession;

import io.kenxue.devops.coreclient.dto.common.response.Response;

/**
 * websocket 信息处理器统一接口
 */
public interface WebSocketMessageHandlerI {
    Response execute(String message, WebSocketSession session);
}