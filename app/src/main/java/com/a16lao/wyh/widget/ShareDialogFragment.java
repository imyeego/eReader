package com.a16lao.wyh.widget;


import android.view.Gravity;

import android.view.View;

import android.widget.LinearLayout;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.utils.ClientAvailableUtils;
import com.a16lao.wyh.utils.ToastUtils;


/**
 * date:   2018/5/28 0028 下午 12:01
 * author: caoyan
 * description:
 */

public class ShareDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    private LinearLayout view_share_weChat, view_share_friends, view_share_sina, view_share_QQ;

    @Override
    protected Object setLayout() {
        return R.layout.dialog_share;
    }

    @Override
    protected void initView() {
        view_share_weChat = rootView.findViewById(R.id.view_share_weChat);
        view_share_friends = rootView.findViewById(R.id.view_share_friends);
        view_share_sina = rootView.findViewById(R.id.view_share_sina);
        view_share_QQ = rootView.findViewById(R.id.view_share_QQ);
        setListener();
    }

    private void setListener() {
        view_share_weChat.setOnClickListener(this);
        view_share_friends.setOnClickListener(this);
        view_share_sina.setOnClickListener(this);
        view_share_QQ.setOnClickListener(this);
    }

    @Override
    protected int initGravity() {
        return Gravity.BOTTOM;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_share_weChat:
                if (!ClientAvailableUtils.isWeiChatAvailable(Latte.getApplication())) {
                    ToastUtils.showToast(Latte.getApplication(), "请先安装微信");
                } else {

                }

                break;
            case R.id.view_share_friends:
                if (!ClientAvailableUtils.isWeiChatAvailable(Latte.getApplication())) {
                    ToastUtils.showToast(Latte.getApplication(), "请先安装微信");
                } else {

                }
                break;
            case R.id.view_share_sina:
                if (!ClientAvailableUtils.isSinaClientAvailable(Latte.getApplication())) {
                    ToastUtils.showToast(Latte.getApplication(), "请先安装新浪微博客戶端");
                } else {

                }
                break;
            case R.id.view_share_QQ:
                if (!ClientAvailableUtils.isQQClientAvailable(Latte.getApplication())) {
                    ToastUtils.showToast(Latte.getApplication(), "请先安装QQ");
                } else {

                }
                break;

        }
    }


}
