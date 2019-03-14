package com.a16lao.wyh.ui.main.adapter;

import android.content.Context;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.bean.shelf.BookList;

import java.util.List;


/**
 * date:   2018/5/16 0016 上午 11:57
 * author: caoyan
 * description:
 */

public class MainAdapter extends SimpleBaseAdapter<BookList> {

    public MainAdapter(Context mContext, List<BookList> list) {
        super(mContext, R.layout.list_item, list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, BookList bookList, int position) {
        holder.setText(R.id.text, bookList.getBookname());
    }
}
