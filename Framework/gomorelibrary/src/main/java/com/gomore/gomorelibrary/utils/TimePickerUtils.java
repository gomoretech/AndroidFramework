package com.gomore.gomorelibrary.utils;

import android.content.Context;

import com.gomore.gomorelibrary.view.pickerview.TimePickerView;

/**
 * 仿IOS模式的时间选择框
 * <p>
 * Created by Kennen on 2017/3/23.
 */
public class TimePickerUtils {

    private static TimePickerView mTimePickerView = null;

    /**
     * 显示时间选择对话框
     *
     * @param context
     * @param selectListener
     */
    public static void showTimeSelect(Context context, TimePickerView.OnTimeSelectListener selectListener) {
        if (mTimePickerView != null && !mTimePickerView.isShowing()) {
            mTimePickerView = null;
        }
        if (mTimePickerView == null) {
            mTimePickerView = new TimePickerView.Builder(context, selectListener)
                    .setLabel("年", "月", "日", "时", "分", "秒")
                    .setType(TimePickerView.Type.ALL)
                    .build();
        }
        if (!mTimePickerView.isShowing()) {
            mTimePickerView.show();
        }
    }

    /**
     * 显示时间选择对话框
     *
     * @param context
     * @param type           // 五种选择模式，ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
     *                       年月日时分秒，年月日，时分，月日时分，年月
     * @param selectListener
     */
    public static void showTimeSelect(Context context, TimePickerView.Type type, TimePickerView.OnTimeSelectListener selectListener) {
        if (mTimePickerView != null && !mTimePickerView.isShowing()) {
            mTimePickerView = null;
        }
        if (mTimePickerView == null) {
            mTimePickerView = new TimePickerView.Builder(context, selectListener)
                    .setLabel("年", "月", "日", "时", "分", "秒")
                    .setType(type)
                    .build();
        }
        if (!mTimePickerView.isShowing()) {
            mTimePickerView.show();
        }
    }

    /**
     * 显示时间选择对话框
     *
     * @param context
     * @param type              // 五种选择模式，ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
     *                          年月日时分秒，年月日，时分，月日时分，年月
     * @param confirmCancelable //点击确认按钮是否取消该对话框(用于所选时间的校验)
     * @param selectListener
     */
    public static void showTimeSelect(Context context, TimePickerView.Type type, boolean confirmCancelable,
                                      TimePickerView.OnTimeSelectListener selectListener) {
        if (mTimePickerView != null && !mTimePickerView.isShowing()) {
            mTimePickerView = null;
        }
        if (mTimePickerView == null) {
            mTimePickerView = new TimePickerView.Builder(context, selectListener)
                    .setLabel("年", "月", "日", "时", "分", "秒")
                    .setType(type)
                    .setConfirmCancelable(confirmCancelable)
                    .build();
        }
        if (!mTimePickerView.isShowing()) {
            mTimePickerView.show();
        }
    }
}
