package com.sanjun.project.quartz4.service.impl;

import com.sanjun.project.quartz4.entity.CronEntity;
import com.sanjun.project.quartz4.entity.JobTask;
import com.sanjun.project.quartz4.service.ICronService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Created by caozhixin on 2019-11-06 16:23
 */
@Configuration
public class QuartzManager {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ICronService iCronService;

    public void start(String id) throws SchedulerException {
        CronEntity cronEntity = iCronService.findByCronId(id);
        if(cronEntity!= null){
            startJob(scheduler, cronEntity.getQuarzName(), cronEntity.getCron());
        }
    }

    private void startJob(Scheduler scheduler,String taskName,String cron) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(JobTask.class)
                .withIdentity(taskName, "group1")
                .usingJobData("taskName", taskName)
                .build();
        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        // CronTrigger表达式触发器 继承于Trigger
        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(taskName, "group1")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 获取Job信息
     *
     * @param name
     * @param group
     * @return
     * @throws SchedulerException
     */
    public String getJobInfo(String name, String group) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                scheduler.getTriggerState(triggerKey).name());
    }

    /**
     * 修改某个任务的执行时间
     *
     * @param name
     * @param group
     * @param time
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 暂停所有任务
     *
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 暂停某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复所有任务
     *
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除某个任务
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        scheduler.deleteJob(jobKey);
    }
}
