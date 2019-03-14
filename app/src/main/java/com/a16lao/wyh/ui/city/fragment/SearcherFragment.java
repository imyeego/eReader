package com.a16lao.wyh.ui.city.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.city.adapter.SearchAdapter;
import com.a16lao.wyh.ui.city.adapter.SearchHistoryAdapter;
import com.a16lao.wyh.ui.city.event.RemoveSearchHistoryEvent;
import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.utils.storage.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * date:   2018/6/21 0021 上午 11:40
 * author: caoyan
 * description:
 */

public class SearcherFragment extends LazyFragment{
    private String[] mStrings = {"我的夏威夷之恋", "南派三叔", "故乡的杨梅",
            "我们一直关注用户体验拉萨市反馈阶段还没拿到半年内采用清新明快的设计思想", "废少重生归来", "田字格"};
    private GridView grid_search_hot, grid_search_history;
    private Set<String> historySet;
    private List<String> list = new LinkedList<>();
    private SearchHistoryAdapter historyAdapter;
    private TextView tvHistoryClear, tvHistoryTitle;

    @Override
    public Object setLayout() {
        return R.layout.fragment_searcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        EventBus.getDefault().register(this);

        grid_search_hot = rootView.findViewById(R.id.grid_search_hot);
        grid_search_hot.setAdapter(new SearchAdapter(getActivity(), Arrays.asList(mStrings)));

        grid_search_history = rootView.findViewById(R.id.grid_search_history);
        tvHistoryClear = rootView.findViewById(R.id.tv_history_clear);
        tvHistoryTitle = rootView.findViewById(R.id.tv_history_title);
        setListener();
    }

    private void setListener() {

        tvHistoryClear.setOnClickListener(view -> {
            if (historySet != null) {
                list.clear();
                historyAdapter.notifyDataSetChanged();
                new Thread(() -> {
                    historySet.clear();
                    SPUtils.saveObject("search_history_set", historySet);
                }).start();
                tvHistoryClear.setVisibility(View.GONE);
                tvHistoryTitle.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initData() {

        historyAdapter = new SearchHistoryAdapter(getActivity(), list);
        grid_search_history.setAdapter(historyAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        list.clear();
        historySet =  (LinkedHashSet) SPUtils.getObject("search_history_set");
        if (historySet == null) {
            historySet = new LinkedHashSet<>();
        }
        list.addAll(historySet);
        Collections.reverse(list);

        historyAdapter.notifyDataSetChanged();
        if (list.isEmpty()){
            tvHistoryClear.setVisibility(View.GONE);
            tvHistoryTitle.setVisibility(View.GONE);
        }
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void onRemoveHistoryItem(RemoveSearchHistoryEvent event){
        if (historySet != null) {
            historySet.remove(event.getHistoryItem());
            SPUtils.saveObject("search_history_set", historySet);
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        RemoveSearchHistoryEvent event = EventBus.getDefault().getStickyEvent(RemoveSearchHistoryEvent.class);
        if (event != null) {
            EventBus.getDefault().removeStickyEvent(event);
        }
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
