package com.a16lao.wyh.utils.timer;

import java.util.TimerTask;

/**
 * author: caoyan
 * time:2018/5/7 23:46
 * description:
 */
public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        if(mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
