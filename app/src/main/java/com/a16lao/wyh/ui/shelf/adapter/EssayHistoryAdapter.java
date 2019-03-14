package com.a16lao.wyh.ui.shelf.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.a16lao.wyh.R;
import com.a16lao.wyh.bean.shelf.EssayHistoryBean;

import java.util.List;

public class EssayHistoryAdapter extends RecyclerView.Adapter<ListEssayHistoryItemHolder>{

    private List<EssayHistoryBean> list;
    private Context context;

    public EssayHistoryAdapter(List<EssayHistoryBean> list) {
        this.list = list;
    }

    @Override
    public ListEssayHistoryItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ListEssayHistoryItemHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ListEssayHistoryItemHolder holder, int position) {
        EssayHistoryBean item = list.get(position);
        holder.getTvEssayTitle().setText(item.getTitle());
        TextPaint paint = holder.getTvEssayTitle().getPaint();
        paint.setFakeBoldText(true);
        holder.getTvEssayAbstract().setText(item.getSubTitle());
        if (item.isAddShelf()){
            holder.getTvAddShelf().setText("已在书架");
            holder.getTvAddShelf().setTextColor(ContextCompat.getColor(context, R.color.color_3));
        }
        else
            holder.getTvAddShelf().setText("加入书架");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
