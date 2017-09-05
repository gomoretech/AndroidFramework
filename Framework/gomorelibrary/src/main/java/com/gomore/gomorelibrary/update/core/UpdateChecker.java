package com.gomore.gomorelibrary.update.core;

import android.content.Context;
import android.content.Intent;

public class UpdateChecker {
    private static boolean isDebug=true;
    public static void startVersionCheck(Context context, VersionParams versionParams) {
        Intent intent = new Intent(context, versionParams.getService());
        intent.putExtra(VersionService.VERSION_PARAMS_KEY, versionParams);
        context.startService(intent);
    }
    public static void init(boolean debug){
        isDebug=debug;
    }
    public static boolean isDebug(){
        return isDebug;
    }




}
