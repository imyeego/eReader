package com.a16lao.wyh.ui.category.adapter;

import android.content.Context;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/7/18 0018 下午 3:21
 * author: caoyan
 * description:
 */

public class MaleCategoryDetailAdapter extends SimpleBaseAdapter<String> {
    public MaleCategoryDetailAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.grid_male_category_item, list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {

    }
}
