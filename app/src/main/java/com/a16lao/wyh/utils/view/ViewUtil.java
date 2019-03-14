package com.a16lao.wyh.utils.view;

import android.content.Context;
import android.util.TypedValue;
import android.widget.AbsListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.widget.PromptView;

/**
 * date:   2018/5/15 0015 上午 11:47
 * author: caoyan
 * description:
 */

public class ViewUtil {
    /**
     * dip转换px
     */
    public static int dip2px(int dip, Context context) {
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px, Context context) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static PromptView getEmptyLayout(Context context, int type) {

        PromptView layout = new PromptView(context);

        switch (type) {

        }

        //这里控制了
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);

        return layout;
    }
}
