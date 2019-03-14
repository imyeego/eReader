package com.a16lao.wyh.utils;

/**
 * date:   2018/7/5 0005 下午 12:01
 * author: caoyan
 * description:
 */

public class SystemUtils {
    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

}
