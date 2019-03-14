package com.a16lao.wyh.widget.pageindecator;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.a16lao.wyh.R;

import java.util.List;

public class PageIndicatorSetup {

    public static void setupPageIndicator(Context context, ViewPagerIndicator vpi, ViewPager vp, List<String> titles){

        vpi.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setScrollPivotX(0.35f);
        commonNavigator.setAdapter(new NavigatorAdapter() {
            @Override
            public int getCount() {
                return titles == null ? 0 : titles.size();
            }

            @Override
            public ITitleView getTitleView(Context context, final int index) {
                TitleView titleView = new TitleView(context);
                titleView.setText(titles.get(index));
                titleView.setNormalColor(ContextCompat.getColor(context, R.color.color_2));
                titleView.setSelectedColor(ContextCompat.getColor(context, R.color.white));
                titleView.setOnClickListener(v -> {
                    vp.setCurrentItem(index);

                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                indicator.setFillColor(ContextCompat.getColor(context, R.color.color_1));
                return indicator;
            }
        });
        vpi.setNavigator(commonNavigator);
        ViewPagerHelper.bind(vpi, vp);


    }
}
