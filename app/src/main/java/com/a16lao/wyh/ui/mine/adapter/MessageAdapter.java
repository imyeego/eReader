package com.a16lao.wyh.ui.mine.adapter;

import android.content.Context;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/5/31 0031 下午 1:36
 * author: caoyan
 * description:
 */

public class MessageAdapter extends SimpleBaseAdapter<String> {
    public MessageAdapter(Context mContext,List<String> list) {
        super(mContext,R.layout.list_message_item,list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {


    }
}
