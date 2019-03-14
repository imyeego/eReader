package com.a16lao.wyh.bean.category;

/**
 * date:   2018/7/19 0019 下午 3:52
 * author: caoyan
 * description:
 */

public class ChooseTypeBean {
    private String mString;
    private boolean flag;

    public ChooseTypeBean(String string, boolean flag) {
        mString = string;
        this.flag = flag;
    }

    public String getString() {
        return mString;
    }

    public void setString(String string) {
        mString = string;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
