package com.gomore.gomorelibrary.view.pickerview;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gomore.gomorelibrary.R;
import com.gomore.gomorelibrary.view.pickerview.view.BasePickerView;
import com.gomore.gomorelibrary.view.pickerview.view.WheelTime;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间选择器
 * Created by Sai on 15/11/22.
 * Updated by XiaoSong on 2017-2-22.
 */
public class TimePickerView extends BasePickerView implements View.OnClickListener {

    public enum Type {
        ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
    } // 五种选择模式，年月日时分秒，年月日，时分，月日时分，年月

    WheelTime wheelTime; //自定义控件
    private Button btnSubmit, btnCancel; //确定、取消按钮
    private TextView tvTitle;//标题
    private OnTimeSelectListener timeSelectListener;//回调接口
    private int gravity = Gravity.CENTER;//内容显示位置 默认居中
    private Type type;// 显示类型

    private String Str_Submit;//确定按钮字符串
    private String Str_Cancel;//取消按钮字符串
    private String Str_Title;//标题字符串

    private int Color_Submit;//确定按钮颜色
    private int Color_Cancel;//取消按钮颜色
    private int Color_Title;//标题颜色

    private int Color_Background_Wheel;//滚轮背景颜色
    private int Color_Background_Title;//标题背景颜色

    private int Size_Submit_Cancel;//确定取消按钮大小
    private int Size_Title;//标题字体大小
    private int Size_Content;//内容字体大小

    private Date date;//当前选中时间
    private int startYear;//开始年份
    private int endYear;//结尾年份
    private boolean cyclic;//是否循环
    private boolean cancelable;//是否能取消

    private String label_year, label_month, label_day, label_hours, label_mins, label_seconds;

    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";

    //构造方法
    public TimePickerView(Builder builder) {
        super(builder.context);
        this.timeSelectListener = builder.timeSelectListener;
        this.gravity = builder.gravity;
        this.type = builder.type;
        this.Str_Submit = builder.Str_Submit;
        this.Str_Cancel = builder.Str_Cancel;
        this.Str_Title = builder.Str_Title;
        this.Color_Submit = builder.Color_Submit;
        this.Color_Cancel = builder.Color_Cancel;
        this.Color_Title = builder.Color_Title;
        this.Color_Background_Wheel = builder.Color_Background_Wheel;
        this.Color_Background_Title = builder.Color_Background_Title;
        this.Size_Submit_Cancel = builder.Size_Submit_Cancel;
        this.Size_Title = builder.Size_Title;
        this.Size_Content = builder.Size_Content;
        this.startYear = builder.startYear;
        this.endYear = builder.endYear;
        this.date = builder.date;
        this.cyclic = builder.cyclic;
        this.cancelable = builder.cancelable;
        this.label_year = builder.label_year;
        this.label_month = builder.label_month;
        this.label_day = builder.label_day;
        this.label_hours = builder.label_hours;
        this.label_mins = builder.label_mins;
        this.label_seconds = builder.label_seconds;

        initView(builder.context);
    }


    //建造器
    public static class Builder {

        private Context context;
        private OnTimeSelectListener timeSelectListener;

        private Type type = Type.ALL;//显示类型 默认全部显示
        private int gravity = Gravity.CENTER;//内容显示位置 默认居中

        private String Str_Submit;//确定按钮文字
        private String Str_Cancel;//取消按钮文字
        private String Str_Title;//标题文字

        private int Color_Submit;//确定按钮颜色
        private int Color_Cancel;//取消按钮颜色
        private int Color_Title;//标题颜色

        private int Color_Background_Wheel;//滚轮背景颜色
        private int Color_Background_Title;//标题背景颜色

        private int Size_Submit_Cancel = 17;//确定取消按钮大小
        private int Size_Title = 18;//标题字体大小
        private int Size_Content = 18;//内容字体大小
        private Date date;//当前选中时间
        private int startYear;//开始年份
        private int endYear;//结尾年份
        private boolean cyclic = false;//是否循环
        private boolean cancelable = true;//是否能取消

        private String label_year, label_month, label_day, label_hours, label_mins, label_seconds;//单位

        //Required
        public Builder(Context context, OnTimeSelectListener listener) {
            this.context = context;
            this.timeSelectListener = listener;
        }

        //Option
        public Builder setType(Type type) {
            this.type = type;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setSubmitText(String Str_Cancel) {
            this.Str_Submit = Str_Cancel;
            return this;
        }

        public Builder setCancelText(String Str_Cancel) {
            this.Str_Cancel = Str_Cancel;
            return this;
        }

        public Builder setTitleText(String Str_Title) {
            this.Str_Title = Str_Title;
            return this;
        }

        public Builder setSubmitColor(int Color_Submit) {
            this.Color_Submit = Color_Submit;
            return this;
        }

        public Builder setCancelColor(int Color_Cancel) {
            this.Color_Cancel = Color_Cancel;
            return this;
        }

        public Builder setBgColor(int Color_Background_Wheel) {
            this.Color_Background_Wheel = Color_Background_Wheel;
            return this;
        }

        public Builder setTitleBgColor(int Color_Background_Title) {
            this.Color_Background_Title = Color_Background_Title;
            return this;
        }

        public Builder setTitleColor(int Color_Title) {
            this.Color_Title = Color_Title;
            return this;
        }

        public Builder setSubCalSize(int Size_Submit_Cancel) {
            this.Size_Submit_Cancel = Size_Submit_Cancel;
            return this;
        }

        public Builder setTitleSize(int Size_Title) {
            this.Size_Title = Size_Title;
            return this;
        }

        public Builder setContentSize(int Size_Content) {
            this.Size_Content = Size_Content;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setRange(int startYear, int endYear) {
            this.startYear = startYear;
            this.endYear = endYear;
            return this;
        }

        public Builder isCyclic(boolean cyclic) {
            this.cyclic = cyclic;
            return this;
        }

        public Builder setOutSideCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setLabel(String label_year, String label_month, String label_day, String label_hours, String label_mins, String label_seconds) {
            this.label_year = label_year;
            this.label_month = label_month;
            this.label_day = label_day;
            this.label_hours = label_hours;
            this.label_mins = label_mins;
            this.label_seconds = label_seconds;
            return this;
        }

        public TimePickerView build() {
            return new TimePickerView(this);
        }
    }


    private void initView(Context context) {

        LayoutInflater.from(context).inflate(R.layout.pickerview_time, contentContainer);

        //顶部标题
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        //确定和取消按钮
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel.setTag(TAG_CANCEL);

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        //设置文字
        btnSubmit.setText(TextUtils.isEmpty(Str_Submit) ? context.getResources().getString(R.string.pickerview_submit) : Str_Submit);
        btnCancel.setText(TextUtils.isEmpty(Str_Cancel) ? context.getResources().getString(R.string.pickerview_cancel) : Str_Cancel);
        tvTitle.setText(TextUtils.isEmpty(Str_Title) ? "" : Str_Title);//默认为空

        //设置文字颜色
        btnSubmit.setTextColor(Color_Submit == 0 ? context.getResources().getColor(R.color.pickerview_timebtn_nor) : Color_Submit);
        btnCancel.setTextColor(Color_Cancel == 0 ? context.getResources().getColor(R.color.pickerview_timebtn_nor) : Color_Cancel);
        tvTitle.setTextColor(Color_Title == 0 ? context.getResources().getColor(R.color.pickerview_topbar_title) : Color_Title);

        //设置文字大小
        btnSubmit.setTextSize(Size_Submit_Cancel);
        btnCancel.setTextSize(Size_Submit_Cancel);
        tvTitle.setTextSize(Size_Title);

        // 时间转轮 自定义控件
        LinearLayout timePickerView = (LinearLayout) findViewById(R.id.timepicker);

        RelativeLayout rv_top_bar = (RelativeLayout) findViewById(R.id.rv_topbar);
        rv_top_bar.setBackgroundColor(Color_Background_Title == 0 ? context.getResources().getColor(R.color.pickerview_bg_topbar) : Color_Background_Title);
        timePickerView.setBackgroundColor(Color_Background_Wheel == 0 ? context.getResources().getColor(R.color.bgColor_default) : Color_Background_Wheel);

        wheelTime = new WheelTime(timePickerView, type, gravity, Size_Content);

        if (startYear != 0 && endYear != 0 && startYear <= endYear) {
            setRange();
        }
        setTime();
        wheelTime.setLabels(label_year, label_month, label_day, label_hours, label_mins, label_seconds);
        setOutSideCancelable(cancelable);
        wheelTime.setCyclic(cyclic);
    }


    /**
     * 设置可以选择的时间范围, 要在setTime之前调用才有效果
     */
    private void setRange() {
        wheelTime.setStartYear(startYear);
        wheelTime.setEndYear(endYear);
    }

    /**
     * 设置选中时间,默认选中当前时间
     */
    private void setTime() {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            calendar.setTimeInMillis(System.currentTimeMillis());
        } else {
            calendar.setTime(date);
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        wheelTime.setPicker(year, month, day, hours, minute, seconds);
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
        } else {
            if (timeSelectListener != null) {
                try {
                    Date date = WheelTime.dateFormat.parse(wheelTime.getTime());
                    timeSelectListener.onTimeSelect(date, v);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            dismiss();
        }
    }

    public interface OnTimeSelectListener {
        void onTimeSelect(Date date, View v);
    }


}
