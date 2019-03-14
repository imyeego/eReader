package com.a16lao.wyh.ui.mine.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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

public class DetailAdapter extends SimpleBaseAdapter<String> {

    private int selectedPosition = -1;

    public DetailAdapter(Context mContext,List<String> list) {
        super(mContext,R.layout.list_item,list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        TextView text = holder.getViewById(R.id.text);
        text.setText(s);
        ImageView icon_list_selected = holder.getViewById(R.id.icon_list_selected);

        if (selectedPosition == position) {
            icon_list_selected.setVisibility(View.VISIBLE);
        } else {
            icon_list_selected.setVisibility(View.GONE);
        }
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

}
