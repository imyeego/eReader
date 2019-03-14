package com.a16lao.wyh.base;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * date:   2018/6/13 0013 下午 1:14
 * author: caoyan
 * description:
 */

public class SimpleBaseViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private int layoutId;

    public SimpleBaseViewHolder(Context context, ViewGroup parent, int layoutId) {
        this.mViews = new SparseArray<>();
        this.layoutId = layoutId;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static SimpleBaseViewHolder getViewHolder(Context context,View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new SimpleBaseViewHolder(context,parent, layoutId);
        }
        SimpleBaseViewHolder holder = (SimpleBaseViewHolder) convertView.getTag();

        if (holder.getLayoutId() == layoutId) {
            return holder;
        }
        return holder;


    }

    private int getLayoutId() {
        return layoutId;
    }

    // 通过ID获取控件
    public <T extends View> T getViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    // 通过字符串设置TextView的值
    public SimpleBaseViewHolder setText(int viewId, String text) {
        TextView tv = getViewById(viewId);
        tv.setText(text);
        return this;
    }

    // 通过资源文件设置ImageView的值
    public SimpleBaseViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getViewById(viewId);
        iv.setImageResource(resId);
        return this;
    }

    // 通过位图设置ImageView的值
    public SimpleBaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getViewById(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    public SimpleBaseViewHolder setImageURI(int viewId, String url) {
        ImageView iv = getViewById(viewId);
        // 通过网络图片设置ImageView的值
        return this;
    }

    public SimpleBaseViewHolder setOnClickListner(int viewId, View.OnClickListener listener){
        getViewById(viewId).setOnClickListener(listener);

        return this;
    }

}
