package com.gomore.gomorelibrary.utils;

import android.content.Context;
import android.view.View;

import com.gomore.gomorelibrary.view.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.gomore.gomorelibrary.utils.DateUtil.pattern;

/**
 * 仿IOS模式的时间选择框
 * <p>
 * Created by Kennen on 2017/3/23.
 */
public class TimePickerUtils {

    /**
     * 选择时间控件（点击确定后自动隐藏）
     */
    public static void setTimeCancelable(Context context, final TimeSelectListener listener) {
        //new boolean[]{true, true, true, false, false, false}  年/月/日/时/分/秒
        setTimeCancelable(context, new boolean[]{true, true, true, false, false, false}, null, pattern, listener);
    }

    /**
     * 选择时间控件（点击确定后自动隐藏）
     */
    public static void setTimeCancelable(Context context, boolean[] type, String title,
                                         final String pattern, final TimeSelectListener listener) {
        setTimeCancelable(context, type, title, null, pattern, listener);
    }

    /**
     * 选择时间控件（点击确定后自动隐藏）
     */
    public static void setTimeCancelable(Context context, boolean[] type, String title,
                                         Calendar date, final String pattern, final TimeSelectListener listener) {
        setTimeCancelable(context, type, title, date, null, null, pattern, listener);
    }


    /**
     * 选择时间控件（点击确定后自动隐藏）
     *
     * @param context
     * @param type     日期显示格式
     * @param title    标题
     * @param pattern  获得的日期类型
     * @param listener
     */
    public static void setTimeCancelable(Context context, boolean[] type, String title,
                                         Calendar date, Calendar startDate, Calendar endDate, final String pattern, final TimeSelectListener listener) {
        TimePickerView timePickerView = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                listener.selectDate(date);
                SimpleDateFormat format;
                if (pattern != null) {
                    format = new SimpleDateFormat(pattern);
                } else {
                    format = new SimpleDateFormat("yyyy-MM-dd");
                }
                listener.selectDateString(format.format(date));
            }
        }).setType(type)//设置显示方式
                .isCyclic(false)//是否循环滚动(默认false)
                .setDate(date)
                .setTitleText(title)
                .setRangDate(startDate, endDate)
                .build();
        timePickerView.show();
    }

    public interface TimeSelectListener {
        void selectDate(Date date);

        void selectDateString(String date);
    }
}
