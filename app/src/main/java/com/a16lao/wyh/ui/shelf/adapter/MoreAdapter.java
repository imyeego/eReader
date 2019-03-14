package com.a16lao.wyh.ui.shelf.adapter;


import android.content.Context;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/5/21 0021 下午 2:53
 * author: caoyan
 * description:
 */

public class MoreAdapter extends SimpleBaseAdapter<String> {

    public MoreAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.list_item,list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        TextView text = holder.getViewById(R.id.text);
        text.setText(s);
    }
}
