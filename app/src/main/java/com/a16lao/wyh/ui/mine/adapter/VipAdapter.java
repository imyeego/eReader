package com.a16lao.wyh.ui.mine.adapter;

import android.content.Context;
import android.widget.LinearLayout;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/7/16 0016 下午 7:57
 * author: caoyan
 * description:
 */

public class VipAdapter extends SimpleBaseAdapter<String> {
    private int selectedPosition = -1;

    public VipAdapter(Context mContext,  List<String> list) {
        super(mContext, R.layout.grid_vip_item, list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        LinearLayout view_vip_bg = holder.getViewById(R.id.view_vip_bg);
        view_vip_bg.setBackgroundResource(selectedPosition == position ? R.drawable.bg_c14_border_c12 : R.drawable.border_c7);
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }
}
