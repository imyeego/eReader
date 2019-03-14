package com.a16lao.wyh.ui.shelf.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePopupWindow;
import com.a16lao.wyh.ui.main.CallBack;
import com.a16lao.wyh.ui.shelf.adapter.MoreAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/21 0021 上午 10:15
 * author: caoyan
 * description:
 */

public class MorePopUpWindow extends BasePopupWindow implements AdapterView.OnItemClickListener {

    private List<String> mList;

    private CallBack mCallBack;

    public void setOnCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }


    public MorePopUpWindow(Context context) {
        super(context);
    }

    @Override
    protected int setLayout() {

        return R.layout.layout_popupwindow;
    }

    @Override
    protected void initData(View view) {
        ListView pop = view.findViewById(R.id.pop);
        mList = new ArrayList<>();
        mList.add("扫一扫");
        mList.add("批量管理");
        mList.add("导入本地书籍");
        mList.add("已购书籍");
        mList.add("书籍缓存");
        mList.add("历史评论");
        mList.add("夜间模式");
        pop.setAdapter(new MoreAdapter(getContext(),mList));
        pop.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mCallBack != null) {
            mCallBack.onCallBack(position, mList.get(position));
        }
    }
}


