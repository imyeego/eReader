package com.a16lao.wyh.ui.main.fragment;


import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a16lao.wyh.R;

import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;

import com.a16lao.wyh.ui.city.activity.SearcherActivity;
import com.a16lao.wyh.ui.city.adapter.BookCityAdapter;
import com.a16lao.wyh.ui.city.fragment.ComicFragment;
import com.a16lao.wyh.ui.city.fragment.EssayFragment;
import com.a16lao.wyh.ui.city.fragment.FemaleFragment;
import com.a16lao.wyh.ui.city.fragment.MaleFragment;
import com.a16lao.wyh.ui.city.fragment.SelectionFragment;

import com.a16lao.wyh.utils.ToastUtils;
import com.a16lao.wyh.zxing.android.CaptureActivity;
import com.a16lao.wyh.zxing.common.Constant;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * date:   2018/5/24 0024 上午 11:44
 * author: caoyan
 * description:
 */

public class BookCityFragment extends LazyFragment {
    private LinearLayout search_city_all;
    private TabLayout tab_city_selected;
    private ViewPager vp_city_selected;
    private List<Fragment> mFragments;
    private String[] titles = {"精选", "男频", "女频", "短文", "听书", "漫画"};
    private ImageView icon_city_scan;
    public static final int REQUEST_CODE_SCAN = 1001;

    @Override
    public Object setLayout() {
        return R.layout.fragment_book_city;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        search_city_all = rootView.findViewById(R.id.search_city_all);
        tab_city_selected = rootView.findViewById(R.id.tab_city_selected);
        vp_city_selected = rootView.findViewById(R.id.vp_city_selected);
        icon_city_scan = rootView.findViewById(R.id.icon_city_scan);
        icon_city_scan.setOnClickListener(v -> toOtherBackActivity(CaptureActivity.class, null, false, REQUEST_CODE_SCAN));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                ToastUtils.showToast(mActivity, data.getStringExtra(Constant.CODED_CONTENT));
            }
        }
    }


    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new SelectionFragment());
        mFragments.add(new MaleFragment());
        mFragments.add(new FemaleFragment());
        mFragments.add(new EssayFragment());
        mFragments.add(new EssayFragment());
        mFragments.add(new ComicFragment());
        vp_city_selected.setAdapter(new BookCityAdapter(getChildFragmentManager(), mFragments, titles));
        tab_city_selected.setupWithViewPager(vp_city_selected);
        //搜索页
        search_city_all.setOnClickListener(v -> {
            toOtherActivity(SearcherActivity.class, null, false);
            mActivity.overridePendingTransition(R.anim.in, R.anim.out);


        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
