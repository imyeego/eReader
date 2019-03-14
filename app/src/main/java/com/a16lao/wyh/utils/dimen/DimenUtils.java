package com.a16lao.wyh.utils.dimen;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.a16lao.wyh.config.Latte;


/**
 * author: caoyan
 * time:2018/5/4 14:16
 * description:
 */
public class DimenUtils {
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }
}
