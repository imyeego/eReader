package com.a16lao.wyh.ui.main.activity;

import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;

import com.a16lao.wyh.config.GlobalParam;
import com.a16lao.wyh.utils.storage.SPUtils;
import com.a16lao.wyh.utils.timer.BaseTimerTask;
import com.a16lao.wyh.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;


/**
 * author: caoyan
 * time:2018/5/29
 * description:闪屏页
 */
public class SplashActivity extends BaseActivity implements ITimerListener {
    private TextView text_splash_skip;
    private Timer mTimer = null;
    private int mCount = GlobalParam.SPLASH_TIME;


    @Override
    protected int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        text_splash_skip = findViewById(R.id.text_splash_skip);

    }

    @Override
    protected void initData() {
        initTimer();
    }

    @Override
    protected void setListener() {

        text_splash_skip.setOnClickListener(v -> goHomeActivity());
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    public void onTimer() {
        runOnUiThread(() -> {
            if (text_splash_skip != null) {
                text_splash_skip.setText(MessageFormat.format("跳过{0}s", mCount));
                mCount--;
                if (mCount < 0) {
                    if (text_splash_skip != null) {
                        goHomeActivity();
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                        }

                    }
                }
            }
        });
    }

    private void initTimer() {
        mTimer = new Timer();
        BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, GlobalParam.DURATION_TIME);
    }


    private void goHomeActivity() {
        Class clz;
        if (SPUtils.getAppFlag(SPUtils.IS_FIRST)) {
            clz = MainActivity.class;
        } else {
            clz = GuideActivity.class;
        }
//        toOtherActivity(clz, null, false);
        toOtherActivity(MainActivity.class, null, false);
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
