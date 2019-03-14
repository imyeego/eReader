package com.a16lao.wyh.ui.city.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;

public class ListBookPartHolder extends RecyclerView.ViewHolder {
    private ImageView icon;
    private TextView textBook;
    private TextView tvBookDescription;

    public ListBookPartHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.list_book_part, parent, false));
    }

    public ListBookPartHolder(View view) {
        super(view);
        icon = (ImageView) view.findViewById(R.id.icon);
        textBook = (TextView) view.findViewById(R.id.text_book);
        tvBookDescription = (TextView) view.findViewById(R.id.tv_book_description);
    }

    public ImageView getIcon() {
        return icon;
    }

    public TextView getTvBookDescription() {
        return tvBookDescription;
    }

    public TextView getTextBook() {
        return textBook;
    }
}
