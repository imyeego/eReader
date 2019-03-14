package com.a16lao.wyh.ui.shelf.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.a16lao.wyh.base.DefaultFrgPagerAdapter;

import java.util.List;


public class ShelfPagerAdapter extends DefaultFrgPagerAdapter {

    private List<String> titles;

    public ShelfPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm, list);
    }

    public ShelfPagerAdapter(FragmentManager fm, List<String> titles, List<Fragment> list){
        super(fm, list);
        this.titles = titles;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
