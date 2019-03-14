package com.a16lao.wyh.widget.loading;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;

import com.a16lao.wyh.R;


/**
 * date:   2018/5/15 0015 下午 1:50
 * author: caoyan
 * description:
 */

public class BeforeLoadingDialog implements LoadingDialog {
    private Context mContext;
    private Dialog dialog;
    private LoadingView loading;
    private boolean isShowing;

    public BeforeLoadingDialog(Context context) {
        this.mContext = context;
        dialog = new AppCompatDialog(context, R.style.dialog);
        loading = new LoadingView(context);
        dialog.setContentView(loading);
    }

    @Override
    public void show() {
        if (!isShowing) {
            loading.createAnimation();
            isShowing = true;
            dialog.show();
        }

    }

    @Override
    public void dismiss() {
        if (isShowing) {
            loading.release();
            isShowing = false;
            dialog.dismiss();
        }
    }

    @Override
    public boolean isShowing() {
        return isShowing;
    }
}
