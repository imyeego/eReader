package com.a16lao.wyh.ui.mine.adapter;

import android.content.Context;
import android.content.Intent;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.ui.mine.activity.BookBuyDetailActivity;
import com.a16lao.wyh.widget.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/31 0031 下午 1:36
 * author: caoyan
 * description:
 */

public class RewardDetailAdapter extends SimpleBaseAdapter<String> {
    protected Context mContext;
    private List<String> mList;

    public RewardDetailAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.list_book_buy_item, list);
        this.mContext = mContext;
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        holder.setText(R.id.text_book_buy_month, "2018.4月");
        NoScrollListView list_book_buy_item = holder.getViewById(R.id.list_book_buy_item);
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        list_book_buy_item.setAdapter(new BookBuy2Adapter(mContext, mList, false));
    }
}
