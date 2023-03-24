package io.kenxue.devops.application.common.event;

import java.util.concurrent.ExecutorService;

import io.kenxue.devops.coreclient.dto.common.event.EventI;
import io.kenxue.devops.coreclient.dto.common.response.Response;

/**
 * 事件处理器顶层接口
 * @param <R>
 * @param <E>
 */
public interface EventHandlerI<R extends Response, E extends EventI> {

    default ExecutorService getExecutor(){
        return null;
    }

    R execute(E e);
}
