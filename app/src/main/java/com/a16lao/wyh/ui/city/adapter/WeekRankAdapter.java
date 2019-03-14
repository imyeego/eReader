package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import com.a16lao.wyh.utils.StringUtils;

import java.util.List;

/**
 * date:   2018/6/12 0012 上午 11:12
 * author: caoyan
 * description:
 */

public class WeekRankAdapter extends SimpleBaseAdapter<String> {
    private Context mContext;


    public WeekRankAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.list_week_rank,list);
        this.mContext = mContext;
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        TextView text_week_title = holder.getViewById(R.id.text_week_title);
        if (position == 0) {
            text_week_title.setText(StringUtils.setColoredString(s, 0, 2, mContext.getResources().getColor(R.color.color_13)));
        } else if (position == 1) {
            text_week_title.setText(StringUtils.setColoredString(s, 0, 2, mContext.getResources().getColor(R.color.color_9)));
        } else if (position == 2) {
            text_week_title.setText(StringUtils.setColoredString(s, 0, 2, mContext.getResources().getColor(R.color.color_11)));
        } else {
            text_week_title.setText(StringUtils.setColoredString(s, 0, 2, Color.BLACK));
        }
    }
}
