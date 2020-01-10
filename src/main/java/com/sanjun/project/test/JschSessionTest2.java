package com.sanjun.project.test;

import com.jcraft.jsch.JSchException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by caozhixin on 2020-01-08 16:16
 */
@RestController
public class JschSessionTest2 {
    @RequestMapping("/test2")
    public void testJsch2() {
        System.out.println("start3");
        JschDemo jschDemo = JschDemo.getInstance();
        System.out.println(jschDemo.getSession());
        jschDemo.connChannelExec();
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
        System.out.println("end3");
    }
}
