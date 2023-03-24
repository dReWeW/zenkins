package io.kenxue.devops.application.redis;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 描述
 *
 * @author haolongz@fiture.com
 * @date 3/12/2023
 */
@Repository
public class JenkinsRedisDAO {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String PORT_KEY = "dynamic_port";
    private static final String ALLOCATE_KEY = "allocate_port";
    private static final String JOB_KEY = "job";

    public List<String> getJobList() {
        return redisTemplate.opsForList().range(JOB_KEY, 0, -1);
    }

    public void addJob(String job) {
        redisTemplate.opsForList().leftPush(JOB_KEY, job);
    }

    public void removeJob(String job) {
        redisTemplate.opsForList().remove(JOB_KEY, 0, job);
    }
    public Boolean hasPort(Integer port) {
        return redisTemplate.opsForSet().isMember(PORT_KEY, String.valueOf(port));
    }

    public void addPort(Integer port) {
        redisTemplate.opsForSet().add(PORT_KEY, String.valueOf(port));
    }

    public void removePort(Integer port) {
        redisTemplate.opsForSet().remove(PORT_KEY, String.valueOf(port));
    }

    public Integer getAllocatePort() {
        String port = redisTemplate.opsForValue().get(ALLOCATE_KEY);
        if (port == null) {
            redisTemplate.opsForValue().set(ALLOCATE_KEY, "10000");
            return 10000;
        }
        return Integer.valueOf(port);
    }

    public void increaseAllocatePort() {
        Integer port = getAllocatePort();
        if (port == null) {
            return;
        }
        if (port >= 20000) {
            redisTemplate.opsForValue().set(ALLOCATE_KEY, "10000");
            return;
        }
        redisTemplate.opsForValue().set(ALLOCATE_KEY, String.valueOf(port + 1));
    }
}
