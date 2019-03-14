package com.a16lao.wyh.ui.mine.activity;

import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.mine.adapter.RewardDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class GiftActivity extends BaseActivity {
    private List<String> mList;
    private ListView list_gift;

    @Override
    protected int setLayout() {
        return R.layout.activity_gift;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_activity_gift));
        list_gift = findViewById(R.id.list_gift);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        list_gift.setAdapter(new RewardDetailAdapter(this, mList));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
