package com.sanjun.project.jsch;

import com.alibaba.fastjson.JSONArray;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Iterator;

/**
 * Created by caozhixin on 2019-07-24 16:11
 */
public class JsonToXmlUtil {

    private static String key = null;
    private static String text = null;
    private static JSONArray jsonArray = null;

    public static void parserJsonToXml(String filePath, String configContent) throws DocumentException, IOException {
        int index = 1;
        File inputXml = new File(filePath);
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputXml);
        Element root = document.getRootElement();
        Element rootQueue = root.element("queue");

        // JSONArray
        jsonArray = JSONArray.parseArray(configContent);
        for (int i = 0; i < jsonArray.size(); i++) {
            key = jsonArray.getJSONObject(i).get("key").toString();
            text = jsonArray.getJSONObject(i).get("newValue").toString();
            traversingXMLByRecursive(rootQueue, index);
        }
        saveDocument(document, new File(filePath));
    }

    private static void saveDocument(Document document, File xmlFile) throws IOException {
        Writer osWriter = new OutputStreamWriter(new FileOutputStream(xmlFile));
        OutputFormat format = OutputFormat.createPrettyPrint();

        XMLWriter xmlWriter = new XMLWriter(osWriter, format);
        xmlWriter.write(document);
        xmlWriter.flush();
        xmlWriter.close();
    }

    private static void traversingXMLByRecursive(Element queue, int index) {
        index = index * 10;
        for (Iterator i = queue.elementIterator(); i.hasNext();) {
            Element element = (Element) i.next();
            String elementName = element.getName();
            if (!"queue".equals(elementName) && !"minResources".equals(elementName) && !"maxResources".equals(elementName)) {
                continue;
            }
            index++;
            String elementKey = queue.attributeValue("name") + "-" + index;
            if (key.equals(elementKey)) {
                element.setText(text);
            }
            if ("queue".equals(element.getName())) {
                traversingXMLByRecursive(element, index);
            }
        }
    }
}
