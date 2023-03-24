package io.kenxue.devops.application.project.log;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import io.kenxue.devops.application.common.socket.handler.WebSocket;
import io.kenxue.devops.application.common.socket.service.WebSocketService;
import io.kenxue.devops.domain.domain.project.NodeInfo;
import io.kenxue.devops.domain.repository.project.NodeInfoRepository;
import io.kenxue.devops.service.JenkinsApiService;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/14/2023
 */
@Service
@Slf4j
@WebSocket("log")
public class JenkinsLogWebsocketServiceImpl implements WebSocketService {
    @Resource
    private NodeInfoRepository nodeInfoRepository;
    @Resource
    private JenkinsApiService jenkinsApiService;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void initConnection(WebSocketSession session) {
        log.info("query log websocket initConnection {}", session.getUri());
        URI uri = session.getUri();
        assert uri != null;
        String nodeId = uri.getQuery();
        NodeInfo nodeInfo = nodeInfoRepository.getById(Long.parseLong(nodeId));
        executorService.execute(() -> {
            try {
                getLog(session, nodeInfo);
            } catch (InterruptedException | IOException e) {
                try {
                    session.sendMessage(new TextMessage("连接异常" + e.getMessage()));
                } catch (IOException e1) {
                    log.error("响应异常：{}",e1.getMessage());
                }
                log.error("获取日志异常：{}",e.getMessage());
                close(session);

            }
        });
    }

    private void getLog(WebSocketSession session, NodeInfo nodeInfo) throws InterruptedException, IOException {
        JenkinsLogListener jenkinsLogListener = jenkinsApiService.getListener(nodeInfo);
        if (Objects.isNull(jenkinsLogListener) || jenkinsLogListener.isFinished()) {
            session.sendMessage(new TextMessage(jenkinsApiService.getLog(nodeInfo)));
            return;
        }
        while (!jenkinsLogListener.isFinished()) {
            Thread.sleep(1000);
            session.sendMessage(new TextMessage(jenkinsLogListener.getLog()));
        }
    }

    @Override
    public void recvHandle(String buffer, WebSocketSession session) {

    }

    @Override
    public void sendMessage(String key, byte[] buffer) {

    }

    @Override
    public void close(WebSocketSession session) {
    }

    @Override
    public void close(String key) {

    }
}
