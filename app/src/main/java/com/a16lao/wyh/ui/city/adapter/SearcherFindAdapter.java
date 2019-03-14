package com.a16lao.wyh.ui.city.adapter;


import android.content.Context;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.utils.StringUtils;


import java.util.List;

/**
 * date:   2018/6/12 0012 下午 2:59
 * author: caoyan
 * description:
 */

public class SearcherFindAdapter extends SimpleBaseAdapter<String> {
    private String keyWord;

    public SearcherFindAdapter(Context mContext,List<String> list, String keyWord) {
        super(mContext,R.layout.list_free_item,list);
        this.keyWord = keyWord;
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        ((TextView) holder.getViewById(R.id.text_free_title)).setText(StringUtils.setKeyWordColor(s, keyWord));
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord.trim();
        notifyDataSetChanged();
    }


}
