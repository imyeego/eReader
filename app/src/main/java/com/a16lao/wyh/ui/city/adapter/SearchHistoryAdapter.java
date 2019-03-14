package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.view.View;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.ui.city.event.RemoveSearchHistoryEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * date:   2018/6/20 0020 下午 4:38
 * author: caoyan
 * description:
 */

public class SearchHistoryAdapter extends SimpleBaseAdapter<String>{


    public SearchHistoryAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.grid_search_history_item,list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        holder.setText(R.id.text_search_history, s);
        holder.setOnClickListner(R.id.icon_search_history, view -> {
            EventBus.getDefault().postSticky(new RemoveSearchHistoryEvent(mList.get(position)));
            deleteItem(position);
                });

    }


}
