package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.widget.LinearLayout;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/7/12 0012 下午 3:06
 * author: caoyan
 * description:
 */

public class RewardAdapter extends SimpleBaseAdapter<String> {
    private Context mContext;
    private int selectedPosition = -1;

    public RewardAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.grid_reward_item, list);
        this.mContext = mContext;
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        LinearLayout grid_reward_bg = holder.getViewById(R.id.grid_reward_bg);
        if (selectedPosition == position) {

            grid_reward_bg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_c14_border_c15));
        } else {
            grid_reward_bg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_cw_border_c7));
        }
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }

}
