package com.sanjun.project.xml.impl;


import java.io.File;
import java.util.Iterator;

import com.sanjun.project.xml.XmlDocument;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author caozhixin
 *
 * Dom4j 解析XML文档
 */
public class Dom4jDemo implements XmlDocument {

    public void parserXml(String fileName) {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(inputXml);
            Element root = document.getRootElement();
//            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
//                Element element = (Element) i.next();
//                System.out.println(element);
//            }

//            traversingXMLByFor(root);
            Element rootQueue = root.element("queue");
            System.out.println("=====================================================");
            System.out.println(rootQueue.getName() + ": " + rootQueue.attributeValue("name"));
            System.out.println("=====================================================");
            traversingXMLByRecursive(rootQueue);
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void traversingXMLByRecursive(Element queue) {
        for (Iterator i = queue.elementIterator(); i.hasNext();) {
            Element element = (Element) i.next();

            if ("queue".equals(element.getName())) {
                System.out.println("=====================================================");
                System.out.println(element.getName() + ": " + element.attributeValue("name"));
                System.out.println("=====================================================");
                traversingXMLByRecursive(element);
            } else {
                System.out.println(element.getName() + ": " + element.getText());
            }
        }
    }

    private void traversingXMLByFor(Element root) {
        Element rootQueue = root.element("queue");
        // 第一层
        System.out.println("=====================================================");
        System.out.println(rootQueue.getName() + ": " + rootQueue.attributeValue("name"));
        System.out.println("=====================================================");
        for (Iterator j = rootQueue.elementIterator(); j.hasNext();) {
            Element element = (Element) j.next();

            if ("queue".equals(element.getName())) {
                // 第二层
                System.out.println("=====================================================");
                System.out.println(element.getName() + ": " + element.attributeValue("name"));
                System.out.println("=====================================================");
                for (Iterator k = element.elementIterator(); k.hasNext();) {
                    Element ele = (Element) k.next();

                    if ("queue".equals(ele.getName())) {
                        // 第三层
                        System.out.println("=====================================================");
                        System.out.println(ele.getName() + ": " + ele.attributeValue("name"));
                        System.out.println("=====================================================");
                        for (Iterator l = ele.elementIterator(); l.hasNext(); ) {
                            // 第四层
                            Element e = (Element) l.next();
                            System.out.println(e.getName() + ": " + e.getText());
                        }
                    } else {
                        System.out.println(ele.getName() + ": " + ele.getText());
                    }
                }
            } else {
                System.out.println(element.getName() + ": " + element.getText());
            }
        }
    }


}