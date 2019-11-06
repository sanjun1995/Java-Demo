package com.sanjun.project.scheduled.config;

import com.sanjun.project.scheduled.entity.ScheduledTaskEnum;
import com.sanjun.project.scheduled.service.ScheduledTaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import java.util.Map;

/**
 * Created by caozhixin on 2019-11-04 17:38
 */
@Configuration
public class ScheduledTaskConfig {
    /**
     * 日志
     */
    private  static  final Logger logger = LoggerFactory.getLogger(ScheduledTaskConfig.class);

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskExecutor() {
        logger.info("创建定时任务调度线程池 start");
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        threadPoolTaskScheduler.setThreadNamePrefix("taskExecutor-");
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        logger.info("创建定时任务调度线程池 end");
        return threadPoolTaskScheduler;
    }

    /**
     * 初始化定时任务Map
     * key: 任务key
     * value: 执行接口实现
     */
//    @Bean(name = "scheduledTaskJobMap")
//    public Map<String, ScheduledTaskJob> scheduledTaskJobMap() {
//        return ScheduledTaskEnum.initScheduledTask();
//    }

}
