package com.group0201.motobikelicenseexam.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String DDHHMM = "ddHHmm";
    public static String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
    public static String DD_MM_YYYY = "dd/MM/yyyy";
    public static String MM_YYYY = "MM/yyyy";
    public static String HH_MM_SS = "HH:mm:ss";
    public static String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String secondToMinuteAndSecond(int second) {
        if (second % 60 > 10) {
            return second / 60 + ":" + second % 60;
        } else {
            return second / 60 + ":0" + second % 60;
        }
    }

}
