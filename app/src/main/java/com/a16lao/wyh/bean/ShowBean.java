package com.a16lao.wyh.bean;

/**
 * date:   2018/7/13 0013 下午 3:52
 * author: caoyan
 * description:
 */

public class ShowBean {
    private boolean isShowing;

    public ShowBean(boolean isShowing) {
        this.isShowing = isShowing;
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }
}
