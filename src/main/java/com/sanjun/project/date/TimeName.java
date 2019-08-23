package com.sanjun.project.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by caozhixin on 2019-08-05 14:42
 */
public class TimeName {
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        System.out.println(dateName);
    }
}
