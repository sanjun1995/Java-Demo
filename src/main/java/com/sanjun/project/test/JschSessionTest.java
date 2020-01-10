package com.sanjun.project.test;

import com.jcraft.jsch.JSchException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * Created by caozhixin on 2020-01-08 11:57
 */
@Configuration
@EnableScheduling
@RestController
public class JschSessionTest {

    @Scheduled(cron = "*/5 * * * * ?")
    public void testJsch() {
        JschDemo jschDemo = JschDemo.getInstance();
        System.out.println(jschDemo.getSession());
        jschDemo.connChannelExec();
        System.out.println(jschDemo.getChannelExec());
        String msg = "";
        try {
            msg = jschDemo.execShell("curl -XGET http://192.168.9.201:9200/_cluster/health\\?pretty|grep status");
//            msg = jSchUtil.execShell("curl -XGET http://192.168.9.201:9200/_cat/nodes\\?v");
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        jschDemo.closeChannelExec();
//        jschDemo.closeSession();
//
//        System.out.println(jschDemo.getSession());
//        jschDemo.closeSession();
    }

    @RequestMapping("/test")
    public void testJsch2() {
        JschDemo jschDemo = JschDemo.getInstance();
        System.out.println("test:" + jschDemo.getSession());
        jschDemo.connChannelExec();
        String msg = "";
        try {
            msg = jschDemo.execShell("curl -XGET http://192.168.9.201:9200/_cluster/health\\?pretty|grep status");
//            msg = jSchUtil.execShell("curl -XGET http://192.168.9.201:9200/_cat/nodes\\?v");
            System.out.println("test:" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        jschDemo.closeChannelExec();
//        jschDemo.closeSession();
    }

}
