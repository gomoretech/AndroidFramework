package com.gomore.gomorelibrary.view.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.gomore.gomorelibrary.view.viewpager.animation.DepthPageTransformer;
import com.gomore.gomorelibrary.view.viewpager.animation.ZoomOutPageTransformer;

/**
 * 自定义HorizontalViewPager
 */

public class HorizontalViewPager extends ViewPager {

    public static final int VIEWPAGER_ANIMATION_DEPTHPAGE = 1;//深度页面动画
    public static final int VIEWPAGER_ANIMATION_ZOOMOUT = 2;//缩放页面动画

    private float mDownX;
    private float mDownY;
    private float mTouchSlop;

    public HorizontalViewPager(Context context) {
        super(context);
        init(context);
    }

    public HorizontalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * @param AnimationType 动画类型
     *                      VIEWPAGER_ANIMATION_DEPTHPAGE：深度页面动画
     *                      VIEWPAGER_ANIMATION_ZOOMOUT：缩放页面动画
     */
    public void setViewpagerAnimation(int AnimationType) {
        switch (AnimationType){
            case VIEWPAGER_ANIMATION_DEPTHPAGE:
                setPageTransformer(false,new DepthPageTransformer());
                break;
            case VIEWPAGER_ANIMATION_ZOOMOUT:
                setPageTransformer(false,new ZoomOutPageTransformer());
                break;
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = super.onInterceptTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = x;
                mDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mDownX);
                float dy = Math.abs(y - mDownY);
                if (!intercept && dx > mTouchSlop && dx * 0.5f > dy) {
                    intercept = true;
                }
                break;
            default:
                break;
        }
        return intercept;
    }
}
