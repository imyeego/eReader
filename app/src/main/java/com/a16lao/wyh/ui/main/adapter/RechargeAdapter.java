package com.a16lao.wyh.ui.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/5/31 0031 上午 10:51
 * author: caoyan
 * description:
 */

public class RechargeAdapter extends SimpleBaseAdapter<String> {

    public int clickPosition = 0;


    public RechargeAdapter(Context mContext, List<String> list) {
        super(mContext,R.layout.list_item,list);
    }


    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        TextView text = holder.getViewById(R.id.text);
        text.setTextColor(clickPosition == position ? Color.BLUE : Color.RED);

    }
}
