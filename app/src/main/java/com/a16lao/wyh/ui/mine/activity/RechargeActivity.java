package com.a16lao.wyh.ui.mine.activity;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.adapter.RewardAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/31 0031 上午 10:47
 * author: caoyan
 * description:充值中心
 */

public class RechargeActivity extends BaseActivity {
    private GridView grid_recharge;
    private List<String> mList;
    private RewardAdapter mAdapter;
    private RelativeLayout view_recharge_ali, view_recharge_wx;
    private TextView text_recharge_ali, text_recharge_wx, text_recharge_go;
    private ImageView icon_recharge_ali, icon_recharge_wx;


    @Override
    protected int setLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_recharge_center));
        grid_recharge = findViewById(R.id.grid_recharge);
        view_recharge_ali = findViewById(R.id.view_recharge_ali);
        text_recharge_ali = view_recharge_ali.findViewById(R.id.text_recharge_payment);
        text_recharge_ali.setText(R.string.text_recharge_ali);
        text_recharge_ali.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.alipay), null, null, null);
        icon_recharge_ali = view_recharge_ali.findViewById(R.id.icon_recharge_selected);
        view_recharge_wx = findViewById(R.id.view_recharge_wx);
        text_recharge_wx = view_recharge_wx.findViewById(R.id.text_recharge_payment);
        text_recharge_wx.setText(R.string.text_recharge_wx);
        text_recharge_wx.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.wechat_pay), null, null, null);
        icon_recharge_wx = view_recharge_wx.findViewById(R.id.icon_recharge_selected);
        text_recharge_go = findViewById(R.id.text_recharge_go);


    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add(i + 1 + "");
        }
        mAdapter = new RewardAdapter(this, mList);
        grid_recharge.setAdapter(mAdapter);
        mAdapter.setSelectedPosition(0);

    }

    @Override
    protected void setListener() {
        setRechargeMethod(false);
        icon_recharge_ali.setOnClickListener(v -> setRechargeMethod(false));
        icon_recharge_wx.setOnClickListener(v -> setRechargeMethod(true));
        text_recharge_go.setOnClickListener(v -> toOtherActivity(RechargeResultActivity.class, null, false));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void setRechargeMethod(boolean flag) {
        icon_recharge_ali.setImageResource(flag ? R.drawable.art_down_buy : R.drawable.art_down_buy_c);
        icon_recharge_wx.setImageResource(flag ? R.drawable.art_down_buy_c : R.drawable.art_down_buy);
    }
}
