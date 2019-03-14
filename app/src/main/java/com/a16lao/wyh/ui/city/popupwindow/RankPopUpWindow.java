package com.a16lao.wyh.ui.city.popupwindow;

import android.content.Context;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePopupWindow;
import com.a16lao.wyh.ui.city.adapter.RankAdapter;
import com.a16lao.wyh.ui.main.CallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/21 0021 上午 10:15
 * author: caoyan
 * description:
 */

public class RankPopUpWindow extends BasePopupWindow implements AdapterView.OnItemClickListener {

    private List<String> mList;

    private CallBack mCallBack;

    public void setOnCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }


    public RankPopUpWindow(Context context) {
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
        mList.add("周榜");
        mList.add("月榜");
        mList.add("总榜");
        pop.setAdapter(new RankAdapter(getContext(),mList));
        pop.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mCallBack != null) {
            mCallBack.onCallBack(position, mList.get(position));
        }
    }
}


