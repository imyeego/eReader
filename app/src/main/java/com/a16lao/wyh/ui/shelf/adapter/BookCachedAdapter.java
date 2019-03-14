package com.a16lao.wyh.ui.shelf.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a16lao.wyh.R;
import com.a16lao.wyh.bean.shelf.BookCachedBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class BookCachedAdapter extends RecyclerView.Adapter<ListBookCachedItemHolder> {

    private Context context;
    private List<BookCachedBean> list;
    private boolean isScrolling;


    public interface OnItemClickLisener{
        void onItemClick(View view, int pos);
        void onItemLongClick(View view, int pos);
    }
    private OnItemClickLisener onItemClickLisener;

    public void setOnItemClickLister(OnItemClickLisener onItemClickLister){
        this.onItemClickLisener = onItemClickLister;
    }

    public BookCachedAdapter(Context context, List<BookCachedBean> list) {
        this.context = context;
        this.list = list;
    }



    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }



    @Override
    public ListBookCachedItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ListBookCachedItemHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ListBookCachedItemHolder holder, int position) {
        final BookCachedBean item = list.get(position);
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
        holder.getTvDownloadState().setText(item.getDownloadState());
        if (item.getDownloadState().equals("下载中")){
            holder.getTvDownloadState().setTextColor(ContextCompat.getColor(context, R.color.color_11));
        }else{
            holder.getTvDownloadState().setTextColor(ContextCompat.getColor(context, R.color.color_4));
        }
        holder.getTvDownloadTime().setText(item.getDownloadTime());
        if (item.isChapter())
            holder.getTvIsChapter().setText("章节");
        else
            holder.getTvIsChapter().setText("全本");

        if (item.isSelected()){
            holder.getIvIsSelected().setVisibility(View.VISIBLE);
            holder.getTvStart().setVisibility(View.GONE);
            holder.itemView.setOnClickListener(view -> {
                int layoutPosition = holder.getLayoutPosition();
                onItemClickLisener.onItemClick(holder.itemView, layoutPosition);
            });
            if (item.isClicked()){
                holder.getIvIsSelected().setBackgroundResource(R.drawable.selected);
            }else{
                holder.getIvIsSelected().setBackgroundResource(R.drawable.unselected);
            }
        }else{
            holder.getIvIsSelected().setVisibility(View.GONE);
            holder.getTvStart().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void delete(int pos){
        list.remove(pos);
        notifyItemRemoved(pos);
    }
}
