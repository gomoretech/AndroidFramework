# Framework


## 勾芒科技公司安卓项目依赖框架

## 添加说明





1.仿iOS的PickerView控件，有时间选择和选项选择并支持一二三级联动效果

——TimePickerView  时间选择器，支持年月日时分，年月日，年月，时分等格式   
——OptionsPickerView  选项选择器，支持一，二，三级选项选择，并且可以设置是否联动
使用详情具体见: (https://github.com/Bigkoo/Android-PickerView)


2.引用比较酷炫的SweetAlertDialog对话框

——USage

    SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
    pDialog.setTitleText("Loading");
    pDialog.setCancelable(false);
    pDialog.show();

使用详情具体见：(https://github.com/pedant/sweet-alert-dialog)