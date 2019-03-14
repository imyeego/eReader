package com.a16lao.wyh.ui.city.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.a16lao.wyh.base.DefaultFrgPagerAdapter;

import java.util.List;

/**
 * date:   2018/5/29 0029 上午 10:29
 * author: caoyan
 * description:
 */

public class BookCityAdapter extends DefaultFrgPagerAdapter {
    private String[] titles;

    public BookCityAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm, list);
    }

    public BookCityAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm, list);
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
