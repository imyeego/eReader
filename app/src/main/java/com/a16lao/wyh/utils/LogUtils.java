package com.a16lao.wyh.utils;

import android.util.Log;

/**
 * date:   2018/5/15 0015 下午 4:06
 * author: caoyan
 * description:
 */

public class LogUtils {
    private static final boolean isDebug = true;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }
}
