package com.a16lao.wyh.bean;

/**
 * date:   2018/6/7 0007 下午 2:09
 * author: caoyan
 * description:
 */

public class ThemeBean {
    private boolean flag;
    public ThemeBean(boolean flag) {
        this.flag=flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
