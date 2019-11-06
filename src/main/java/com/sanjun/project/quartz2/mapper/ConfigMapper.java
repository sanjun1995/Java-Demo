package com.sanjun.project.quartz2.mapper;

import com.sanjun.project.quartz2.entity.Config;
import org.apache.ibatis.annotations.*;

/**
 * Created by caozhixin on 2019-11-04 15:28
 */
@Mapper
public interface ConfigMapper {
    @Select("select id, cron from config where id = #{id}")
    Config findOne(Integer id);
}
