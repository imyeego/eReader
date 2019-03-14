package com.a16lao.wyh.ui.city.adapter;


import android.content.Context;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/5/21 0021 下午 2:53
 * author: caoyan
 * description:
 */

public class ComicMoreAdapter extends SimpleBaseAdapter<String> {

    public ComicMoreAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.list_comic_item, list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        holder.setText(R.id.text_comic_item, s);
    }
}
