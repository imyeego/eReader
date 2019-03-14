package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.a16lao.wyh.bean.city.BookListBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<ListBookPartHolder> {

    private List<BookListBean> list;
    private Context context;

    public BookListAdapter(List<BookListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ListBookPartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListBookPartHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ListBookPartHolder holder, int position) {
        final BookListBean item = list.get(position);
        TextPaint paint = holder.getTextBook().getPaint();
        paint.setFakeBoldText(true);
        holder.getTextBook().setText(item.getBookListName());
        Glide.with(context).load(item.getBookListCover()).into(holder.getIcon());
        holder.getTvBookDescription().setText(item.getBookListDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
