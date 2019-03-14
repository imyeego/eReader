package com.a16lao.wyh.ui.mine.activity;

import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.mine.adapter.RewardDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class RechargeDetailActivity extends BaseActivity {
    private List<String> mList;
    private ListView list_recharge_detail;

    @Override
    protected int setLayout() {
        return R.layout.activity_recharge_detail;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_recharge_detail));
        list_recharge_detail = findViewById(R.id.list_recharge_detail);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        list_recharge_detail.setAdapter(new RewardDetailAdapter(this, mList));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
