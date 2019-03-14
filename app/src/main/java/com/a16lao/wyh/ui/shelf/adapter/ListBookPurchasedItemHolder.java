package com.a16lao.wyh.ui.shelf.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;

public class ListBookPurchasedItemHolder extends RecyclerView.ViewHolder {
    private ImageView imgBookCover;
    private RelativeLayout rlLine1;
    private TextView tvBookName;
    private RelativeLayout rlLine2;
    private TextView tvLoadingState;
    private TextView tvBookTag;
    private RelativeLayout rlLine3;
    private TextView tvReadProgress;
    private TextView tvAddShelf;

    public ListBookPurchasedItemHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.list_book_purchased_item, parent, false));
    }

    public ListBookPurchasedItemHolder(View view) {
        super(view);
        imgBookCover = (ImageView) view.findViewById(R.id.img_book_cover);
        rlLine1 = (RelativeLayout) view.findViewById(R.id.rl_line_1);
        tvBookName = (TextView) rlLine1.findViewById(R.id.tv_book_name);
        rlLine2 = (RelativeLayout) view.findViewById(R.id.rl_line_2);
        tvLoadingState = (TextView) rlLine2.findViewById(R.id.tv_loading_state);
        tvBookTag = (TextView) rlLine2.findViewById(R.id.tv_book_tag);
        rlLine3 = (RelativeLayout) view.findViewById(R.id.rl_line_3);
        tvReadProgress = (TextView) rlLine3.findViewById(R.id.tv_read_progress);
        tvAddShelf = (TextView) view.findViewById(R.id.tv_add_shelf);
    }

    public TextView getTvLoadingState() {
        return tvLoadingState;
    }

    public TextView getTvBookName() {
        return tvBookName;
    }

    public RelativeLayout getRlLine3() {
        return rlLine3;
    }

    public RelativeLayout getRlLine1() {
        return rlLine1;
    }

    public RelativeLayout getRlLine2() {
        return rlLine2;
    }

    public TextView getTvReadProgress() {
        return tvReadProgress;
    }

    public ImageView getImgBookCover() {
        return imgBookCover;
    }

    public TextView getTvAddShelf() {
        return tvAddShelf;
    }

    public TextView getTvBookTag() {
        return tvBookTag;
    }
}
