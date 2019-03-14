package com.a16lao.wyh.ui.shelf.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a16lao.wyh.R;

public class ListEssayHistoryItemHolder extends RecyclerView.ViewHolder {
    private TextView tvEssayTitle;
    private TextView tvEssayAbstract;
    private TextView tvAddShelf;

    public ListEssayHistoryItemHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.list_essay_history_item, parent, false));
    }

    public ListEssayHistoryItemHolder(View view) {
        super(view);
        tvEssayTitle = (TextView) view.findViewById(R.id.tv_essay_title);
        tvEssayAbstract = (TextView) view.findViewById(R.id.tv_essay_abstract);
        tvAddShelf = (TextView) view.findViewById(R.id.tv_add_shelf);
    }

    public TextView getTvAddShelf() {
        return tvAddShelf;
    }

    public TextView getTvEssayTitle() {
        return tvEssayTitle;
    }

    public TextView getTvEssayAbstract() {
        return tvEssayAbstract;
    }
}
