package com.a16lao.wyh.ui.shelf.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.widget.RoundTextView;

public class ListBookCachedItemHolder extends RecyclerView.ViewHolder {
    private ImageView imgBookCover;
    private TextView tvBookName;
    private TextView tvDownloadState;
    private TextView tvLoadingState;
    private TextView tvBookTag;
    private TextView tvDownloadTime;
    private TextView tvIsChapter;
    private RoundTextView tvStart;
    private ImageView ivIsSelected;

    public ListBookCachedItemHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.list_book_cached_item, parent, false));
    }

    public ListBookCachedItemHolder(View view) {
        super(view);
        imgBookCover = (ImageView) view.findViewById(R.id.img_book_cover);
        tvBookName = (TextView) view.findViewById(R.id.tv_book_name);
        tvDownloadState = (TextView) view.findViewById(R.id.tv_download_state);
        tvLoadingState = (TextView) view.findViewById(R.id.tv_loading_state);
        tvBookTag = (TextView) view.findViewById(R.id.tv_book_tag);
        tvDownloadTime = (TextView) view.findViewById(R.id.tv_download_time);
        tvIsChapter = (TextView) view.findViewById(R.id.tv_is_chapter);
        tvStart = view.findViewById(R.id.tv_start);
        ivIsSelected = view.findViewById(R.id.img_selected);
    }

    public ImageView getImgBookCover() {
        return imgBookCover;
    }

    public TextView getTvDownloadTime() {
        return tvDownloadTime;
    }

    public TextView getTvLoadingState() {
        return tvLoadingState;
    }

    public TextView getTvBookName() {
        return tvBookName;
    }

    public TextView getTvDownloadState() {
        return tvDownloadState;
    }

    public TextView getTvBookTag() {
        return tvBookTag;
    }

    public RoundTextView getTvStart() {
        return tvStart;
    }

    public TextView getTvIsChapter() {
        return tvIsChapter;
    }

    public ImageView getIvIsSelected() {
        return ivIsSelected;
    }
}
