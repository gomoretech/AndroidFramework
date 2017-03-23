
package com.gomore.gomorelibrary.utils;

public final class GomoreConfig {

    public static final int VERSION = 1;

    /** 声音改变的广播 */
    public static final String RECEIVER_MUSIC_CHANGE = GomoreConfig.class.getName()
            + "org.kymjs.android.frame.music_change";
    /** 错误处理广播 */
    public static final String RECEIVER_ERROR = GomoreConfig.class.getName()
            + "org.kymjs.android.frame.error";
    /** 无网络警告广播 */
    public static final String RECEIVER_NOT_NET_WARN = GomoreConfig.class.getName()
            + "org.kymjs.android.frame.notnet";
    /** preference键值对 */
    public static final String SETTING_FILE = "kjframe_preference";
    public static final String ONLY_WIFI = "only_wifi";
}
