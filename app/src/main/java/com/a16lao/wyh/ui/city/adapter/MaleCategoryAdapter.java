package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/6/12 0012 下午 2:59
 * author: caoyan
 * description:
 */

public class MaleCategoryAdapter extends SimpleBaseAdapter<String> {


    public MaleCategoryAdapter(Context mContext, List<String> list) {
        super(mContext,R.layout.grid_male_category,list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {

    }
}
