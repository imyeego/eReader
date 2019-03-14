package com.a16lao.wyh.ui.mine.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.config.GlobalParam;
import com.a16lao.wyh.ui.mine.dialog.SignDialogFragment;

import java.text.MessageFormat;

/**
 * date:   2018/5/23 0023 下午 5:21
 * author: caoyan
 * description:签到
 */

public class SignActivity extends BaseActivity {
    private RelativeLayout view_sign_3, view_sign_5, view_sign_10, view_sign_30;
    private SignDialogFragment mSignDialogFragment;

    @Override
    protected int setLayout() {
        return R.layout.activity_sign;
    }

    @Override
    protected void getFromIntentData() {
    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_title_sign));
        //签到3天
        view_sign_3 = findViewById(R.id.view_sign_3);
        setSignedIn(view_sign_3, 3, 10);
        //签到5天
        view_sign_5 = findViewById(R.id.view_sign_5);
        setSignedIn(view_sign_5, 5, 15);
        //签到10天
        view_sign_10 = findViewById(R.id.view_sign_10);
        setSignedIn(view_sign_10, 10, 50);
        //签到30天
        view_sign_30 = findViewById(R.id.view_sign_30);
        setNotSignedIn(view_sign_30, 30, 500);

        mSignDialogFragment = new SignDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(GlobalParam.SIGN_REWARD, 10);
        mSignDialogFragment.setArguments(bundle);
        mSignDialogFragment.show(getFragmentManager(), "");
    }

    @Override
    protected void initData() {
        //todo 与天数比较，修改赠送汇豆字体颜色与完成颜色
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    public void setSignedIn(RelativeLayout layout, int num, int bean) {
        TextView text_sign_day = layout.findViewById(R.id.text_sign_day);
        text_sign_day.setText(MessageFormat.format("连续签到{0}天", num));
        TextView text_sign_bean = layout.findViewById(R.id.text_sign_bean);
        text_sign_bean.setText(MessageFormat.format("赠送{0}汇豆", bean));
        text_sign_bean.setTextColor(getResources().getColor(R.color.color_1));
        TextView text_sign_done = layout.findViewById(R.id.text_sign_done);
        text_sign_done.setText(getString(R.string.text_sign_done));
        text_sign_done.setTextColor(getResources().getColor(R.color.color_11));
        text_sign_done.setBackgroundResource(R.drawable.border_c11_r15);
    }

    public void setNotSignedIn(RelativeLayout layout, int num, int bean) {
        TextView text_sign_day = layout.findViewById(R.id.text_sign_day);
        text_sign_day.setText(MessageFormat.format("连续签到{0}天", num));
        TextView text_sign_bean = layout.findViewById(R.id.text_sign_bean);
        text_sign_bean.setText(MessageFormat.format("赠送{0}汇豆", bean));
        text_sign_bean.setTextColor(getResources().getColor(R.color.color_6));
        TextView text_sign_done = layout.findViewById(R.id.text_sign_done);
        text_sign_done.setText(getString(R.string.text_sign_undone));
        text_sign_done.setTextColor(getResources().getColor(R.color.color_6));
        text_sign_done.setBackgroundResource(R.drawable.border_c6_r15);
    }
}
