package com.gomore.gomorelibrary.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by Kennen on 2017/3/23.
 */
public class DateUtil {
    private final static SimpleDateFormat defaultDateFormat;
    public static String pattern = "yyyy-MM-dd";
    public static String pattern1 = "yyyy-MM-dd HH:mm:ss";
    public static String pattern2 = "yyyyMM";
    public static String pattern3 = "yyyy-MM";

    static {
        defaultDateFormat = new SimpleDateFormat(pattern1);
    }


    /**
     * 根据年月日转化成Date
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getDate(int year, int month, int day) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.YEAR, year);
        ca.set(Calendar.MONTH, month);
        ca.set(Calendar.DAY_OF_MONTH, day);
        Date d = ca.getTime();
        return d;
    }

    /**
     * 取得上一年时间值(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getLastYear() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.YEAR, -1);
        Date d = ca.getTime();
        String lastYear = DateUtil.formatDate(d, DateUtil.pattern1);
        return lastYear;
    }

    /**
     * 取得上一年时间值(yyyy-MM-dd)
     *
     * @return
     */
    public static String getShortLastYear() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.YEAR, -1);
        Date d = ca.getTime();
        String lastYear = DateUtil.formatDate(d, DateUtil.pattern);
        return lastYear;
    }

    /**
     * 取得上周时间值
     *
     * @return
     */
    public static Date getLastWeek() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.WEEK_OF_YEAR, -1);
        Date d = ca.getTime();
        return d;
    }

    /**
     * 根据传入日期返回星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 将Date根据SimpleDateFormat格式转化为字符串
     *
     * @param date
     * @param pattern 转化格式 为空时：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (pattern == null)
            return defaultDateFormat.format(date);
        else
            return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将时间字符串根据SimpleDateFormat格式转化为Date
     *
     * @param input
     * @param pattern 转化格式 为空时：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseDate(String input, String pattern)
            throws ParseException {
        if (pattern == null)
            return new Date(defaultDateFormat.parse(input).getTime());
        else
            return new Date(new SimpleDateFormat(pattern).parse(input)
                    .getTime());
    }

    /**
     * 获取今天日期的字符串（yyyy-MM-dd）
     *
     * @return
     */
    public static String getToday() {
        Calendar cal = Calendar.getInstance();
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
                .getTime());
        return yesterday;
    }

    /**
     * 获取今天日期的字符串（yyyy-MM-dd HH:mm:ss）
     */
    public static String getTodayStr() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(pattern1);
        String todyStr = df.format(date);
        return todyStr;
    }

    /**
     * 获取昨天日期的字符串（yyyy-MM-dd）
     *
     * @return
     */
    public static String getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
                .getTime());
        return yesterday;
    }

    /**
     * 获取上一天的日期值
     *
     * @param inputDate 输入日期
     * @param pattern   转化格式 为空时：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getLastDay(String inputDate, String pattern) {
        try {
            if (pattern == null) {
                pattern = pattern1;
            }
            Date date = parseDate(inputDate, pattern);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            return formatDate(calendar.getTime(), pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取下一天的日期值
     *
     * @param inputDate 输入日期
     * @param pattern   转化格式 为空时：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getNextDay(String inputDate, String pattern) {
        try {
            Date date = parseDate(inputDate, pattern);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            return formatDate(calendar.getTime(), pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前月份的字符串（yyyyMM）
     *
     * @return
     */
    public static String getMonth() {
        Calendar cal = Calendar.getInstance();
        String yesterday = new SimpleDateFormat(pattern2).format(cal.getTime());
        return yesterday;
    }

    /**
     * 获取当前月份的字符串（yyyy-MM）
     *
     * @return
     */
    public static String getMonthDivide() {
        Calendar cal = Calendar.getInstance();
        String yesterday = new SimpleDateFormat(pattern3).format(cal.getTime());
        return yesterday;
    }


    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        if (strTime == null) {
            strTime = getTodayStr();
        }
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }
}
