package com.a16lao.wyh.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * date:   2018/5/29 0029 上午 10:29
 * author: caoyan
 * description:
 */

public class DefaultWithTabAdapter extends DefaultFrgPagerAdapter {
    private String[] titles;


    public DefaultWithTabAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm, list);
        this.titles = titles;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
