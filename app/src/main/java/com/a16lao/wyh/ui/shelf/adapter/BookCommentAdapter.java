package com.a16lao.wyh.ui.shelf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a16lao.wyh.bean.shelf.BookCommentBean;
import com.a16lao.wyh.widget.CommonDialog;
import com.bumptech.glide.Glide;

import java.util.List;

public class BookCommentAdapter extends RecyclerView.Adapter<ListBookCommentItemHolder> {

    private Context context;
    private List<BookCommentBean> list;
    private boolean isScrolling;
    private CommonDialog dialog;

    public BookCommentAdapter(Context context, List<BookCommentBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    @Override
    public ListBookCommentItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       context = parent.getContext();
        return new ListBookCommentItemHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ListBookCommentItemHolder holder, int position) {
        final BookCommentBean item = list.get(position);

        holder.getTvDate().setText(item.getDate());
        holder.getTvTime().setText(item.getTime());
        holder.getTvContent().setText(item.getContent());
        Glide.with(context).load(item.getImgUrl()).into(holder.getImgBookCover());
        holder.getTvBookName().setText(item.getBookName());
        holder.getTvChapterName().setText(item.getChapterName() + " | " + item.getLoadingState());
        final int pos = position;
        holder.getImgDelete().setOnClickListener(view -> {
            CommonDialog.Builder builder = new CommonDialog.Builder(context).setTitle("提示")
                    .setContent("确定要删除评论吗？")
                    .setLeftClickListener("取消", () -> dialog.dismiss())
                    .setRightClickListener("确定", () -> {
                        delete(pos);
                        dialog.dismiss();
                    });
            dialog = builder.create();
            dialog.show();
        });

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
