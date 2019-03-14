package com.a16lao.wyh.ui.city.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.a16lao.wyh.R;

import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.DefaultWithTabAdapter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.utils.view.ViewUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * date:   2018/6/21 0021 上午 11:40
 * author: caoyan
 * description:
 */

public class SearcherResultFragment extends LazyFragment {
    private String[] mStrings = {"书籍", "短文", "用户"};
    private TabLayout tab_search_result;
    private ViewPager vp_search;
    private List<Fragment> fragments;


    @Override
    public Object setLayout() {
        return R.layout.fragment_searcher_result;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        tab_search_result = rootView.findViewById(R.id.tab_search_result);
        vp_search = rootView.findViewById(R.id.vp_search);
        tab_search_result.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
        });


    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        for (int i = 0; i < mStrings.length; i++) {
            fragments.add(new SearcherFindFragment());
        }
        vp_search.setAdapter(new DefaultWithTabAdapter(getChildFragmentManager(), fragments, mStrings));
        tab_search_result.setupWithViewPager(vp_search);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
