package com.a16lao.wyh.bean.mine;

/**
 * date:   2018/7/17 0017 上午 10:37
 * author: caoyan
 * description:
 */

public class VipBean {
    private String tag;
    private boolean noVip;
    private boolean isVip;

    public VipBean(String tag, boolean noVip, boolean isVip) {
        this.tag = tag;
        this.noVip = noVip;
        this.isVip = isVip;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isNoVip() {
        return noVip;
    }

    public void setNoVip(boolean noVip) {
        this.noVip = noVip;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
