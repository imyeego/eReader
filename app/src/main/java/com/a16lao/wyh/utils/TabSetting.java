package com.a16lao.wyh.utils;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.config.Latte;

import java.util.List;

/**
 * date:   2018/7/6 0006 下午 5:03
 * author: caoyan
 * description:
 */

public class TabSetting {

    private ViewHolder mHolder;

    public void setMargin(TabLayout tab, int margin) {
        for (int i = 0; i < tab.getTabCount(); i++) {
            LinearLayout linearLayout = (LinearLayout) (((LinearLayout) tab.getChildAt(0)).getChildAt(tab.getTabAt(i).getPosition()));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            params.rightMargin = margin;
            linearLayout.setLayoutParams(params);
        }
    }

    public void setTabView(TabLayout tabs, List<String> titles, ViewPager viewPager) {
        mHolder = null;
        for (int i = 0; i < titles.size(); i++) {
            TabLayout.Tab tab = tabs.getTabAt(i);
            tab.setCustomView(R.layout.layout_pager_title);
            mHolder = new ViewHolder(tab.getCustomView());
            mHolder.text_pager_title.setText(titles.get(i));
            if (i == 0) {
                mHolder.text_pager_title.setSelected(true);
                mHolder.text_pager_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, R.dimen.font_2);

            }
        }


        //tab选中的监听事件
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mHolder = new ViewHolder(tab.getCustomView());
                mHolder.text_pager_title.setSelected(true);
                mHolder.text_pager_title.setTextColor(Color.WHITE);
                mHolder.text_pager_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, R.dimen.font_2);

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mHolder = new ViewHolder(tab.getCustomView());
                mHolder.text_pager_title.setSelected(false);
                mHolder.text_pager_title.setTextColor(Latte.getApplication().getResources().getColor(R.color.color_4));
                //恢复为默认字体大小
                mHolder.text_pager_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, R.dimen.font_3);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}


class ViewHolder {
    TextView text_pager_title;

    public ViewHolder(View tabView) {
        text_pager_title = tabView.findViewById(R.id.text_pager_title);
    }
}
