package com.gomore.framework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gomore.framework.fragment.FragmentOne;
import com.gomore.framework.fragment.FragmentThr;
import com.gomore.framework.fragment.FragmentTwo;
import com.gomore.gomorelibrary.view.BottomTabView;
import com.gomore.gomorelibrary.view.viewpager.HorizontalViewPager;
import com.gomore.gomorelibrary.view.viewpager.adapter.FragmentViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity {

    HorizontalViewPager viewPager;

    //底部导航栏按钮
    BottomTabView tab_account;
    BottomTabView tab_plan;
    BottomTabView tab_self;
    private List<BottomTabView> tabList = new ArrayList<>();
    //Fragment适配器
    FragmentViewPagerAdapter adapter;
    //子Fragment集合
    private List<Fragment> list = new ArrayList<>();
    //子fragment
    private Fragment fragmentOne, fragmentTwo, fragmentThr;
    //底部按钮点击事件标记
    private int lastClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        //设置监听
        setListener();
    }

    //设置监听
    private void setListener() {
        //底部按钮设置监听
        for (int i = 0; i < tabList.size(); i++) {
            tabList.get(i).setTag(i);
            tabList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int tag = (int) view.getTag();
                    if (lastClick == tag) {
                        return;
                    }
                    for (int i = 0; i < tabList.size(); i++) {
                        tabList.get(i).setIconAlpha(0);
                    }
                    tabList.get(tag).setIconAlpha(1f);
                    viewPager.setCurrentItem(tag, false);
                    lastClick = tag;
                }
            });
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动中……
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    tabList.get(position).setIconAlpha(1 - positionOffset);
                    tabList.get(position + 1).setIconAlpha(positionOffset);
                }
            }

            //选中后
            @Override
            public void onPageSelected(int position) {
                lastClick = position;
            }

            //滑动状态改变
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewPager = (HorizontalViewPager) findViewById(R.id.viewpager_home);
        tab_account = (BottomTabView) findViewById(R.id.bottomTab_account);
        tab_plan = (BottomTabView) findViewById(R.id.bottomTab_plan);
        tab_self = (BottomTabView) findViewById(R.id.bottomTab_self);
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThr = new FragmentThr();
        list.add(fragmentOne);
        list.add(fragmentTwo);
        list.add(fragmentThr);
        //适配器
        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        //底部导航栏按钮集合
        tabList.add(tab_account);
        tabList.add(tab_plan);
        tabList.add(tab_self);
        tabList.get(0).setIconAlpha(1f);
        viewPager.setViewpagerAnimation(HorizontalViewPager.VIEWPAGER_ANIMATION_ZOOMOUT);
    }
}
