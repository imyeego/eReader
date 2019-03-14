package com.a16lao.wyh.ui.mine.activity;

import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.mine.adapter.BookBuy2Adapter;

import java.util.ArrayList;
import java.util.List;

public class BookBuyDetailActivity extends BaseActivity {
    private List<String> mList;
    private ListView list_book_buy_detail;

    @Override
    protected int setLayout() {
        return R.layout.activity_book_buy_detail;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_book_buy_detail));
        list_book_buy_detail = findViewById(R.id.list_book_buy_detail);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        list_book_buy_detail.setAdapter(new BookBuy2Adapter(this, mList, false));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
