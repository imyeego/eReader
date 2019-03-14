package com.a16lao.wyh.ui.city.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.city.adapter.WeekRankAdapter;
import com.a16lao.wyh.ui.city.adapter.WeekRankLeftAdapter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * date:   2018/6/12 0012 上午 10:50
 * author: caoyan
 * description:
 */

public class WeekRankFragment extends LazyFragment {
    private ListView list_week_rank, list_week_item;
    private String[] mStrings = {"热销榜", "新书榜", "点击榜"};
    private List<String> mList;
    private WeekRankLeftAdapter mWeekRankLeftAdapter;

    @Override
    public Object setLayout() {
        return R.layout.fragment_week_rank;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        list_week_rank = rootView.findViewById(R.id.list_week_rank);
        list_week_item = rootView.findViewById(R.id.list_week_item);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("1.道岳独尊");
        }
        mWeekRankLeftAdapter = new WeekRankLeftAdapter(mActivity, Arrays.asList(mStrings));
        list_week_rank.setAdapter(mWeekRankLeftAdapter);
        list_week_item.setAdapter(new WeekRankAdapter(mActivity, mList));
        mWeekRankLeftAdapter.setSelectedPosition(0);
        list_week_rank.setOnItemClickListener((parent, view, position, id) -> mWeekRankLeftAdapter.setSelectedPosition(position));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
