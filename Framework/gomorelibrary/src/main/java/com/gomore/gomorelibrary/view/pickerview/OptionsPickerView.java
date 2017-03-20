package com.gomore.gomorelibrary.view.pickerview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gomore.gomorelibrary.R;
import com.gomore.gomorelibrary.view.pickerview.view.BasePickerView;
import com.gomore.gomorelibrary.view.pickerview.view.WheelOptions;

import java.util.ArrayList;

/**
 * 条件选择器
 * Created by Sai on 15/11/22.
 */
public class OptionsPickerView<T> extends BasePickerView implements View.OnClickListener {

    WheelOptions<T> wheelOptions;

   /* private ArrayList<T> optionsItems;
    private ArrayList<ArrayList<T>> options2Items;
    private ArrayList<ArrayList<ArrayList<T>>> options3Items;*/

    private Button btnSubmit, btnCancel; //确定、取消按钮
    private TextView tvTitle;

    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";


    private OnOptionsSelectListener optionsSelectListener;

    private String Str_Submit;//确定按钮文字
    private String Str_Cancel;//取消按钮文字
    private String Str_Title;//标题文字

    private int Color_Submit;//确定按钮颜色
    private int Color_Cancel;//取消按钮颜色
    private int Color_Title;//标题颜色

    private int Color_Background_Wheel;//滚轮背景颜色
    private int Color_Background_Title;//标题背景颜色

    private int Size_Submit_Cancel;//确定取消按钮大小
    private int Size_Title;//标题文字大小
    private int Size_Content;//内容文字大小

    private boolean cancelable;//是否能取消
    private boolean linkage;//是否联动

    private String label1;//单位
    private String label2;
    private String label3;

    private boolean cyclic1;//是否循环
    private boolean cyclic2;
    private boolean cyclic3;

    private int option1;//默认选中项
    private int option2;
    private int option3;

    //构造方法
    public OptionsPickerView(Builder builder) {
        super(builder.context);
        this.optionsSelectListener = builder.optionsSelectListener;
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

        this.cyclic1 = builder.cyclic1;
        this.cyclic2 = builder.cyclic2;
        this.cyclic3 = builder.cyclic3;

        this.cancelable = builder.cancelable;
        this.linkage = builder.linkage;

        this.label1 = builder.label1;
        this.label2 = builder.label2;
        this.label3 = builder.label3;

        this.option1 = builder.option1;
        this.option2 = builder.option2;
        this.option3 = builder.option3;

        initView(builder.context);
    }


    //建造器
    public static class Builder {

        private Context context;
        private OnOptionsSelectListener optionsSelectListener;

        private String Str_Submit;//确定按钮文字
        private String Str_Cancel;//取消按钮文字
        private String Str_Title;//标题文字

        private int Color_Submit;//确定按钮颜色
        private int Color_Cancel;//取消按钮颜色
        private int Color_Title;//标题颜色

        private int Color_Background_Wheel;//滚轮背景颜色
        private int Color_Background_Title;//标题背景颜色

        private int Size_Submit_Cancel = 17;//确定取消按钮大小
        private int Size_Title = 18;//标题文字大小
        private int Size_Content = 18;//内容文字大小

        private boolean cancelable = true;//是否能取消
        private boolean linkage = true;//是否联动

        private String label1;
        private String label2;
        private String label3;

        private boolean cyclic1 = false;//是否循环，默认否
        private boolean cyclic2 = false;
        private boolean cyclic3 = false;

        private int option1;//默认选中项
        private int option2;
        private int option3;

        //Required
        public Builder(Context context, OnOptionsSelectListener listener) {
            this.context = context;
            this.optionsSelectListener = listener;
        }

        //Option

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

        public Builder setContentTextSize(int Size_Content) {
            this.Size_Content = Size_Content;
            return this;
        }


        public Builder setOutSideCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setLinkage(boolean linkage) {
            this.linkage = linkage;
            return this;
        }

        public Builder setLabels(String label1, String label2, String label3) {
            this.label1 = label1;
            this.label2 = label2;
            this.label3 = label3;
            return this;
        }

        public Builder setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
            this.cyclic1 = cyclic1;
            this.cyclic2 = cyclic2;
            this.cyclic3 = cyclic3;
            return this;
        }

        public Builder setSelectOptions(int option1) {
            this.option1 = option1;
            return this;
        }

        public Builder setSelectOptions(int option1, int option2) {
            this.option1 = option1;
            this.option2 = option2;
            return this;
        }

        public Builder setSelectOptions(int option1, int option2, int option3) {
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            return this;
        }


        public OptionsPickerView build() {
            return new OptionsPickerView(this);
        }
    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.pickerview_options, contentContainer);

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

        // ----转轮
        final LinearLayout optionsPicker = (LinearLayout) findViewById(R.id.optionspicker);

        RelativeLayout rv_top_bar = (RelativeLayout) findViewById(R.id.rv_topbar);
        rv_top_bar.setBackgroundColor(Color_Background_Title == 0 ? context.getResources().getColor(R.color.pickerview_bg_topbar) : Color_Background_Title);
        optionsPicker.setBackgroundColor(Color_Background_Wheel == 0 ? context.getResources().getColor(R.color.bgColor_default) : Color_Background_Wheel);

        wheelOptions = new WheelOptions(optionsPicker, linkage);
        wheelOptions.setTextContentSize(Size_Content);
        wheelOptions.setLabels(label1, label2, label3);
        wheelOptions.setCyclic(cyclic1, cyclic2, cyclic3);

        setOutSideCancelable(cancelable);
        tvTitle.setText(Str_Title);
        /*wheelOptions.setPicker(optionsItems, options2Items, options3Items, linkage);*/

    }

    public void setPicker(ArrayList<T> optionsItems) {
        wheelOptions.setPicker(optionsItems, null, null);
        wheelOptions.setCurrentItems(option1, option2, option3);
    }

    public void setPicker(ArrayList<T> options1Items, ArrayList<ArrayList<T>> options2Items) {
        wheelOptions.setPicker(options1Items, options2Items, null);
        wheelOptions.setCurrentItems(option1, option2, option3);
    }

    public void setPicker(ArrayList<T> options1Items,
                          ArrayList<ArrayList<T>> options2Items,
                          ArrayList<ArrayList<ArrayList<T>>> options3Items) {

        wheelOptions.setPicker(options1Items, options2Items, options3Items);
        wheelOptions.setCurrentItems(option1, option2, option3);
    }


    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (optionsSelectListener != null) {
                int[] optionsCurrentItems = wheelOptions.getCurrentItems();
                optionsSelectListener.onOptionsSelect(optionsCurrentItems[0], optionsCurrentItems[1], optionsCurrentItems[2], v);
            }
            dismiss();
            return;
        }
    }

    public interface OnOptionsSelectListener {
        void onOptionsSelect(int options1, int option2, int options3, View v);
    }

}
