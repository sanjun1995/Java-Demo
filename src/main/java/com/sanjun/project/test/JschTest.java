package com.sanjun.project.test;

import com.jcraft.jsch.JSchException;
import com.sanjun.project.jsch.JSchUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caozhixin on 2019-11-22 15:27
 */
public class JschTest {
    public static void main(String[] args) {
        List<TTT> ttts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ttts.add(new TTT(i));
        }
        for (TTT ttt : ttts) {
            ttt.setMsg(test());
        }
        for (TTT ttt : ttts) {
            System.out.println(ttt);
        }
    }

    private static String test() {
        JSchUtil jSchUtil = new JSchUtil();
        jSchUtil.connSession("127.0.0.1", "caozhixin", "sanjun1995");
        jSchUtil.connChannelExec();
        String msg = "";
        try {
            msg = jSchUtil.execShell("curl -XGET http://192.168.9.201:9200/_cat/nodes\\?v");
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        jSchUtil.closeChannelExec();
        jSchUtil.closeSession();
        return msg;
    }
}
