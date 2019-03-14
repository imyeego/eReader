package com.a16lao.wyh.base;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * date:   2018/5/18 0018 下午 2:54
 * author: caoyan
 * description:
 */

public class DefaultFrgPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;


    public DefaultFrgPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
