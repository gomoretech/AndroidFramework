# Framework[ ![Download](https://api.bintray.com/packages/smilefei/maven/AndroidFramework/images/download.svg) ](https://bintray.com/smilefei/maven/AndroidFramework)


## 勾芒科技公司安卓项目依赖框架

## 添加说明





### 一、仿iOS的PickerView控件，有时间选择和选项选择并支持一二三级联动效果

    ——TimePickerView  时间选择器，支持年月日时分，年月日，年月，时分等格式
    ——OptionsPickerView  选项选择器，支持一，二，三级选项选择，并且可以设置是否联动

使用详情具体见: (https://github.com/Bigkoo/Android-PickerView)



### 二、引用比较酷炫的SweetAlertDialog对话框

——Usage

    SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
    pDialog.setTitleText("Loading");
    pDialog.setCancelable(false);
    pDialog.show();

使用详情具体见：(https://github.com/pedant/sweet-alert-dialog)



### 三、新增工具模块

    ——DateUtil: 日期处理工具
    ——StringUtils: 字符串处理工具类
    ——DialogUtils: 提示对话框
    ——ProgressUtils: 加载对话框
    ——TimePickerUtils: 仿IOS模式的时间选择框工具类
    ——MD5Util: MD5加密工具
    ——DensityUtil: 手机屏幕尺寸处理工具
    ——FileTools: 文件读写工具
    ——NetSpeed: 实时网速监测工具
    ——SystemTool: 系统信息工具类



### 四、仿微信底部菜单控件

    ——BottomTabView 仿微信底部菜单，配合ViewPager使用，滑动时颜色渐变



### 五、自定义ViewPager

    ——VerticalViewPager 纵向滑动ViewPager
    ——HorizontalViewPager 横向滑动ViewPager

    FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(),  new ArrayList<Fragment>());
    viewPager.setAdapter(adapter);
    viewPager.setViewpagerAnimation(HorizontalViewPager.VIEWPAGER_ANIMATION_ZOOMOUT);//设置ViewPager切换动画



### 六、版本检测升级（更新）库(支持通知栏显示，可设置静默下载)

1. 自定义service继承 `VersionService `，实现其中的 `onResponses(VersionService service, String response)`抽象方法.该方法主要是请求版本接口的回调，由于不同的使用者版本检测接口返回数据类型不一致，所以你需要自己解析数据，判断版本号之后调用升级对话框，如果使用库默认的界面直接调用如下方法: `service.showVersionDialog(downloadUrl,title,updateMsg )`

        if (serverVersion > clientVersion) {
    	     //传入下载地址，以及版本更新消息，bundle可附带更多信息
    	    service.showVersionDialog(downloadUrl,title,updateMsg,bundle);
        }

2. 在任意地方开启自定义service，并传入`VersionParam`

    ```
        VersionParams.Builder builder = new VersionParams.Builder()
                 .setRequestUrl("请求服务器版本信息的接口地址")
                 .setService(DemoService.class);
        //可配置通知栏图标
        DownloadManager.NotificationIcon = R.mipmap.palmmall_launcher_icon;
        AllenChecker.startVersionCheck(this, builder.build());
    ```

3. 可自定义升级弹框，只需创建一个继承自DialogActivity`的Activity,Activity设置Theme为透明

     android:theme="@style/versionCheckLibvtransparentTheme"`

开启Service的之前，记住将自定义的Activity传入VersionParams

      `setCustomDownloadActivityClass(CustomVersionDialogActivity.class)`

调用父类`getVersionTitle()` ,`getVersionUpdateMsg()`,`getVersionParamBundle()`方法,这是从service传过来的值，可以在自定义界面使用

使用详情具体见：(https://github.com/AlexLiuSheng/CheckVersionLib)



### android studio导入
`compile 'com.gomorelibrary.AndroidFramework.1.1.5'`