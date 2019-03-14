package com.a16lao.wyh.ui.mine.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.bean.mine.VipBean;

import java.util.List;

/**
 * date:   2018/7/17 0017 上午 9:43
 * author: caoyan
 * description:
 */

public class VipPrivilegeAdapter extends SimpleBaseAdapter<VipBean> {
    public VipPrivilegeAdapter(Context mContext, List<VipBean> list) {
        super(mContext, R.layout.list_vip_privilege_item, list);
    }

    @Override
    protected void bindView(SimpleBaseViewHolder holder, VipBean vipBean, int position) {
        ImageView icon_vip_no = holder.getViewById(R.id.icon_vip_no);
        ImageView icon_vip_member = holder.getViewById(R.id.icon_vip_member);
        holder.setText(R.id.text_vip_op, vipBean.getTag());
        icon_vip_no.setImageResource(vipBean.isNoVip() ? R.drawable.list_selected : R.drawable.authority_no);
        icon_vip_member.setImageResource(vipBean.isVip() ? R.drawable.list_selected : R.drawable.authority_no);
    }
}
