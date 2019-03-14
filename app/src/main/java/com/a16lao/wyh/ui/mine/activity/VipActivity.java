package com.a16lao.wyh.ui.mine.activity;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.mine.adapter.VipAdapter;
import com.a16lao.wyh.utils.StatusBarUtils;
import com.a16lao.wyh.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VipActivity extends BaseActivity {
    private TextView text_vip_validity, text_vip_ali, text_vip_wx, text_vip_recharge;
    private ImageView icon_vip_ali, icon_vip_wx;
    private GridView grid_vip_money;
    private List<String> mList;
    private VipAdapter mAdapter;
    private RelativeLayout view_vip_ali, view_vip_wx, view_vip_tool;
    private LinearLayout view_vip_explain;

    @Override
    public boolean isTranslucent() {
        return true;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_vip;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_vip_center));
        setTitleRightText(getString(R.string.text_title_open_record), v -> {
            toOtherActivity(OpenRecordActivity.class, null, false);
        });
        text_vip_validity = findViewById(R.id.text_vip_validity);
        text_vip_validity.setText(StringUtils.setColoredString("会员将于2019-06-15到期", 4, 14, getResources().getColor(R.color.color_12)));
        grid_vip_money = findViewById(R.id.grid_vip_money);

        view_vip_ali = findViewById(R.id.view_vip_ali);
        text_vip_ali = view_vip_ali.findViewById(R.id.text_recharge_payment);
        text_vip_ali.setText(R.string.text_recharge_ali);
        text_vip_ali.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.alipay), null, null, null);
        icon_vip_ali = view_vip_ali.findViewById(R.id.icon_recharge_selected);
        view_vip_wx = findViewById(R.id.view_vip_wx);
        text_vip_wx = view_vip_wx.findViewById(R.id.text_recharge_payment);
        text_vip_wx.setText(R.string.text_recharge_wx);
        text_vip_wx.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.wechat_pay), null, null, null);
        icon_vip_wx = view_vip_wx.findViewById(R.id.icon_recharge_selected);

        view_vip_explain = findViewById(R.id.view_vip_explain);
        text_vip_recharge = findViewById(R.id.text_vip_recharge);
        view_vip_tool = findViewById(R.id.view_vip_tool);
        view_vip_tool.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view_vip_tool.getLayoutParams();
            params.topMargin = StatusBarUtils.getStatusBarHeight(this);
            view_vip_tool.setLayoutParams(params);
        });
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        mAdapter = new VipAdapter(this, mList);
        grid_vip_money.setAdapter(mAdapter);
        mAdapter.setSelectedPosition(0);
        grid_vip_money.setOnItemClickListener((parent, view, position, id) -> {
            mAdapter.setSelectedPosition(position);
        });

    }

    @Override
    protected void setListener() {
        setRechargeMethod(false);
        icon_vip_ali.setOnClickListener(v -> setRechargeMethod(false));
        icon_vip_wx.setOnClickListener(v -> setRechargeMethod(true));
        //特权说明
        view_vip_explain.setOnClickListener(v -> toOtherActivity(VipPrivilegeActivity.class, null, false));
        text_vip_recharge.setOnClickListener(v -> toOtherActivity(RechargeActivity.class, null, false));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void setRechargeMethod(boolean flag) {
        icon_vip_ali.setImageResource(flag ? R.drawable.art_down_buy : R.drawable.art_down_buy_c);
        icon_vip_wx.setImageResource(flag ? R.drawable.art_down_buy_c : R.drawable.art_down_buy);
    }
}
