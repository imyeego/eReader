package com.a16lao.wyh.ui.city.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.bean.city.BookCatalogGroupBean;

import java.util.List;

/**
 * date:   2018/7/20 0020 下午 4:03
 * author: caoyan
 * description:
 */

public class BookCatalogExpandAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<BookCatalogGroupBean> groups;
    //                用于存放Indicator的集合
    private SparseArray<ImageView> mIndicators;

    public BookCatalogExpandAdapter(Context mContext, List<BookCatalogGroupBean> groups) {
        this.mContext = mContext;
        this.groups = groups;
        mIndicators = new SparseArray<>();
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getChilds().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChilds().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expand_book_catalog_group, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.text_expand_group = convertView.findViewById(R.id.text_expand_group);
            groupViewHolder.icon_expand_indicator = convertView.findViewById(R.id.icon_expand_indicator);
            groupViewHolder.text_expand_group_state = convertView.findViewById(R.id.text_expand_group_state);
            groupViewHolder.icon_expand_selected = convertView.findViewById(R.id.icon_expand_selected);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.text_expand_group.setText(groups.get(groupPosition).getGroupName());
        mIndicators.put(groupPosition, groupViewHolder.icon_expand_indicator);
        setIndicatorState(groupPosition, isExpanded);
        if (groups.get(groupPosition).isSelected()) {
            groupViewHolder.text_expand_group_state.setVisibility(View.VISIBLE);
            groupViewHolder.icon_expand_selected.setVisibility(View.GONE);
            groupViewHolder.text_expand_group_state.setText("已下载");
        } else {
            groupViewHolder.text_expand_group_state.setVisibility(View.GONE);
            groupViewHolder.icon_expand_selected.setVisibility(View.VISIBLE);
            groupViewHolder.icon_expand_selected.setImageResource(R.drawable.art_down_buy);
        }


        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expand_book_catalog_child, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.text_expand_child = convertView.findViewById(R.id.text_expand_child);
            childViewHolder.icon_expand_child = convertView.findViewById(R.id.icon_expand_child);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.text_expand_child.setText(groups.get(groupPosition).getChilds().get(childPosition).getChildName());
        if (groups.get(groupPosition).getChilds().get(childPosition).isSelected()) {
            childViewHolder.icon_expand_child.setImageResource(R.drawable.art_down_buy_c);
        } else {
            childViewHolder.icon_expand_child.setImageResource(R.drawable.art_down_buy);
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //            根据分组的展开闭合状态设置指示器
    public void setIndicatorState(int groupPosition, boolean isExpanded) {
        if (isExpanded) {
            mIndicators.get(groupPosition).setImageResource(R.drawable.art_down_up);
        } else {
            mIndicators.get(groupPosition).setImageResource(R.drawable.art_down_down);
        }

    }

    static class GroupViewHolder {
        TextView text_expand_group, text_expand_group_state;
        ImageView icon_expand_indicator, icon_expand_selected;
    }

    static class ChildViewHolder {
        TextView text_expand_child;
        ImageView icon_expand_child;
    }


}
