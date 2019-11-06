package com.sanjun.project.quartz4.service;

import com.sanjun.project.quartz4.entity.CronEntity;

import java.util.List;

/**
 * Created by caozhixin on 2019-11-06 16:16
 */
public interface ICronService {

    CronEntity findByCronId(String id );

    void update(CronEntity cronEntity);

    List<CronEntity> findAll();
}
