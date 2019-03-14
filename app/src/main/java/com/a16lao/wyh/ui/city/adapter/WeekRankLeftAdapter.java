package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/6/12 0012 下午 4:16
 * author: caoyan
 * description:
 */

public class WeekRankLeftAdapter extends SimpleBaseAdapter<String> {
    private int selectedPosition = -1;
    private Context context;

    public WeekRankLeftAdapter(Context context, List<String> list) {
        super(context, R.layout.list_week_rank_left, list);
        this.context = context;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }


    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        holder.setText(R.id.text_rank_left, s);
        ImageView icon_rank_left = holder.getViewById(R.id.icon_rank_left);
        LinearLayout layout_rank_left = holder.getViewById(R.id.layout_rank_left);
        if (selectedPosition == position) {
            icon_rank_left.setVisibility(View.VISIBLE);
            layout_rank_left.setBackgroundColor(Color.WHITE);
        } else {
            icon_rank_left.setVisibility(View.INVISIBLE);
            layout_rank_left.setBackgroundColor(context.getResources().getColor(R.color.color_8));
        }
    }
}
