package io.kenxue.devops.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/1/2023
 */
@Configuration
@Data
public class JenkinsConfig {
    @Value("${jenkins.server.jenkinsServerUrl}")
    private String jenkinsServerUrl;
    @Value("${jenkins.repo.repositoryUrl}")
    private String repositoryUrl;
    @Value("${jenkins.repo.api.queryVersion}")
    private String queryVersionApi;
}
