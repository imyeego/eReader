package com.a16lao.wyh.base;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;


/**
 * date:   2018/5/18 0018 下午 4:53
 * author: caoyan
 * description:
 */

public abstract class BasePopupWindow extends PopupWindow {
    private Context mContext;
    private Drawable mBackgroundDrawable;
    protected View mView;

    public BasePopupWindow(Context context) {
        super(context);
        this.mContext = context;
        initBasePopupWindow();
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        super.setOutsideTouchable(touchable);
        if (touchable) {
            super.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#50000000")));
        } else {
            super.setBackgroundDrawable(null);
        }

    }


    @Override
    public void setBackgroundDrawable(Drawable background) {
        mBackgroundDrawable = background;
        setOutsideTouchable(isOutsideTouchable());
    }


    private void initBasePopupWindow() {
        mView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(setLayout(), null);
        setContentView(mView);
        mView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                dismiss();
            }
            return true;
        });
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setOutsideTouchable(true);
        initData(mView);

    }


    /**
     * 显示popupWindow
     *
     * @param view
     */
    public void showPopupWindow(View view) {
        if (!isShowing()) {
            showAsDropDown(view);
        } else {
            dismiss();
        }


    }


    protected abstract int setLayout();

    protected abstract void initData(View view);


}

