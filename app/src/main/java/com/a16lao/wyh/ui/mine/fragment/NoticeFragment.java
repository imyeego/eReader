package com.a16lao.wyh.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.mine.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/7/4 0004 下午 4:58
 * author: caoyan
 * description:通知
 */

public class NoticeFragment extends LazyFragment {
    private ListView list_notice;
    private List<String> mList;

    @Override
    public Object setLayout() {
        return R.layout.fragment_notice;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        list_notice = rootView.findViewById(R.id.list_notice);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            mList.add(1 + "");
        }
        list_notice.setAdapter(new MessageAdapter(getActivity(), mList));
        list_notice.setOnItemClickListener((parent, view, position, id) -> {

        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
