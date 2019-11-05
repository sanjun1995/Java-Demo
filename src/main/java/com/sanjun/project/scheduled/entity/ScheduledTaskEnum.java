package com.sanjun.project.scheduled.entity;

import com.sanjun.project.scheduled.service.ScheduledTaskJob;
import com.sanjun.project.scheduled.service.impl.ScheduledTask01;
import com.sanjun.project.scheduled.service.impl.ScheduledTask02;
import com.sanjun.project.scheduled.service.impl.ScheduledTask03;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务枚举值
 * 注：需要与数据库保持一致
 * Created by caozhixin on 2019-11-04 17:51
 */
public enum ScheduledTaskEnum {
    TASK_01("scheduledTask01", new ScheduledTask01()),

    TASK_02("scheduledTask02", new ScheduledTask02()),

    TASK_03("scheduledTask01", new ScheduledTask03());

    // 定时任务key
    private String taskKey;

    // 定时任务 执行实现类
    private ScheduledTaskJob scheduledTaskJob;

    ScheduledTaskEnum(String taskKey, ScheduledTaskJob scheduledTaskJob) {
        this.taskKey = taskKey;
        this.scheduledTaskJob = scheduledTaskJob;
    }

    /**
     * 初始化所有任务
     */
    public static Map<String, ScheduledTaskJob> initScheduledTask() {
        if (ScheduledTaskEnum.values().length < 0) {
            return new ConcurrentHashMap<>();
        }
        Map<String, ScheduledTaskJob> scheduledTaskJobMap = new ConcurrentHashMap<>();
        for (ScheduledTaskEnum taskEnum : ScheduledTaskEnum.values()) {
            scheduledTaskJobMap.put(taskEnum.taskKey, taskEnum.scheduledTaskJob);
        }
        return scheduledTaskJobMap;
    }
}
