package com.a16lao.wyh.ui.category.adapter;

import android.content.Context;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.bean.category.ChooseTypeBean;
import com.a16lao.wyh.widget.NoScrollGridView;

import java.util.List;

/**
 * date:   2018/5/31 0031 下午 1:36
 * author: caoyan
 * description:
 */

public class ChooseTypeAdapter extends SimpleBaseAdapter<String> {
    protected Context mContext;
    private List<List<ChooseTypeBean>> mList;

    public ChooseTypeAdapter(Context mContext, List<String> list, List<List<ChooseTypeBean>> mList) {
        super(mContext, R.layout.grid_choose_type, list);
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        holder.setText(R.id.text_choose_type, s);
        NoScrollGridView grid_choose_type = holder.getViewById(R.id.grid_choose_type);
        ChooseType2Adapter mAdapter = new ChooseType2Adapter(mContext, mList.get(position));
        grid_choose_type.setAdapter(mAdapter);

        grid_choose_type.setOnItemClickListener((parent, view, position1, id) -> {
            mAdapter.setSelectedPosition(position1);
        });
    }
}
