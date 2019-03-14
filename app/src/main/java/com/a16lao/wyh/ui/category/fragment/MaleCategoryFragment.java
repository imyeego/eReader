package com.a16lao.wyh.ui.category.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.category.activity.CategoryDetailActivity;
import com.a16lao.wyh.ui.category.adapter.MaleCategoryDetailAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/7/18 0018 下午 3:08
 * author: caoyan
 * description:
 */

public class MaleCategoryFragment extends LazyFragment {
    private GridView grid_male_category;
    private List<String> mList;

    @Override
    public Object setLayout() {
        return R.layout.fragment_male_category;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        grid_male_category = rootView.findViewById(R.id.grid_male_category);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        mList.add("1");
        mList.add("1");
        grid_male_category.setAdapter(new MaleCategoryDetailAdapter(mActivity, mList));
        grid_male_category.setOnItemClickListener((parent, view, position, id) -> {
            Bundle bundle = new Bundle();
            bundle.putString("position", mList.get(position));
            toOtherActivity(CategoryDetailActivity.class, bundle, false);
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
