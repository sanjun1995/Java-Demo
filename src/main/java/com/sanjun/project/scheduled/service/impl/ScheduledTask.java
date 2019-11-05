package com.sanjun.project.scheduled.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Created by caozhixin on 2019-11-04 14:51
 */
@Slf4j
@Configuration
@Component
@EnableScheduling
public class ScheduledTask {
    public void sayHello() {
        log.info("Hello world, i'm the king of the world~~~");
    }
}
