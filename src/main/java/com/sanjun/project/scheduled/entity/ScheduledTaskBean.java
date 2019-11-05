package com.sanjun.project.scheduled.entity;

import lombok.Data;

/**
 * Created by caozhixin on 2019-11-04 18:04
 */
@Data
public class ScheduledTaskBean {
    /**
     * 任务key值 唯一
     */
    private String taskKey;
    /**
     * 任务描述
     */
    private String taskDesc;
    /**
     * 任务表达式
     */
    private String taskCron;

    /**
     * 程序初始化是否启动 1 是 0 否
     */
    private Integer initStartFlag;

    /**
     * 当前是否已启动
     */
    private boolean startFlag;
}
