package com.a16lao.wyh.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;


/**
 * date:   2018/5/16 0016 下午 3:34
 * author: caoyan
 * description:
 */

public class MainFragment extends LazyFragment {

    @Override
    public Object setLayout() {
        return R.layout.list_item;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        rootView.findViewById(R.id.text);


    }

    @Override
    protected void initData() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
