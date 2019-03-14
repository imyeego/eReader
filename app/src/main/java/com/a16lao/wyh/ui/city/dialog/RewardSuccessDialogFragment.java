package com.a16lao.wyh.ui.city.dialog;

import android.view.Gravity;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;

/**
 * date:   2018/7/12 0012 下午 4:20
 * author: caoyan
 * description:
 */

public class RewardSuccessDialogFragment extends BaseDialogFragment {

    @Override
    protected Object setLayout() {
        return R.layout.dialog_reward_success;
    }

    @Override
    protected void initView() {
        TextView text_reward_close = rootView.findViewById(R.id.text_reward_close);
        text_reward_close.setOnClickListener(v -> dismiss());
    }

    @Override
    protected int initGravity() {
        return Gravity.CENTER;
    }
}
