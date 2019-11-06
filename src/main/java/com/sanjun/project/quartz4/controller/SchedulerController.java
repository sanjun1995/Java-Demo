package com.sanjun.project.quartz4.controller;

import com.sanjun.project.quartz4.service.impl.QuartzManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by caozhixin on 2019-11-06 16:37
 */
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {
    @Autowired
    QuartzManager quartzManager;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    public String start(@RequestParam(name = "id", defaultValue = "") String id) {
        try {
            quartzManager.start(id);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "启动成功";
    }
}
