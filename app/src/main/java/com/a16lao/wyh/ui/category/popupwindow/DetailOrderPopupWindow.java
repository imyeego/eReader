package com.a16lao.wyh.ui.category.popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.ListView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePopupWindow;
import com.a16lao.wyh.ui.main.CallBack;
import com.a16lao.wyh.ui.mine.adapter.DetailAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/23 0023 下午 3:16
 * author: caoyan
 * description:
 */

public class DetailOrderPopupWindow extends BasePopupWindow {
    private List<String> mList;
    private DetailAdapter mAdapter;
    private CallBack mCallBack;

    public void setOnCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public DetailOrderPopupWindow(Context context) {
        super(context);
    }


    @Override
    protected int setLayout() {
        return R.layout.layout_popupwindow_vertical;
    }

    @Override
    protected void initData(View view) {
        ListView pop = view.findViewById(R.id.pop_vertical);
        mList = new ArrayList<>();
        mList.add("人气排序");
        mList.add("畅销排序");
        mList.add("更新排序");
        mList.add("字数排序");
        mList.add("收藏排序");
        mAdapter = new DetailAdapter(getContext(), mList);
        pop.setAdapter(mAdapter);
        mAdapter.setSelectedPosition(0);
        pop.setOnItemClickListener((parent, view1, position, id) -> {
            mAdapter.setSelectedPosition(position);
            if (mCallBack != null) {
                mCallBack.onCallBack(position, mList.get(position));
            }
            dismiss();
        });
    }
}
