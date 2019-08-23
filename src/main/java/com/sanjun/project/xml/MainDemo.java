package com.sanjun.project.xml;

import com.sanjun.project.xml.impl.Dom4jDemo;

/**
 * Created by caozhixin on 2019-07-19 14:33
 */
public class MainDemo {
    public static void main(String[] args) {
        String fileName = "/Users/caozhixin/Downloads/new-cluster-conf/fair-scheduler.xml.day";
        Dom4jDemo dom4jDemo = new Dom4jDemo();
        dom4jDemo.parserXml(fileName);
    }
}
