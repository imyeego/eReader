package com.a16lao.wyh.ui.city.dialog;

import android.view.Gravity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;
import com.a16lao.wyh.ui.city.adapter.RechargeMoneyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/7/13 0013 上午 10:56
 * author: caoyan
 * description:充值
 */

public class RechargeDialogFragment extends BaseDialogFragment {
    private GridView grid_recharge_money;
    private RechargeMoneyAdapter mMoneyAdapter;
    private List<String> mList;
    private LinearLayout view_reward_ali, view_reward_wx;
    private TextView text_recharge_imm;
    private boolean flag = false;

    @Override
    public boolean isOnCancel() {
        return flag;
    }


    @Override
    protected Object setLayout() {
        return R.layout.dialog_recharge;
    }

    @Override
    protected void initView() {
        flag = false;
        getDialog().getWindow().getDecorView().setOnTouchListener((v, event) -> false);
        TextView text_toolbar_middle = rootView.findViewById(R.id.text_toolbar_middle);
        text_toolbar_middle.setText(getString(R.string.text_recharge_center));
        ImageView image_toolbar_back = rootView.findViewById(R.id.image_toolbar_back);
        image_toolbar_back.setOnClickListener(v -> {
            flag = true;
            dismiss();
        });
        view_reward_ali = rootView.findViewById(R.id.view_reward_ali);
        view_reward_wx = rootView.findViewById(R.id.view_reward_wx);
        text_recharge_imm = rootView.findViewById(R.id.text_recharge_imm);
        text_recharge_imm.setOnClickListener(v -> {
            new RechargeResultDialogFragment().show(getFragmentManager(), "");
        });
        setRechargeMethod(false);
        view_reward_ali.setOnClickListener(v -> {
            setRechargeMethod(false);
        });

        view_reward_wx.setOnClickListener(v -> {
            setRechargeMethod(true);
        });


        grid_recharge_money = rootView.findViewById(R.id.grid_recharge_money);

        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add(i + "");
        }
        mMoneyAdapter = new RechargeMoneyAdapter(rootView.getContext(), mList);
        grid_recharge_money.setAdapter(mMoneyAdapter);
        mMoneyAdapter.setSelectedPosition(0);
    }

    @Override
    protected int initGravity() {
        return Gravity.BOTTOM;
    }


    private void setRechargeMethod(boolean flag) {
        view_reward_ali.setBackgroundResource(!flag ? R.drawable.border_c15 : R.drawable.border_c7);
        view_reward_wx.setBackgroundResource(!flag ? R.drawable.border_c7 : R.drawable.border_c15);
    }

}
