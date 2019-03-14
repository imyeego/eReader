package com.a16lao.wyh.ui.mine.activity;

import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.mine.adapter.BookBuyAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookBuyActivity extends BaseActivity {
    private ListView list_book_buy;
    private List<String> mList;

    @Override
    protected int setLayout() {
        return R.layout.activity_book_buy;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_book_buy));
        list_book_buy = findViewById(R.id.list_book_buy);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        list_book_buy.setAdapter(new BookBuyAdapter(this, mList));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
