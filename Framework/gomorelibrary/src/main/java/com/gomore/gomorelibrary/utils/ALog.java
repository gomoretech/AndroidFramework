package com.gomore.gomorelibrary.utils;

import android.util.Log;

import com.gomore.gomorelibrary.update.core.UpdateChecker;


public class ALog {
    public static void e(String msg) {
        if (UpdateChecker.isDebug()) {
            if (msg != null && !msg.isEmpty())
                Log.e("Allen Checker", msg);
        }
    }
}
