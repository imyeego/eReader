package com.a16lao.wyh.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;

import com.a16lao.wyh.config.Latte;

/**
 * date:   2018/6/7 0007 下午 4:50
 * author: caoyan
 * description:系统亮度调节
 */

public class BrightnessUtils {
    /**
     * 获取当前系统亮度
     *
     * @return
     */
    public static int getSystemBrightness() {
        int brightnessValue = -1;
        try {
            brightnessValue = Settings.System.
                    getInt(Latte.getApplication().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brightnessValue;
    }

    /**
     * 是否打开自动调节亮度
     *
     * @return
     */
    public static boolean isAutoBrightness() {
        boolean autoBrightness = false;
        try {
            autoBrightness
                    = Settings.System.getInt(Latte.getApplication().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE)
                    == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autoBrightness;
    }

    /**
     * 停止自动调节亮度
     *
     * @param activity
     */
    public static void closeAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    /**
     * 打开自动调节亮度
     *
     * @param activity
     */
    public static void openAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }

    public static void saveBrightness( int brightnessValue) {
        Uri uri = android.provider.
                Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
        android.provider.Settings.System.putInt(Latte.getApplication().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, brightnessValue);
        Latte.getApplication().getContentResolver().notifyChange(uri, null);
    }
}
