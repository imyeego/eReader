package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.a16lao.wyh.bean.bookmarket.CommentBean;

import java.util.List;


public class CommentsAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CommentBean> list;
    private LayoutInflater inflater;

    public CommentsAdapter(Context context, List<CommentBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
