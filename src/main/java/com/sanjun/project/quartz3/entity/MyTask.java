package com.sanjun.project.quartz3.entity;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * Created by caozhixin on 2019-11-06 14:33
 */
public class MyTask extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // TODO 这里写定时任务的执行逻辑
        System.out.println("简单的定时任务执行时间："+new Date().toLocaleString());
    }
}
