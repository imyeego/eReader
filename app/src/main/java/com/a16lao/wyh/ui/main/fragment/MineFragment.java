package com.a16lao.wyh.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.main.activity.LoginActivity;
import com.a16lao.wyh.ui.mine.activity.AccountActivity;
import com.a16lao.wyh.ui.mine.activity.MessageActivity;
import com.a16lao.wyh.ui.mine.activity.SettingActivity;
import com.a16lao.wyh.ui.mine.activity.SignActivity;
import com.a16lao.wyh.ui.mine.activity.VipActivity;
import com.a16lao.wyh.utils.StatusBarUtils;
import com.a16lao.wyh.utils.storage.SPUtils;

/**
 * date:   2018/5/23 0023 下午 5:21
 * author: caoyan
 * description:我的
 */

public class MineFragment extends LazyFragment {
    private LinearLayout view_mine_account, view_mine_writer, view_mine_vip, view_mine_setting,
            view_mine_sign;
    private TextView text_mine_sign, text_mine_bean, balance, text_mine_login;
    private ImageView icon_mine_message;
    private RelativeLayout view_mine_logged;

    @Override
    public Object setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        text_mine_login = rootView.findViewById(R.id.text_mine_login);
        view_mine_logged = rootView.findViewById(R.id.view_mine_logged);
        view_mine_account = rootView.findViewById(R.id.view_mine_account);
        view_mine_writer = rootView.findViewById(R.id.view_mine_writer);
        view_mine_vip = rootView.findViewById(R.id.view_mine_vip);
        view_mine_setting = rootView.findViewById(R.id.view_mine_setting);
        text_mine_sign = rootView.findViewById(R.id.text_mine_sign);
        text_mine_bean = rootView.findViewById(R.id.text_mine_bean);
        icon_mine_message = rootView.findViewById(R.id.icon_mine_message);
        view_mine_sign = rootView.findViewById(R.id.view_mine_sign);

    }

    @Override
    protected void initData() {
//        StatusBarUtils.setTranslucentForImageViewInFragment(mActivity, null);
        boolean isLogin = SPUtils.getAppFlag(SPUtils.IS_LOGIN);
        text_mine_login.setVisibility(isLogin ? View.GONE : View.VISIBLE);
        view_mine_logged.setVisibility(isLogin ? View.VISIBLE : View.GONE);

        //todo 签到
        if (false) {
            text_mine_bean.setVisibility(View.GONE);
            text_mine_sign.setText(getString(R.string.text_mine_signed));
        } else {
            text_mine_sign.setText(getString(R.string.text_mine_sign));
            text_mine_bean.setVisibility(View.VISIBLE);
            text_mine_bean.setText("+10汇豆");
        }

        //账户
        (view_mine_account.findViewById(R.id.icon_mine_item)).setBackgroundResource(R.drawable.personal_account);
        ((TextView) view_mine_account.findViewById(R.id.text_mine_item)).setText(getString(R.string.text_mine_account));
        balance = view_mine_account.findViewById(R.id.text_mine_desc);

        //todo  获得账户余额
        if (isLogin) {
            balance.setVisibility(View.VISIBLE);
            balance.setText("余额738汇豆");
            balance.setTextColor(getResources().getColor(R.color.color_1));
        } else {
            balance.setVisibility(View.GONE);
        }


        //成为作家
        (view_mine_writer.findViewById(R.id.icon_mine_item)).setBackgroundResource(R.drawable.personal_writer);
        ((TextView) view_mine_writer.findViewById(R.id.text_mine_item)).setText(getString(R.string.text_mine_writer));
        TextView writer = view_mine_writer.findViewById(R.id.text_mine_desc);
        writer.setVisibility(View.VISIBLE);
        writer.setText(getString(R.string.text_mine_writer));
        writer.setTextColor(getResources().getColor(R.color.color_4));

        //会员中心
        (view_mine_vip.findViewById(R.id.icon_mine_item)).setBackgroundResource(R.drawable.personal_vip);
        ((TextView) view_mine_vip.findViewById(R.id.text_mine_item)).setText(getString(R.string.text_mine_vip));
        view_mine_vip.findViewById(R.id.text_mine_desc).setVisibility(View.GONE);

        //设置
        (view_mine_setting.findViewById(R.id.icon_mine_item)).setBackgroundResource(R.drawable.personal_set);
        ((TextView) view_mine_setting.findViewById(R.id.text_mine_item)).setText(getString(R.string.text_mine_setting));
        view_mine_setting.findViewById(R.id.text_mine_desc).setVisibility(View.GONE);
        setListener();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void setListener() {
        //登录
        text_mine_login.setOnClickListener(v -> toOtherActivity(LoginActivity.class, null, false));
        //签到
        view_mine_sign.setOnClickListener(v -> toOtherActivity(SignActivity.class, null, false));
        //账户
        view_mine_account.setOnClickListener(v -> toOtherActivity(AccountActivity.class, null, false));
        //成为作家
        view_mine_writer.setOnClickListener(v -> toOtherActivity(SettingActivity.class, null, false));
        //会员中心
        view_mine_vip.setOnClickListener(v -> toOtherActivity(VipActivity.class, null, false));
        //设置
        view_mine_setting.setOnClickListener(v -> toOtherActivity(SettingActivity.class, null, false));
        //消息
        icon_mine_message.setOnClickListener(v -> toOtherActivity(MessageActivity.class, null, false));
    }

}
