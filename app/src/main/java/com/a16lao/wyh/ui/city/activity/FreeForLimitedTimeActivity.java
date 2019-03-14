package com.a16lao.wyh.ui.city.activity;


import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.adapter.FreeForLimitedTimeAdapter;

import com.a16lao.wyh.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/6/6 0006 下午 1:25
 * author: caoyan
 * description:限时免费
 */

public class FreeForLimitedTimeActivity extends BaseActivity {
    private SwipeToLoadLayout swipe_book_free;
    private ListView swipe_target;
    private List<String> mList = new ArrayList<>();
    private FreeForLimitedTimeAdapter adapter;

    int num = 0;

    @Override
    protected int setLayout() {
        return R.layout.activity_free_for_limited_time;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText("限时免费");
        swipe_book_free = findViewById(R.id.swipe_book_free);
        swipe_target = findViewById(R.id.swipe_target);
    }

    @Override
    protected void initData() {
        for (int i = 1; i <= 20; i++) {
            mList.add("条目" + i);
        }

    }

    @Override
    protected void setListener() {
        adapter = new FreeForLimitedTimeAdapter(this, mList);
        swipe_target.setAdapter(adapter);
        swipe_book_free.setOnRefreshListener(() -> {
            num = 0;
            mList.clear();
            initData();
            adapter.notifyDataSetChanged();
            swipe_book_free.setRefreshing(false);
        });

        swipe_book_free.setOnLoadMoreListener(() -> {
            if (num == 1) {

            } else {

                num++;
                initData();
            }
            adapter.notifyDataSetChanged();
            swipe_book_free.setLoadingMore(false);

        });

        swipe_target.setOnItemClickListener((parent, view, position, id) -> {
            mList.remove(position);
            adapter.notifyDataSetChanged();
        });


    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
