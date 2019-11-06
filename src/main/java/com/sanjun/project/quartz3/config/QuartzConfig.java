package com.sanjun.project.quartz3.config;

import com.sanjun.project.quartz3.entity.MyTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by caozhixin on 2019-11-06 14:35
 */
//@Configuration
public class QuartzConfig {
    // 指定具体的定时任务类
    @Bean
    public JobDetail uploadTaskDetail() {
        return JobBuilder.newJob(MyTask.class).withIdentity("MyTask").storeDurably().build();
    }

    @Bean
    public Trigger uploadTaskTrigger() {
        //TODO 这里设定执行方式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        // 返回任务触发器
        return TriggerBuilder.newTrigger().forJob(uploadTaskDetail())
                .withIdentity("MyTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
