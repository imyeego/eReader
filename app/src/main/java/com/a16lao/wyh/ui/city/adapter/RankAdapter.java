package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;


import java.util.List;

/**
 * date:   2018/5/21 0021 下午 2:53
 * author: caoyan
 * description:
 */

public class RankAdapter extends SimpleBaseAdapter<String> {
    private int selectedPosition = 0;

    public RankAdapter(Context mContext,List<String> list) {

        super(mContext, R.layout.list_rank_item,list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        holder.setText(R.id.text_book_rank_item, s);
        ImageView icon_book_rank = holder.getViewById(R.id.icon_book_rank);

        if (selectedPosition == position) {
            icon_book_rank.setVisibility(View.VISIBLE);
        } else {
            icon_book_rank.setVisibility(View.GONE);
        }
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

}
