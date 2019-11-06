package com.sanjun.project.quartz4.mapper;

import com.sanjun.project.quartz4.entity.CronEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by caozhixin on 2019-11-06 16:09
 */
@Mapper
public interface CronMapper {
    @Select("select * from cron_table where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "cron", column = "cron"),
            @Result(property = "quarzName", column = "quarz_name"),
            @Result(property = "schedulerClass", column = "scheduler_class"),
            @Result(property = "time", column = "time")
    })
    CronEntity load(String id);

    @Insert("insert into cron_table(id, userId, cron, quartzName, schedulerClass, time)" +
            "values(#{id}, #{user_id}, #{cron}, #{quartz_name}, #{scheduler_class}, #{time})")
    void insert(CronEntity cronEntity);

    @Select("select * from cron_table")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "cron", column = "cron"),
            @Result(property = "quarzName", column = "quarz_name"),
            @Result(property = "schedulerClass", column = "scheduler_class"),
            @Result(property = "time", column = "time")
    })
    List<CronEntity> queryAll();

    @Update("update cron_table set cron = #{1} where id = #{0}")
    void updateCron(String id, String cron);
}
