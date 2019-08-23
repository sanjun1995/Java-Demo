package com.sanjun.project.xml;

/**
 * Created by caozhixin on 2019-07-19 14:21
 *
 * 定义XML文档解析的接口
 */
public interface XmlDocument {

    /**
     * 解析XML文档
     *
     * @param fileName
     *        文件全路径名称
     */
    public void parserXml(String fileName);
}
