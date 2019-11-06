package com.sanjun.project.quartz4.entity;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by caozhixin on 2019-11-06 16:27
 */
public class JobTask implements Job {
    private Logger logger = LoggerFactory.getLogger(JobTask.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String taskName = dataMap.getString("taskName");
        logger.info("JobTask task start execute. key: {}, taskName: {}", key, taskName);
    }
}
