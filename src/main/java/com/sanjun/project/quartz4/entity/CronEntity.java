package com.sanjun.project.quartz4.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by caozhixin on 2019-11-06 16:08
 */
@Data
public class CronEntity {
    private String id;

    private String userId; //用户标识

    private String cron; //表达式

    private String quarzName; //任务名称

    private String schedulerClass;//定时任务类

    private Date time;
}
