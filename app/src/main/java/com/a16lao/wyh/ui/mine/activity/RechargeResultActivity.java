package com.a16lao.wyh.ui.mine.activity;

import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;

import java.text.MessageFormat;

/**
 * date:   2018/5/31 0031 上午 10:47
 * author: caoyan
 * description:充值中心结果页
 */

public class RechargeResultActivity extends BaseActivity {

    private TextView text_recharge_success, text_recharge_balance, text_recharge_done;


    @Override
    protected int setLayout() {
        return R.layout.activity_recharge_result;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_recharge_center));

        text_recharge_success = findViewById(R.id.text_recharge_success);
        text_recharge_success.setText(MessageFormat.format("成功充值：{0}汇豆", 500));
        text_recharge_balance = findViewById(R.id.text_recharge_balance);
        text_recharge_balance.setText(MessageFormat.format("当前余额：{0}汇豆", 700));
        text_recharge_done = findViewById(R.id.text_recharge_done);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

        text_recharge_done.setOnClickListener(v -> finish());
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}
