package io.kenxue.devops.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 2/28/2023
 */
@Configuration
@Data
public class GiteeOpenApiConfig {
    @Value("${gitee.openapi.accessToken}")
    private String accessToken;
}
