package com.a16lao.wyh.widget.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * date:   2018/5/30 0030 上午 11:00
 * author: caoyan
 * description:
 */

public class BannerViewPager extends ViewPager {
    private boolean scrollable = true;
    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.scrollable && !(getCurrentItem() == 0 && getChildCount() == 0) && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.scrollable && !(getCurrentItem() == 0 && getChildCount() == 0) && super.onInterceptTouchEvent(ev);
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }
}
