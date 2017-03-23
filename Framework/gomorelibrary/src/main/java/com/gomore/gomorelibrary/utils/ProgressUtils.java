package com.gomore.gomorelibrary.utils;

import android.content.Context;

import com.gomore.gomorelibrary.view.sweetdialog.SweetAlertDialog;


public class ProgressUtils {

    private static SweetAlertDialog sweetAlertDialog = null;

    /**
     * 显示加载对话框
     *
     * @param context
     * @param loadingColorId 环形进度条的颜色
     */
    public static void showLoadingDialog(Context context, int loadingColorId) {
        if (sweetAlertDialog != null && !sweetAlertDialog.isShowing()) {
            sweetAlertDialog = null;
        }
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        }
        if (!sweetAlertDialog.isShowing()) {
            sweetAlertDialog.getProgressHelper().setBarColor(context.getResources().getColor(loadingColorId));
            sweetAlertDialog.show();
        }
    }

    /**
     * 显示加载对话框
     *
     * @param context
     * @param strMessage     加载信息提示
     * @param loadingColorId 环形进度条的颜色
     */
    public static void showLoadingDialog(Context context, String strMessage, int loadingColorId) {
        if (sweetAlertDialog != null && !sweetAlertDialog.isShowing()) {
            sweetAlertDialog = null;
        }
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText(strMessage);
        }
        if (!sweetAlertDialog.isShowing()) {
            sweetAlertDialog.getProgressHelper().setBarColor(context.getResources().getColor(loadingColorId));
            sweetAlertDialog.show();
        }
    }

    /**
     * 显示加载对话框（屏蔽返回键）
     *
     * @param context
     * @param strMessage     加载信息提示
     * @param loadingColorId 环形进度条的颜色
     */
    public static void showLoadingDialogNoBack(Context context, String strMessage, int loadingColorId) {
        if (sweetAlertDialog != null && !sweetAlertDialog.isShowing()) {
            sweetAlertDialog = null;
        }
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText(strMessage);
        }
        if (!sweetAlertDialog.isShowing()) {
            sweetAlertDialog.setCancelable(false);
            sweetAlertDialog.getProgressHelper().setBarColor(context.getResources().getColor(loadingColorId));
            sweetAlertDialog.show();
        }
    }


    /**
     * 关闭对话框
     */
    public static void closeLoadingDialog() {
        if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
            sweetAlertDialog.dismiss();
            sweetAlertDialog = null;
        }
    }

    public static boolean isShowing() {
        return sweetAlertDialog.isShowing();
    }
}
