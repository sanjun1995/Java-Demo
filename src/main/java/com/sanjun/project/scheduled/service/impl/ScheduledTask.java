package com.sanjun.project.scheduled.service.impl;

import com.sanjun.project.scheduled.service.ScheduledTaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by caozhixin on 2019-11-04 17:55
 */

public class ScheduledTask implements ScheduledTaskJob {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    private String taskName;

    public ScheduledTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        logger.info(this.taskName + " =>  run  当前线程名称 {} ", Thread.currentThread().getName());
    }
}
