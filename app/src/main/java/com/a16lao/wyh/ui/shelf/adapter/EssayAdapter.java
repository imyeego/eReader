package com.a16lao.wyh.ui.shelf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.bean.shelf.Essay;
import com.a16lao.wyh.utils.dimen.DimenUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * 书架页面短文列表适配器
 *
 */

public class EssayAdapter extends RecyclerView.Adapter<EssayAdapter.MyHolder> implements AdapterListType<Essay>{

    private List<Essay> list;
    private Context context;
    private DataChangedListener listener;

    public EssayAdapter(List<Essay> list, Context context){
        this.context = context;
        this.list = list;
    }

    public interface DataChangedListener{
        void dataSetChanged();
    }

    public void setListener(DataChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public List<Essay> getList() {
        return list;
    }

    @Override
    public void notifyDataChanged() {
        listener.dataSetChanged();
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_essay_item, parent, false);
        MyHolder viewHolder = new MyHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Essay item = list.get(position);
        if (!item.getImg().isEmpty()){
            Glide.with(context).load(item.getImg())
                    .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(5)))
                    .into(holder.imgEssay);
        }else
            holder.imgEssay.setVisibility(View.GONE);

        holder.tvEssayTitle.setText(item.getTitle());
        holder.tvEssayAbstract.setText(item.getSubTitle());
        holder.tvAuthorName.setText(item.getAuthorName());
        holder.tvCount.setText(item.getCount());

        if (item.isSelected()){

            holder.ivSelected.setVisibility(View.VISIBLE);
            if (item.isClicked()){
                holder.ivSelected.setBackgroundResource(R.drawable.selected);
            }else{
                holder.ivSelected.setBackgroundResource(R.drawable.unselected);
            }
        }else{
            holder.ivSelected.setVisibility(View.GONE);
        }

        Glide.with(context).load(item.getAuthorAvatar())
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(DimenUtils.dip2px(context, 10))))
                .into(holder.imgAuthorAvatar);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView imgEssay, imgAuthorAvatar, ivSelected;
        TextView tvEssayTitle, tvEssayAbstract, tvAuthorName, tvCount;
        public MyHolder(View itemView) {
            super(itemView);
            imgEssay = itemView.findViewById(R.id.img_essay);
            imgAuthorAvatar = itemView.findViewById(R.id.img_author_avatar);
            ivSelected = itemView.findViewById(R.id.img_selected);
            tvEssayTitle = itemView.findViewById(R.id.tv_essay_title);
            tvEssayAbstract = itemView.findViewById(R.id.tv_essay_abstract);
            tvAuthorName = itemView.findViewById(R.id.tv_author_name);
            tvCount = itemView.findViewById(R.id.tv_count);
        }
    }

}
