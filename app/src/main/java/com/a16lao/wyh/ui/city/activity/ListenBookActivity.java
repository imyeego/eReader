package com.a16lao.wyh.ui.city.activity;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.widget.FloatingLayer;

public class ListenBookActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_listen_book;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        FloatingLayer.getInstance(this).show(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
