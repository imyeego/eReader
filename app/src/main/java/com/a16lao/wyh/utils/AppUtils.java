package com.a16lao.wyh.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.stetho.common.LogUtil;

/**
 * date:   2018/7/5 0005 下午 1:04
 * author: caoyan
 * description:
 */

public class AppUtils {
    public static int getVersionCode(Context ctx) {
        int versionCode = 0;
        try {
            versionCode = ctx.getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0)
                    .versionCode;
            LogUtil.d("TAG", "本软件的版本号。。" + versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getVersionName(Context ctx) {
        String versionName = "";
        try {
            versionName = ctx.getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0)
                    .versionName;
            LogUtil.d("TAG", "本软件的版本号。。" + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
