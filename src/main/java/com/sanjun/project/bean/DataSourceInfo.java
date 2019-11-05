package com.sanjun.project.bean;

/**
 * Created by caozhixin on 2019-08-30 11:55
 */
public class DataSourceInfo {
    public String connUrl;
    public String userName;
    public String password;

    public String toString(){

        return "(JcbcUrl:"+connUrl+", user:"+userName+", password:"+password+")";
    }

    public String getConnUrl() {
        return connUrl;
    }

    public void setConnUrl(String connUrl) {
        this.connUrl = connUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
