package com.a16lao.wyh.ui.category.adapter;

import android.content.Context;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.bean.category.ChooseTypeBean;

import java.util.List;

/**
 * date:   2018/5/31 0031 下午 1:36
 * author: caoyan
 * description:
 */

public class ChooseType2Adapter extends SimpleBaseAdapter<ChooseTypeBean> {
    protected Context mContext;
    private int selectedPosition = 0;
    private List<ChooseTypeBean> mList;

    public ChooseType2Adapter(Context mContext, List<ChooseTypeBean> list) {
        super(mContext, R.layout.grid_choose_type_item, list);
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, ChooseTypeBean s, int position) {
        TextView text_choose_type_item = holder.getViewById(R.id.text_choose_type_item);
        text_choose_type_item.setText(s.getString());
        if (s.isFlag()) {
            text_choose_type_item.setBackgroundResource(R.drawable.border_c1_r2);
            text_choose_type_item.setTextColor(mContext.getResources().getColor(R.color.color_1));
        } else {
            text_choose_type_item.setBackgroundResource(R.drawable.border_c7_r2);
            text_choose_type_item.setTextColor(mContext.getResources().getColor(R.color.color_4));
        }

    }


    public void setSelectedPosition(int position) {
        for (int i = 0; i < mList.size(); i++) {
            if (position == i) {
                mList.get(position).setFlag(true);
            } else {
                mList.get(i).setFlag(false);
            }
        }
        this.notifyDataSetChanged();
    }
}
