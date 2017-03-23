package com.gomore.gomorelibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Kennen on 2017/3/23.
 */
public class DensityUtil {


    /**
     * 获取屏幕宽度
     */
    public static int getScreenW(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int w = dm.widthPixels;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth();
        return w;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenH(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int h = dm.heightPixels;
        // int h = aty.getWindowManager().getDefaultDisplay().getHeight();
        return h;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param paramContext
     * @param paramFloat
     * @return
     */
    public static int dip2px(Context paramContext, float paramFloat) {
        float f = paramContext.getResources().getDisplayMetrics().density;
        return (int) (paramFloat * f + 0.5F);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param paramContext
     * @param paramFloat
     * @return
     */
    public static int px2dip(Context paramContext, float paramFloat) {
        float f = paramContext.getResources().getDisplayMetrics().density;
        return (int) (paramFloat / f + 0.5F);
    }

    public static int sp2px(Context paramContext, float paramFloat) {
        float f = paramContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (paramFloat * f + 0.5F);
    }

    public static int px2sp(Context paramContext, float paramFloat) {
        float f = paramContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (paramFloat / f + 0.5F);
    }

    public static int dpToPx(Context context, int dp) {
        int px = Math.round(dp * getPixelScaleFactor(context));
        return px;
    }

    public static int pxToDp(Context context, int px) {
        int dp = Math.round(px / getPixelScaleFactor(context));
        return dp;
    }

    private static float getPixelScaleFactor(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}