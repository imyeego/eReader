package com.a16lao.wyh.ui.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;

import java.util.List;

/**
 * date:   2018/5/31 0031 下午 1:36
 * author: caoyan
 * description:
 */

public class BookBuy2Adapter extends SimpleBaseAdapter<String> {
    protected Context mContext;
    private boolean type;

    public BookBuy2Adapter(Context mContext, List<String> list, boolean type) {
        super(mContext, R.layout.list_book_buy_item2, list);
        this.mContext = mContext;
        this.type = type;
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        ImageView icon_book_buy = holder.getViewById(R.id.icon_book_buy);
        icon_book_buy.setVisibility(type ? View.VISIBLE : View.GONE);

    }
}
