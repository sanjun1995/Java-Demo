package com.sanjun.project.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created by caozhixin on 2020-01-08 14:54
 */
@Component
@Order(value = 1)
public class JschRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(JschRunner.class);

    /**
     * 程序启动完毕后，需要自启的任务
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info(">>>>>项目启动完毕，开启需要自启的任务 开始！<<<<<");
        JschDemo jschDemo = JschDemo.getInstance();
        jschDemo.connSession("127.0.0.1", "caozhixin", "sanjun1995");
        System.out.println("one :" + jschDemo.getSession());
        logger.info(">>>>>项目启动完毕，开启需要自启的任务 完毕！<<<<<");
    }
}
