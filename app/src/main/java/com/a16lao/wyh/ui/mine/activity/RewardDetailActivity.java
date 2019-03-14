package com.a16lao.wyh.ui.mine.activity;

import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.mine.adapter.RewardDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class RewardDetailActivity extends BaseActivity {
    private ListView list_reward_detail;
    private List<String> mList;

    @Override
    protected int setLayout() {
        return R.layout.activity_reward_detail;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_reward_detail));
        list_reward_detail = findViewById(R.id.list_reward_detail);
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        list_reward_detail.setAdapter(new RewardDetailAdapter(this, mList));
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
