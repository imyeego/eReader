package com.a16lao.wyh.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.a16lao.wyh.R;

/**
 * date:   2018/5/15 0015 下午 2:24
 * author: caoyan
 * description:
 */

public class ToastUtils {
    private static Toast toast = null;


    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.show();
    }


    public static void show(Context context, String text) {
        toast = new Toast(context);
        setGravity();
        setView(context, text);
        toast.show();
    }

    private static void setGravity() {
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    private static void setView(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
        TextView text_toast = view.findViewById(R.id.text_toast);
        text_toast.setText(message);
        toast.setView(view);
    }
}
