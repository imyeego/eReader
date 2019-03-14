package com.a16lao.wyh.ui.city.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.utils.dimen.DimenUtils;

import java.util.List;


/**
 * 在CoordinatorLayout中用于与AppbarLayout生成视差效果的layout_behavior
 */
public class RatioBehavior extends HeaderScrollingViewBehavior{

    final Rect mTempRect1 = new Rect();
    private int offsetTopHeader;


    public RatioBehavior() {
        offsetTopHeader = DimenUtils.dip2px(Latte.getApplication(), -40);
    }

    public RatioBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        offsetTopHeader = DimenUtils.dip2px(Latte.getApplication(), -40);

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float ratio = (float) getOffsetTopHeader()/(getScrollRange(dependency));
        float commentScrollY = ratio *(dependency.getBottom() - getTitleHeight());
        float y = dependency.getBottom()  + commentScrollY;
        child.setY((int) y);
        if (child.getY() - getTitleHeight() < -getOffsetTopHeader()){
            child.setBottom((int) (getChildBottom() + (getTitleHeight() - getOffsetTopHeader() - child.getY())));
        }
        return true;
    }


    @Override
    AppBarLayout findFirstDependency(List<View> views) {
        for (int i = 0, z = views.size(); i < z; i++) {
            View view = views.get(i);
            if (view instanceof AppBarLayout) {
                return (AppBarLayout) view;
            }
        }
        return null;
    }

    @Override
    int getScrollRange(View v) {
        if (v instanceof AppBarLayout) {
            return Math.max(0, v.getMeasuredHeight() - getTitleHeight() + getOffsetTopHeader());
        } else {
            return super.getScrollRange(v);
        }
    }


//    @Override
//    public boolean onRequestChildRectangleOnScreen(CoordinatorLayout parent, View child,
//                                                   Rect rectangle, boolean immediate) {
//        final AppBarLayout header = findFirstDependency(parent.getDependencies(child));
//        if (header != null) {
//            // Offset the rect by the child's left/top
//            rectangle.offset(child.getLeft(), child.getTop());
//
//            final Rect parentRect = mTempRect1;
//            parentRect.set(0, 0, parent.getWidth(), parent.getHeight());
//
//            if (!parentRect.contains(rectangle)) {
//                // If the rectangle can not be fully seen the visible bounds, collapse
//                // the AppBarLayout
//                header.setExpanded(false, !immediate);
//                return true;
//            }
//        }
//        return false;
//    }

    private int getTitleHeight(){
        return DimenUtils.dip2px(Latte.getApplication(), 56);
    }

    public int getOffsetTopHeader() {
        return offsetTopHeader;
    }
}
