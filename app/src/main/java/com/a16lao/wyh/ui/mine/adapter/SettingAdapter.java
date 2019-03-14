package com.a16lao.wyh.ui.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.config.GlobalParam;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.utils.file.FileUtils;
import com.a16lao.wyh.utils.storage.SPUtils;

import java.util.List;

/**
 * date:   2018/5/31 0031 下午 1:36
 * author: caoyan
 * description:
 */

public class SettingAdapter extends SimpleBaseAdapter<String> {
    public SettingAdapter(Context mContext, List<String> list) {
        super(mContext, R.layout.list_setting_item, list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, String s, int position) {
        View line_setting_item = holder.getViewById(R.id.line_setting_item);
        View line_setting_space = holder.getViewById(R.id.line_setting_space);
        line_setting_space.setVisibility(position == 1 || position == 3 || position == 4 || position == 8 ? View.GONE : View.VISIBLE);

        TextView text_setting_show = holder.getViewById(R.id.text_setting_show);
        line_setting_item.setVisibility(position == 0 || position == 2 || position == 4 || position == 5 ? View.VISIBLE : View.GONE);
        holder.setText(R.id.text_setting_item, s);
        if (position == 3) {
            text_setting_show.setVisibility(View.VISIBLE);
            if (SPUtils.getAppFlag(GlobalParam.PUSH_ACCEPT)) {
                text_setting_show.setText("已开启");
            } else {
                text_setting_show.setText("未开启");
            }

            if (mCallBack != null) {
                mCallBack.onPushAccept(text_setting_show);
            }


        } else if (position == 5) {
            text_setting_show.setVisibility(View.VISIBLE);

            try {
                text_setting_show.setText(FileUtils.getCacheSize(Latte.getApplication().getCacheDir()));
                text_setting_show.setOnClickListener(v -> {
                            FileUtils.cleanCacheFiles(Latte.getApplication());
                            text_setting_show.setText("0.0K");
                        }
                );
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            text_setting_show.setVisibility(View.GONE);
        }

    }

    CallBack mCallBack;

    public void setOnCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public interface CallBack {
        void onPushAccept(TextView view);
    }


}
