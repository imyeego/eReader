package com.a16lao.wyh.ui.city.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import android.widget.ListView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.bean.city.SearcherBean;
import com.a16lao.wyh.ui.city.adapter.SearcherFindAdapter;
import com.a16lao.wyh.utils.RxBus;

import com.a16lao.wyh.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.Arrays;


import rx.functions.Action1;

/**
 * date:   2018/6/21 0021 下午 3:32
 * author: caoyan
 * description:
 */

public class SearcherFindFragment extends LazyFragment {
    private SwipeToLoadLayout swipe_search_free;
    private ListView swipe_target;
    private SearcherFindAdapter mAdapter;
    private String[] mString = {"穿越时空的爱恋", "穿越者", "穿越", "穿越时空", "穿越2", "穿越...", "穿越十年"};


    @Override
    public Object setLayout() {
        return R.layout.fragment_searcher_find;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        swipe_search_free = rootView.findViewById(R.id.swipe_search_free);
        swipe_target = rootView.findViewById(R.id.swipe_target);

    }

    @Override
    protected void initData() {
        mAdapter = new SearcherFindAdapter(mActivity,Arrays.asList(mString), "");
        swipe_target.setAdapter(mAdapter);
        mSubscriptions.add(RxBus.getInstance().toObservableSticky(SearcherBean.class).subscribe(searcherBean -> {
            if (mAdapter != null) {
                mAdapter.setKeyWord(searcherBean.getKeyWord());
            }
        }));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
