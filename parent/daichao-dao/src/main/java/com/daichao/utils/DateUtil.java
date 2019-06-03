package com.daichao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
     * date 按指定format转成 String
     *
     * @param date
     * @return
     */
    public static String getStringDate(Date date, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * date 按指定format转成指定格式 date
     *
     * @param date
     * @return
     */
    public static Date getStringDate(Date date,String beforFormat, String afterFormat) {
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat(afterFormat);
            return formatter.parse(getStringDate(date, beforFormat)) ;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 获取指定日期多少天之前或之后日期 负数：多少天之前 正数多少天之后
     * @param date
     * @param days
     * @return
     */
    public static Date getDateBeforeOrAfterDays(Date date,int days) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, days);
    	return calendar.getTime();
    }
    
    /**
     * String date 按指定format转成 Date
     *
     * @param String
     * @return
     */
    public static Date getDate(String time,String format) {
    	try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.parse(time);
        } catch (Exception e) {
            return null;
        }
    }
}
