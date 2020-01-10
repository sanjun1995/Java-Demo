package com.sanjun.project.test;

import com.jcraft.jsch.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by caozhixin on 2020-01-08 11:58
 */
@Data
public class JschDemo {

    private static JschDemo instance = new JschDemo();

    private JschDemo() {}

    private Session session;
    private ChannelExec channelExec;
    private static Logger logger = LoggerFactory.getLogger(JschDemo.class);

    public static JschDemo getInstance() {
        return instance;
    }

    public String execShell(String command) throws IOException, JSchException {
        channelExec.setCommand(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
        channelExec.connect();
        String result = "";
        String msg = "";
        while ((msg = in.readLine()) != null) {
            result += msg + "\n";
        }
        return result;
    }

    public void connSession(String host, String username, String prvkey) {
        logger.info("connSession => host: {}, username: {}, prvkey: {}", host, username, prvkey);
        JSch jSch = new JSch();
        try {
//            jSch.addIdentity(prvkey);
            session = jSch.getSession(username, host);
            session.setPassword(prvkey);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            logger.error("connect failed, error message: {}", e);
        }
    }

    public void closeSession() {
        session.disconnect();
    }

    public void connChannelExec() {
        try {
            channelExec = (ChannelExec) session.openChannel("exec");
        } catch (JSchException e) {
            logger.error("connChannelExec failed, error message: {}", e);
        }
    }

    public void closeChannelExec() {
        channelExec.disconnect();
    }
}
