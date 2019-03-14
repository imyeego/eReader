package com.a16lao.wyh.ui.shelf.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;

public class ListEssayCommentItemHolder extends RecyclerView.ViewHolder {
    private TextView tvDate;
    private TextView tvTime;
    private ImageView imgDelete;
    private TextView tvContent;
    private TextView tvEssayTitle;

    public ListEssayCommentItemHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.list_essay_comment_item, parent, false));
    }

    public ListEssayCommentItemHolder(View view) {
        super(view);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        imgDelete = (ImageView) view.findViewById(R.id.img_delete);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvEssayTitle = view.findViewById(R.id.tv_essay_title);
    }

    public ImageView getImgDelete() {
        return imgDelete;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public TextView getTvEssayTitle() {
        return tvEssayTitle;
    }
}
