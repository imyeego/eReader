package com.a16lao.wyh.base;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.a16lao.wyh.utils.dimen.DimenUtils;

import rx.subscriptions.CompositeSubscription;

/**
 * date:   2018/5/28 0028 上午 10:41
 * author: caoyan
 * description:
 */

public abstract class BaseDialogFragment extends DialogFragment {
    protected View rootView;
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();
    private boolean isAnimation = false;
    private boolean onCancel = true;

    public boolean isOnCancel() {
        return onCancel;
    }

    public void setOnCancel(boolean onCancel) {
        this.onCancel = onCancel;
    }

    @Override
    public void dismiss() {
        if (isOnCancel()) {
            super.dismiss();
        }

//        if (isAnimation) {
//            return;
//        }
//        isAnimation = true;
//        slideToDown(rootView);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //去掉标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() type must be int or view");
        }
//        slideToUp(rootView);
        return rootView;
    }

    protected abstract Object setLayout();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    protected abstract void initView();


    //上移动画
    public void slideToUp(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);

    }

    //下移动画
    public void slideToDown(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimation = false;
                BaseDialogFragment.super.dismiss();//使用原有的dismiss()方法
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        initDialogStyle();
    }


    protected abstract int initGravity();

    protected void initDialogStyle() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = initGravity();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);

        //把原有背景设置为透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (Gravity.CENTER == initGravity()) {
            window.setLayout((int) (DimenUtils.getScreenWidth() * 0.72), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        window.getDecorView().setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                dismiss();
            }
            return true;
        });

    }
}
