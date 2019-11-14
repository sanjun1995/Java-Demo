package com.sanjun.project.jsch;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by caozhixin on 2019-07-04 16:31
 */
public class JSchUtil {

    private Session session;
    private ChannelSftp channelSftp;
    private ChannelExec channelExec;
    private static Logger logger = LoggerFactory.getLogger(JSchUtil.class);

    public void getFile(String tempFilePath, String targetFilePath) throws IOException, SftpException {
        OutputStream outputStream = new FileOutputStream(tempFilePath);
        channelSftp.get(targetFilePath, outputStream);
        outputStream.close();
    }

    public void putFile(String tempFilePath, String targetFilePath) throws IOException, SftpException {
        InputStream inputStream = new FileInputStream(tempFilePath);
        channelSftp.put(inputStream, targetFilePath);
        inputStream.close();
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
            jSch.addIdentity(prvkey);
            session = jSch.getSession(username, host);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            logger.error("connect failed, error message: {}", e);
        }
    }

    public void closeSession() {
        session.disconnect();
    }

    public void connChannelSftp() {
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
        } catch (JSchException e) {
            logger.error("connChannelSftp failed, error message: {}", e);
        }
    }

    public void closeChannelSft() {
        channelSftp.disconnect();
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public ChannelSftp getChannelSftp() {
        return channelSftp;
    }

    public void setChannelSftp(ChannelSftp channelSftp) {
        this.channelSftp = channelSftp;
    }

    public ChannelExec getChannelExec() {
        return channelExec;
    }

    public void setChannelExec(ChannelExec channelExec) {
        this.channelExec = channelExec;
    }
}
