package com.a16lao.wyh.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.a16lao.wyh.config.Latte;

import java.util.List;

/**
 * date:   2018/5/28 0028 下午 3:41
 * author: caoyan
 * description:
 */

public class ClientAvailableUtils {


    public static boolean isWeiChatAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packageManager
        List<PackageInfo> info = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (info != null) {
            for (int i = 0; i < info.size(); i++) {
                String pn = info.get(i).packageName;
                if ("com.tencent.mm".equals(pn)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> info = packageManager.getInstalledPackages(0);
        if (info != null) {
            for (int i = 0; i < info.size(); i++) {
                String pn = info.get(i).packageName;
                if ("com.tencent.mobileqq".equals(pn)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSinaClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> info = packageManager.getInstalledPackages(0);
        if (info != null) {
            for (int i = 0; i < info.size(); i++) {
                String pn = info.get(i).packageName;
                if ("com.sina.weibo".equals(pn)) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void goToMarket(Context context, String packageName) {

        try {
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            ToastUtils.showToast(Latte.getApplication(),"您的手机没有安装Android应用市场");

            e.printStackTrace();
        }
    }


}
