package com.a16lao.wyh.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * date:   2018/5/16 0016 上午 11:22
 * author: caoyan
 * description:
 */

public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
    protected List<T> mList;
    private int mLayoutId;
    private Context mContext;

    public SimpleBaseAdapter(Context mContext, int layoutId, List<T> list) {
        this.mContext = mContext;
        this.mList = list;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SimpleBaseViewHolder holder = SimpleBaseViewHolder.getViewHolder(mContext,convertView, parent, mLayoutId);
        bindView(holder, getItem(position), position);
        return holder.getConvertView();
    }

    protected abstract void bindView(SimpleBaseViewHolder holder, T t, int position);

    protected void deleteItem(int position){
        mList.remove(position);
        notifyDataSetChanged();
    }

}
