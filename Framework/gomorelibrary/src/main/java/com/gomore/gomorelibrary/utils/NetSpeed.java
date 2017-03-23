package com.gomore.gomorelibrary.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class NetSpeed {
    private final static String TAG = "NetSpeed";
    private long preRxBytes = 0;
    private double preTxBytes = 0;
    private Timer mTimer = null;
    private Context mContext;
    private static NetSpeed mNetSpeed;
    private Handler mHandler;

    private NetSpeed(Context mContext, Handler mHandler) {
        this.mContext = mContext;
        this.mHandler = mHandler;
    }

    public static NetSpeed getInstant(Context mContext, Handler mHandler) {
        if (mNetSpeed == null) {
            mNetSpeed = new NetSpeed(mContext, mHandler);
        }
        return mNetSpeed;
    }

    /**
     * 下载流量监控
     */
    private long getNetworkRxBytes() {
        int currentUid = getUid();
        Log.d(TAG, "currentUid =" + currentUid);
        if (currentUid < 0) {
            return 0;
        }
        long rxBytes = TrafficStats.getUidRxBytes(currentUid);// 获取某个网络UID的接受字节数
        /* 下句中if里的为真时，只能得到全部的网速 */
        if (rxBytes == TrafficStats.UNSUPPORTED) {
            Log.d(TAG, "getUidRxBytes fail !!!");/* 本函数可以只用下面一句即可 */
            rxBytes = TrafficStats.getTotalRxBytes();
        }
        return rxBytes;
    }

    /**
     * 上传流量监控
     */
    private double getNetworkTxBytes() {
        int currentUid = getUid();

        if (currentUid < 0) {
            return 0;
        }
//		double txBytes = TrafficStats.getUidTxBytes(currentUid);// 获取某个网络UID的发送字节数
        double txBytes = TrafficStats.getTotalTxBytes();
        /* 下句中if里的为真时，只能得到全部的网速 */
        if (txBytes == TrafficStats.UNSUPPORTED) {
            Log.d(TAG, "getUidRxBytes fail !!!");/* 本函数可以只用下面一句即可 */
            txBytes = TrafficStats.getTotalTxBytes();
        }
        return txBytes;
    }

    /**
     * 获取到的下载的速率
     */
    public long getNetRxSpeed() {

        long curRxBytes = getNetworkRxBytes();
        long bytes = curRxBytes - preRxBytes;
        preRxBytes = curRxBytes;
        int kb = (int) Math.floor(bytes / 1024 + 0.5);
        return kb;
    }

    /**
     * 获取到的上传的速率
     */
    public String getNetTxSpeed() {

        double curTxBytes = getNetworkTxBytes();
        double bytes = curTxBytes - preTxBytes;
        preTxBytes = curTxBytes;

        double kb = (double) bytes / 1024.00;
        String txtSpeed = String.format("%.2f", Double.parseDouble(kb + ""));
        return txtSpeed;
    }

    public void startCalculateNetSpeed() {
        preRxBytes = getNetworkRxBytes();
        preTxBytes = getNetworkTxBytes();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj = getNetTxSpeed();
                    Log.d(TAG, "NetTxSpeed =" + getNetTxSpeed());
                    mHandler.sendMessage(msg);
                }
            }, 500, 1000);
        }
    }

    public void stopCalculateNetSpeed() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private int getUid() {
        try {
            PackageManager pm = mContext.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(
                    mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            return ai.uid;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
