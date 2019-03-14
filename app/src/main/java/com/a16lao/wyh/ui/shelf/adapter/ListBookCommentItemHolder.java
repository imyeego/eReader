package com.a16lao.wyh.ui.shelf.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;

public class ListBookCommentItemHolder extends RecyclerView.ViewHolder {
    private TextView tvDate;
    private TextView tvTime;
    private ImageView imgDelete;
    private TextView tvContent;
    private ImageView imgBookCover;
    private TextView tvBookName;
    private TextView tvChapterName;

    public ListBookCommentItemHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.list_book_comment_item, parent, false));
    }

    public ListBookCommentItemHolder(View view) {
        super(view);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        imgDelete = (ImageView) view.findViewById(R.id.img_delete);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        imgBookCover = (ImageView) view.findViewById(R.id.img_book_cover);
        tvBookName = (TextView) view.findViewById(R.id.tv_book_name);
        tvChapterName = view.findViewById(R.id.tv_chapter_name);
    }

    public ImageView getImgBookCover() {
        return imgBookCover;
    }

    public TextView getTvBookName() {
        return tvBookName;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public ImageView getImgDelete() {
        return imgDelete;
    }

    public TextView getTvChapterName() {
        return tvChapterName;
    }
}
