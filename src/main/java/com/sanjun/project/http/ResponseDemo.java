package com.sanjun.project.http;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by caozhixin on 2019-07-26 15:02
 */
public class ResponseDemo {
    public void demo(HttpServletResponse response) {
        File file = new File("filePath");
        if (file.exists()) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/xml;charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
