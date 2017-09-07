package com.gomore.gomorelibrary.utils;

import android.content.Context;

import com.gomore.gomorelibrary.view.sweetdialog.SweetAlertDialog;

/**
 * 常用的提示对话框
 * <p>
 * Created by Kennen on 2017/3/23.
 */
public class DialogUtils {

    private static SweetAlertDialog sweetAlertDialog = null;

    /**
     * 确认对话框
     *
     * @param title           标题
     * @param content         内容
     * @param confirmListener 确认回调事件
     */
    public static void confirmDialog(Context context, String title, String content,
                                     SweetAlertDialog.OnSweetClickListener confirmListener) {
        confirmDialog(context, title, content, "取消", "确定", confirmListener, SweetAlertDialog.NORMAL_TYPE);
    }

    /**
     * 确认对话框
     *
     * @param title           标题
     * @param content         内容
     * @param confirmListener 确认回调事件
     * @param dialogType      对话框风格(NORMAL_TYPE, ERROR_TYPE, SUCCESS_TYPE, WARNING_TYPE, CUSTOM_IMAGE_TYPE, PROGRESS_TYPE)
     */
    public static void confirmDialog(Context context, String title, String content,
                                     SweetAlertDialog.OnSweetClickListener confirmListener, int dialogType) {
        confirmDialog(context, title, content, null, null, confirmListener, dialogType);
    }

    /**
     * 确认对话框
     *
     * @param title           标题
     * @param content         内容
     * @param cancel          取消文字
     * @param confirm         确认文字
     * @param confirmListener 确认回调事件
     * @param dialogType      对话框风格(NORMAL_TYPE, ERROR_TYPE, SUCCESS_TYPE, WARNING_TYPE, CUSTOM_IMAGE_TYPE, PROGRESS_TYPE)
     */
    public static void confirmDialog(Context context, String title, String content, String cancel, String confirm,
                                     SweetAlertDialog.OnSweetClickListener confirmListener, int dialogType) {
        if (dialogType < SweetAlertDialog.NORMAL_TYPE || dialogType > SweetAlertDialog.PROGRESS_TYPE) {
            dialogType = 0;
        }
        if (sweetAlertDialog != null && !sweetAlertDialog.isShowing()) {
            sweetAlertDialog = null;
        }
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context, dialogType);
        }
        if (title == null) {
            title = "提示";
        }
        if (cancel == null) {
            cancel = "取消";
        }
        if (confirm == null) {
            confirm = "确定";
        }
        sweetAlertDialog.setTitleText(title)
                .setContentText(content)
                .setCancelText(cancel)
                .setConfirmText(confirm)
                .setConfirmClickListener(confirmListener);
        if (!sweetAlertDialog.isShowing()) {
            sweetAlertDialog.show();
        }
    }

    /**
     * 确认/取消对话框
     *
     * @param title           标题
     * @param content         内容
     * @param cancel          取消文字
     * @param confirm         确认文字
     * @param cancelListener  取消回调事件
     * @param confirmListener 确认回调事件
     * @param dialogType      对话框风格(NORMAL_TYPE, ERROR_TYPE, SUCCESS_TYPE, WARNING_TYPE, CUSTOM_IMAGE_TYPE, PROGRESS_TYPE)
     */
    public static void confirmDialog(Context context, String title, String content, String cancel, String confirm,
                                     SweetAlertDialog.OnSweetClickListener cancelListener,
                                     SweetAlertDialog.OnSweetClickListener confirmListener, int dialogType) {
        if (dialogType < SweetAlertDialog.NORMAL_TYPE || dialogType > SweetAlertDialog.PROGRESS_TYPE) {
            dialogType = 0;
        }
        if (sweetAlertDialog != null && !sweetAlertDialog.isShowing()) {
            sweetAlertDialog = null;
        }
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context, dialogType);
        }
        if (title == null) {
            title = "提示";
        }
        if (cancel == null) {
            cancel = "取消";
        }
        if (confirm == null) {
            confirm = "确定";
        }
        sweetAlertDialog.setTitleText(title)
                .setContentText(content)
                .setCancelText(cancel)
                .setConfirmText(confirm)
                .setCancelClickListener(cancelListener)
                .setConfirmClickListener(confirmListener);
        if (!sweetAlertDialog.isShowing()) {
            sweetAlertDialog.show();
        }
    }

    /**
     * 成功提示框
     *
     * @param content
     */
    public static void successDialog(Context context, String content) {
        successDialog(context, null, content, null);
    }

    /**
     * 成功提示框
     *
     * @param title
     * @param content
     * @param confirm
     */
    public static void successDialog(Context context, String title, String content, String confirm) {
        if (sweetAlertDialog != null && !sweetAlertDialog.isShowing()) {
            sweetAlertDialog = null;
        }
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        }
        if (title == null) {
            title = "提示";
        }
        if (confirm == null) {
            confirm = "确定";
        }
        sweetAlertDialog.setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirm);
        if (!sweetAlertDialog.isShowing()) {
            sweetAlertDialog.show();
        }
    }

    /**
     * 错误提示框
     *
     * @param content
     */
    public static void errorDialog(Context context, String content) {
        errorDialog(context, null, content, null);
    }

    /**
     * 错误提示框
     *
     * @param title
     * @param content
     * @param confirm
     */
    public static void errorDialog(Context context, String title, String content, String confirm) {
        if (sweetAlertDialog != null && !sweetAlertDialog.isShowing()) {
            sweetAlertDialog = null;
        }
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        }
        if (title == null) {
            title = "提示";
        }
        if (confirm == null) {
            confirm = "确定";
        }
        sweetAlertDialog.setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirm);
        if (!sweetAlertDialog.isShowing()) {
            sweetAlertDialog.show();
        }
    }

    /**
     * 警告提示框
     *
     * @param content
     */
    public static void warningDialog(Context context, String content) {
        warningDialog(context, null, content, null);
    }

    /**
     * 警告提示框
     *
     * @param title
     * @param confirm
     */
    public static void warningDialog(Context context, String title, String content, String confirm) {
        if (sweetAlertDialog != null && !sweetAlertDialog.isShowing()) {
            sweetAlertDialog = null;
        }
        if (sweetAlertDialog == null) {
            sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        }
        if (title == null) {
            title = "提示";
        }
        if (confirm == null) {
            confirm = "确定";
        }
        sweetAlertDialog.setTitleText(title)
                .setContentText(content)
                .setConfirmText(confirm);
        if (!sweetAlertDialog.isShowing()) {
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
