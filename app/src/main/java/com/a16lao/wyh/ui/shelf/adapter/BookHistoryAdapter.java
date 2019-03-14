package com.a16lao.wyh.ui.shelf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.bean.shelf.BookHistoryBean;
import com.bumptech.glide.Glide;

import java.util.List;

public class BookHistoryAdapter extends RecyclerView.Adapter<BookHistoryAdapter.BookHistoryViewHolder>{

    private Context context;
    private List<BookHistoryBean> list;
    private boolean isScrolling;

    public BookHistoryAdapter(Context context, List<BookHistoryBean> list) {
        this.list = list;
        this.context = context;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    @Override
    public BookHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book_history_item, parent, false);
        BookHistoryViewHolder viewHolder = new BookHistoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookHistoryViewHolder holder, int position) {
        final BookHistoryBean item = list.get(position);
        if (!isScrolling){
            Glide.with(context).load(item.getImgUrl()).into(holder.imgBookCover);
        }else{
            Glide.with(context).load(R.drawable.ic_list_plus).into(holder.imgBookCover);
        }
        TextPaint paint = holder.tvBookName.getPaint();
        paint.setFakeBoldText(true);
        holder.tvBookName.setText(item.getBookName());
        holder.tvLoadingState.setText(item.getIsFinished());
        holder.tvBookTag.setText(item.getTag());
        holder.tvProgress.setText(item.getProgress());
        if (item.isAddedShelf()){
            holder.tvIsAddedShelf.setText("已在书架");
        }else{
            holder.tvIsAddedShelf.setText("加入书架");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BookHistoryViewHolder extends RecyclerView.ViewHolder{

        ImageView imgBookCover;
        TextView tvBookName, tvLoadingState, tvBookTag, tvProgress, tvIsAddedShelf;

        BookHistoryViewHolder(View itemView){
            super(itemView);
            imgBookCover = itemView.findViewById(R.id.img_book_cover);
            tvBookName = itemView.findViewById(R.id.tv_book_name);
            tvLoadingState = itemView.findViewById(R.id.tv_loading_state);
            tvBookTag = itemView.findViewById(R.id.tv_book_tag);
            tvProgress = itemView.findViewById(R.id.tv_read_progress);
            tvIsAddedShelf = itemView.findViewById(R.id.tv_add_shelf);
        }
    }
}
