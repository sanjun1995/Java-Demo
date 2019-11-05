package com.sanjun.project.scheduled.service.impl;

import com.sanjun.project.scheduled.service.ScheduledTaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by caozhixin on 2019-11-04 17:55
 */
@Service
public class ScheduledTask01 implements ScheduledTaskJob {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask01.class);

    @Override
    public void run() {
        logger.info("ScheduledTask => 01  run  当前线程名称 {} ", Thread.currentThread().getName());
    }
}
