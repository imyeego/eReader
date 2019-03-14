package com.a16lao.wyh.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * date:   2018/5/24 0024 下午 2:57
 * author: caoyan
 * description:
 */

public class InputUtils {
    /**
     * 关闭键盘
     */
    public static void closeInputMethodManager(Activity activity) {
        try {
            if (null == activity) {
                return;
            }
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            boolean isOpen = imm.isActive();// --判断键盘是否打开
            if (isOpen) {
                if (null != activity.getCurrentFocus() && null != activity.getCurrentFocus().getWindowToken()) {
                    ((InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(activity.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
