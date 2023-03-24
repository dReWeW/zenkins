package io.kenxue.devops.application.common.event;

import java.lang.annotation.*;
import org.springframework.stereotype.Component;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface EventHandler {
}
