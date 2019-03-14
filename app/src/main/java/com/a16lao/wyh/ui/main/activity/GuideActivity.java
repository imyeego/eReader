package com.a16lao.wyh.ui.main.activity;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.utils.storage.SPUtils;

/**
 * author: caoyan
 * time:2018/5/29
 * description:引导页
 */
public class GuideActivity extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        SPUtils.setAppFlag(SPUtils.IS_FIRST, true);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
