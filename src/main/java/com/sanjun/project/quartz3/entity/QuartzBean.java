package com.sanjun.project.quartz3.entity;

import lombok.Data;

/**
 * Created by caozhixin on 2019-11-06 14:40
 */
@Data
public class QuartzBean {
    /** 任务id */
    private String  id;

    /** 任务名称 */
    private String jobName;

    /** 任务执行类 */
    private String jobClass;

    /** 任务状态 启动还是暂停*/
    private Integer status;

    /** 任务运行时间表达式 */
    private String cronExpression;
}
