package com.sanjun.project.quartz2.config;

import com.sanjun.project.quartz2.service.Task;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by caozhixin on 2019-11-04 14:53
 */
@Slf4j
//@Configuration
public class QuartzConfig {
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(Task task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false);
        jobDetail.setName("srd-scheduled");
        jobDetail.setGroup("srd");
        jobDetail.setTargetObject(task);
        jobDetail.setTargetMethod("sayHello");
        log.info("jobDetail 初始化成功！");
        return jobDetail;
    }

    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail.getObject());
        trigger.setCronExpression("*/5 * * * * ?");
        trigger.setName("srd-scheduled");
        log.info("jobTrigger 初始化成功！");
        return trigger;
    }

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger trigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        // 用于quartz集群，QuartzScheduler启动时更新已存在的job
        factoryBean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        factoryBean.setStartupDelay(1);
        // 注册触发器
        factoryBean.setTriggers(trigger);
        log.info("scheduler 初始化成功！");
        return factoryBean;
    }
}
