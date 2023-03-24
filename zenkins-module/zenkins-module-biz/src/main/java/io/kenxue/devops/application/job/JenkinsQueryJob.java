package io.kenxue.devops.application.job;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import cn.iocoder.yudao.framework.quartz.core.handler.JobHandler;
import io.kenxue.devops.service.JenkinsApiService;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/13/2023
 */
@Component
@Slf4j
public class JenkinsQueryJob implements JobHandler {
    @Resource
    private JenkinsApiService jenkinsApiService;
    @Override
    public String execute(String param) throws Exception {
        log.info("JenkinsQueryJob start");
        jenkinsApiService.updateJobStatus();
        log.info("JenkinsQueryJob end");
        return "success";
    }
}
