package com.sanjun.project.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by caozhixin on 2019-07-29 16:12
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        String format = "HH:mm:ss";
        DateFormat df = DateFormat.getTimeInstance();
        System.out.println(df.format(new Date()));
//        Date nowTime = new SimpleDateFormat(format).parse(df.format(new Date()));
        Date nowTime = new SimpleDateFormat(format).parse("08:00:00");
        Date startTime = new SimpleDateFormat(format).parse("00:00:00");
        Date endTime = new SimpleDateFormat(format).parse("12:00:00");

        System.out.println(isEffectiveDate(nowTime, startTime, endTime));
    }

    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        }
        return false;
    }
}
