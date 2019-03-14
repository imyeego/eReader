package com.a16lao.wyh.ui.shelf.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a16lao.wyh.R;
import com.a16lao.wyh.bean.shelf.PurchasedBookBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class ListPurchasedBookAdapter extends RecyclerView.Adapter<ListBookPurchasedItemHolder> {

    private Context context;
    private List<PurchasedBookBean> list;
    private boolean isScrolling;

    public ListPurchasedBookAdapter(Context context, List<PurchasedBookBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    @Override
    public ListBookPurchasedItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ListBookPurchasedItemHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ListBookPurchasedItemHolder holder, int position) {
        final PurchasedBookBean item = list.get(position);
        if (!isScrolling){
            Glide.with(context).load(item.getImgUrl()).into(holder.getImgBookCover());
        }else{
            Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.getImgBookCover());
        }

        TextPaint paint = holder.getTvBookName().getPaint();
        paint.setFakeBoldText(true);
        holder.getTvBookName().setText(item.getBookName());
        holder.getTvLoadingState().setText(item.getIsFinished());
        holder.getTvBookTag().setText(item.getTag());
        holder.getTvReadProgress().setText(item.getProgress());
        if (item.isAddedShelf()){
            holder.getTvAddShelf().setText("已在书架");
            holder.getTvAddShelf().setTextColor(ContextCompat.getColor(context, R.color.color_4));
            holder.getTvAddShelf().setClickable(false);

        }else{
            holder.getTvAddShelf().setText("加入书架");
            holder.getTvAddShelf().setTextColor(ContextCompat.getColor(context, R.color.color_1));
            holder.getTvAddShelf().setOnClickListener(view -> {
                list.get(position).setAddedShelf(true);
                notifyItemChanged(position);
            });
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
