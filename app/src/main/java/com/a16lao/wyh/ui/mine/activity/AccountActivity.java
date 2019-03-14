package com.a16lao.wyh.ui.mine.activity;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;

public class AccountActivity extends BaseActivity {
    private TextView text_account_recharge;
    private LinearLayout view_book_buy, view_reward_detail, view_recharge_detail, view_activity_gift;

    @Override
    protected int setLayout() {
        return R.layout.activity_account;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_mine_account));
        text_account_recharge = findViewById(R.id.text_account_recharge);
        view_book_buy = findViewById(R.id.view_book_buy);
        view_reward_detail = findViewById(R.id.view_reward_detail);
        view_recharge_detail = findViewById(R.id.view_recharge_detail);
        view_activity_gift = findViewById(R.id.view_activity_gift);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        //充值中心
        text_account_recharge.setOnClickListener(v ->
                toOtherActivity(RechargeActivity.class, null, false));
        //书籍购买
        view_book_buy.setOnClickListener(v -> toOtherActivity(BookBuyActivity.class, null, false));
        //打赏明细
        view_reward_detail.setOnClickListener(v -> toOtherActivity(RewardDetailActivity.class, null, false));
        //充值明细
        view_recharge_detail.setOnClickListener(v -> toOtherActivity(RechargeDetailActivity.class, null, false));
        //活动赠送
        view_activity_gift.setOnClickListener(v -> toOtherActivity(GiftActivity.class, null, false));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
