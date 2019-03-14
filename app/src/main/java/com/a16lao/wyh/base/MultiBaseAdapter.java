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

public abstract class MultiBaseAdapter<T> extends BaseAdapter {
    protected List<T> mList;
    private int[] mLayoutIds;
    private Context mContext;

    public MultiBaseAdapter(Context mContext, int[] layoutIds, List<T> list) {
        this.mContext = mContext;
        this.mList = list;
        this.mLayoutIds = layoutIds;
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

        SimpleBaseViewHolder holder = SimpleBaseViewHolder.getViewHolder(mContext,convertView,parent, mLayoutIds[getLayoutIndex(mList.get(position))]);
        bindView(holder, getItem(position), position, getLayoutIndex(mList.get(position)));
        return holder.getConvertView();
    }

    protected abstract void bindView(SimpleBaseViewHolder holder, T t, int position, int layoutIndex);

    protected abstract int getLayoutIndex(T entity);
}
