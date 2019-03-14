package com.a16lao.wyh.ui.city.adapter;


import android.content.Context;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/6/20 0020 下午 4:38
 * author: caoyan
 * description:
 */

public class SearchAdapter extends SimpleBaseAdapter<String> {


    public SearchAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.grid_search_item, list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {

        holder.setText(R.id.text_search_hot_name, s);
        holder.setText(R.id.text_search_hot_rank, position + 1 + "");
        if (position == 0) {
            holder.getViewById(R.id.text_search_hot_rank).setBackgroundResource(R.drawable.bg_search_one);
        } else if (position == 1) {
            holder.getViewById(R.id.text_search_hot_rank).setBackgroundResource(R.drawable.bg_search_two);
        } else if (position == 2) {
            holder.getViewById(R.id.text_search_hot_rank).setBackgroundResource(R.drawable.bg_search_three);
        } else {
            holder.getViewById(R.id.text_search_hot_rank).setBackgroundResource(R.drawable.bg_search_gray);
        }

    }
}
