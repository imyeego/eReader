package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.MultiBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/7/9 0009 上午 11:23
 * author: caoyan
 * description:
 */

public class SelectionAdapter extends MultiBaseAdapter<String> {
    private List<String> mList;

    public SelectionAdapter(Context mContext, List<String> list) {
        super(mContext, new int[]{R.layout.list_selection_hot, R.layout.list_selection_recommend,
                R.layout.list_selection_prose, R.layout.list_selection_recommend,
                R.layout.list_selection_popular}, list);
        this.mList = list;
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position, int layoutIndex) {
        switch (layoutIndex) {
            case 0:

                break;
            case 1:
                break;
            case 2:

                break;
            case 3:
                break;
            case 4:

                break;

        }
    }


    @Override
    protected int getLayoutIndex(String entity) {
        if (entity.length() == 1) {
            return 0;
        } else if (entity.length() == 2) {
            return 1;
        } else if (entity.length() == 3) {
            return 2;
        } else if (entity.length() == 4) {
            return 3;
        } else {
            return 4;
        }


    }
}
