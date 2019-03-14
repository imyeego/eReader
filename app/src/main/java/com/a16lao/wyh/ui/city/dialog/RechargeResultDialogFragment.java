package com.a16lao.wyh.ui.city.dialog;

import android.view.Gravity;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;

/**
 * date:   2018/7/13 0013 上午 10:56
 * author: caoyan
 * description:充值
 */

public class RechargeResultDialogFragment extends BaseDialogFragment {

    private TextView text_recharge_close;
    private boolean flag = false;

    @Override
    public boolean isOnCancel() {
        return flag;
    }

    @Override
    protected Object setLayout() {
        return R.layout.dialog_recharge_result;
    }

    @Override
    protected void initView() {
        flag = false;
        getDialog().getWindow().getDecorView().setOnTouchListener(((v, event) -> false));
        text_recharge_close = rootView.findViewById(R.id.text_recharge_close);

        text_recharge_close.setOnClickListener(v -> {
            flag = true;
            dismiss();
        });

    }

    @Override
    protected int initGravity() {
        return Gravity.BOTTOM;
    }


}
