package com.a16lao.wyh.ui.mine.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;
import com.a16lao.wyh.config.GlobalParam;

import java.text.MessageFormat;

/**
 * date:   2018/6/29 0029 下午 4:48
 * author: caoyan
 * description:
 */

public class SignDialogFragment extends BaseDialogFragment {

    @Override
    protected Object setLayout() {
        return R.layout.dialog_sign;
    }

    @Override
    protected void initView() {
        TextView text_sign_bean_num = rootView.findViewById(R.id.text_sign_bean_num);
        Bundle bundle = getArguments();
        int num = bundle.getInt(GlobalParam.SIGN_REWARD);
        text_sign_bean_num.setText(MessageFormat.format("奖励{0}汇豆，已存入账户", num));
        TextView text_sign_close = rootView.findViewById(R.id.text_sign_close);
        text_sign_close.setOnClickListener(v -> dismiss());
    }


    @Override
    protected int initGravity() {
        return Gravity.CENTER;
    }
}
