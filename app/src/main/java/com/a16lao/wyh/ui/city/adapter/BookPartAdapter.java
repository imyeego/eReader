package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;

import java.util.List;


/**
 * date:   2018/6/13 0013 下午 5:21
 * author: caoyan
 * description:
 */

public class BookPartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> list;
    private Context mContext;
    private static final int HEAD_TYPE = 00001;
    private static final int BODY_TYPE = 00002;
    private int headCount = 1;
    private View mView;

    public BookPartAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }


    private boolean isHead(int position) {
        return headCount != 0 && position < headCount;
    }

    public void setHeaderView(View headerView) {
        this.mView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mView;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHead(position)) {
            return HEAD_TYPE;
        } else {
            return BODY_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case HEAD_TYPE:
                return new HeadViewHolder(mView);
            case BODY_TYPE:
                return new ViewHolder(View.inflate(mContext, R.layout.list_book_part, null));

        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HeadViewHolder) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) ((HeadViewHolder) viewHolder).rl.getLayoutParams();
            params.setFullSpan(true);
        } else {
            //        GlideUtils.loadRes(mContext, R.drawable.a, viewHolder.icon);
            ((ViewHolder) viewHolder).text_book.setText(list.get(i - 1));
        }


    }

    @Override
    public int getItemCount() {
        return headCount + list.size();
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl;

        public HeadViewHolder(View itemView) {
            super(itemView);
            rl = itemView.findViewById(R.id.rl);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView text_book;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            text_book = itemView.findViewById(R.id.text_book);
        }
    }
}
