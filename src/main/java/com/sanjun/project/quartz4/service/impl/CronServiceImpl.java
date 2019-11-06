package com.sanjun.project.quartz4.service.impl;

import com.sanjun.project.quartz4.entity.CronEntity;
import com.sanjun.project.quartz4.mapper.CronMapper;
import com.sanjun.project.quartz4.service.ICronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by caozhixin on 2019-11-06 16:19
 */
@Service
public class CronServiceImpl implements ICronService {

    @Autowired
    private CronMapper cronMapper;

    @Override
    public CronEntity findByCronId(String id) {
        return this.cronMapper.load(id);
    }

    @Override
    public void update(CronEntity cronEntity) {
        this.cronMapper.insert(cronEntity);
    }

    @Override
    public List<CronEntity> findAll() {
        return this.cronMapper.queryAll();
    }
}
