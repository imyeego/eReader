package com.a16lao.wyh.ui.shelf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.a16lao.wyh.bean.shelf.EssayCommentBean;
import com.a16lao.wyh.widget.CommonDialog;

import java.util.List;

public class EssayCommentAdapter extends RecyclerView.Adapter<ListEssayCommentItemHolder> {

    private Context context;
    private List<EssayCommentBean> list;
    private CommonDialog dialog;

    public EssayCommentAdapter(Context context, List<EssayCommentBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ListEssayCommentItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ListEssayCommentItemHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ListEssayCommentItemHolder holder, int position) {
        final EssayCommentBean item = list.get(position);
        holder.getTvDate().setText(item.getDate());
        holder.getTvTime().setText(item.getTime());
        holder.getTvContent().setText(item.getContent());

        holder.getTvEssayTitle().setText("《" + item.getEssayTitle() + "》");
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
