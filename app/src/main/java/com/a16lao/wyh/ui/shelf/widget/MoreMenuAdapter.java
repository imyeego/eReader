package com.a16lao.wyh.ui.shelf.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;

import java.util.List;

public class MoreMenuAdapter extends RecyclerView.Adapter<MoreMenuAdapter.MMenuViewHolder>{

    private Context mContext;
    private List<MenuItem> menuItemList;
    private boolean showIcon;
    private MoreMenu moreMenu;
    private MoreMenu.OnMenuItemClickListener onMenuItemClickListener;

    public MoreMenuAdapter(Context context, MoreMenu moreMenu, List<MenuItem> list, boolean showIcon){
        this.mContext = context;
        this.menuItemList = list;
        this.moreMenu = moreMenu;
        this.showIcon = showIcon;

    }

    public void setData(List<MenuItem> data){
        menuItemList = data;
        notifyDataSetChanged();
    }

    public void setShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
        notifyDataSetChanged();
    }

    @Override
    public MMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new MMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MMenuViewHolder holder, int position) {
        final MenuItem menuItem = menuItemList.get(position);
        if (showIcon){
            holder.icon.setVisibility(View.VISIBLE);
            int resId = menuItem.getIcon();
            holder.icon.setImageResource(resId < 0 ? 0 : resId);
        }else{
            holder.icon.setVisibility(View.GONE);
        }

        holder.text.setText(menuItem.getText());
        final int pos = holder.getAdapterPosition();
        holder.container.setOnClickListener(v -> {
            if (onMenuItemClickListener != null) {
                moreMenu.dismiss();
                onMenuItemClickListener.onMenuItemClick(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    class MMenuViewHolder extends RecyclerView.ViewHolder{
        ViewGroup container;
        ImageView icon;
        TextView text;

        MMenuViewHolder(View itemView){
            super(itemView);
            container = (ViewGroup) itemView;
            icon = itemView.findViewById(R.id.icon_list_selected);
            text = itemView.findViewById(R.id.text);
        }
    }

    public void setOnMenuItemClickListener(MoreMenu.OnMenuItemClickListener listener){
        this.onMenuItemClickListener = listener;
    }
}
