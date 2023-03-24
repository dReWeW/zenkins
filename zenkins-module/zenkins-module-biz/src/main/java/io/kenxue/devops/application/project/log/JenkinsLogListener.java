package io.kenxue.devops.application.project.log;

import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.jcraft.jsch.Channel;
import com.offbytwo.jenkins.helper.BuildConsoleStreamListener;

import io.kenxue.devops.application.project.util.SpringUtilsAuTo;
import io.kenxue.devops.service.JenkinsApiService;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/14/2023
 */
public class JenkinsLogListener implements BuildConsoleStreamListener {
    private StringBuilder log;
    private Boolean finished;
    private final Long nodeId;
    private static final JenkinsApiService jenkinsApiService= SpringUtilsAuTo.getBean(JenkinsApiService.class);

    public JenkinsLogListener(Long nodeId) {
        this.nodeId = nodeId;
        log = new StringBuilder();
        finished = false;
    }

    @Override
    public void onData(String s) {
        log.append(s);
    }

    public String getLog() {
        return log.toString();
    }

    @Override
    public void finished() {
        finished = true;
        jenkinsApiService.updateJobStatus(nodeId);
    }

    public boolean isFinished() {
        // 完成则更新
        return finished;
    }

}
