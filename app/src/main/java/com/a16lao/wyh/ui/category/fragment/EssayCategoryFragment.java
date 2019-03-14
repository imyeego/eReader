package com.a16lao.wyh.ui.category.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.category.activity.EssayCategoryDetailActivity;
import com.a16lao.wyh.ui.category.adapter.EssayCategoryOneAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/7/18 0018 下午 3:43
 * author: caoyan
 * description:
 */

public class EssayCategoryFragment extends LazyFragment {
    private GridView grid_essay_category;
    private List<String> mList;

    @Override
    public Object setLayout() {
        return R.layout.fragment_essay_category;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        grid_essay_category = rootView.findViewById(R.id.grid_essay_category);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        mList.add("1");
        mList.add("1");
        grid_essay_category.setAdapter(new EssayCategoryOneAdapter(mActivity, mList));
        grid_essay_category.setOnItemClickListener((parent, view, position, id) -> {
            Bundle bundle = new Bundle();
            bundle.putString("position", mList.get(position));
            toOtherActivity(EssayCategoryDetailActivity.class, bundle, false);
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
