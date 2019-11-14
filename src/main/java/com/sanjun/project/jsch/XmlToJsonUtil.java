package com.sanjun.project.jsch;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * Created by caozhixin on 2019-07-19 19:19
 */
public class XmlToJsonUtil {
    public static String parserXml(String fileName) throws DocumentException {
        int index = 1;
        StringBuilder jsonText = new StringBuilder("[{");
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(inputXml);
        Element root = document.getRootElement();
        Element rootQueue = root.element("queue");
        jsonText.append("\"key\" : \"").append(rootQueue.attributeValue("name")).append("-").append(index).append("\", \"queue\": \"")
                .append(rootQueue.attributeValue("name")).append("\", ")
                .append("\"name\": \"\", \"value\": \"\", \"children\": [");
        jsonText = traversingXMLByRecursive(rootQueue, jsonText, index);
        jsonText.append("]}]");

        return jsonText.toString().replaceAll("},]", "}]");
    }

    private static StringBuilder traversingXMLByRecursive(Element queue, StringBuilder jsonText, int index) {
        index = index * 10;
        for (Iterator i = queue.elementIterator(); i.hasNext();) {
            Element element = (Element) i.next();
            String elementName = element.getName();
            if (!"queue".equals(elementName) && !"minResources".equals(elementName) && !"maxResources".equals(elementName)) {
                continue;
            }
            index++;
            if ("queue".equals(elementName)) {
                jsonText.append("{\"key\": \"").append(queue.attributeValue("name")).append("-").append(index).append("\", \"queue\": \"")
                        .append(element.attributeValue("name")).append("\", ")
                        .append("\"name\": \"\", \"value\": \"\", \"children\": [");
                jsonText = traversingXMLByRecursive(element, jsonText, index);
                jsonText.append("]},");
            } else {
                jsonText.append("{\"key\": \"").append(queue.attributeValue("name")).append("-").append(index).append("\", \"queue\": \"\", ")
                        .append("\"name\": \"").append(elementName).append("\", \"value\": \"")
                        .append(element.getText()).append("\"},");
            }
        }
        return jsonText;
    }
}
