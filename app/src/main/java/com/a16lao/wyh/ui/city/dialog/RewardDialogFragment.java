package com.a16lao.wyh.ui.city.dialog;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;
import com.a16lao.wyh.bean.ShowBean;
import com.a16lao.wyh.ui.city.adapter.RewardAdapter;
import com.a16lao.wyh.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/7/12 0012 下午 1:23
 * author: caoyan
 * description:
 */

public class RewardDialogFragment extends BaseDialogFragment {
    private GridView grid_reward;
    private List<String> mList;
    private RewardAdapter mAdapter;
    private TextView text_reward;
    private boolean flag;


    @Override
    protected Object setLayout() {
        return R.layout.dialog_reward;
    }

    @Override
    protected void initView() {

        text_reward = rootView.findViewById(R.id.text_reward);
        grid_reward = rootView.findViewById(R.id.grid_reward);
        if (flag) {
            flag = false;
            text_reward.setText("余额不足,去充值");
            text_reward.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            text_reward.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            text_reward.setOnClickListener(v -> {
                RechargeDialogFragment rechargeDialogFragment = new RechargeDialogFragment();
                rechargeDialogFragment.show(getFragmentManager(), "");
            });

        } else {
            flag = true;
            text_reward.setText("打赏");
            text_reward.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            text_reward.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            text_reward.setOnClickListener(v -> {
                new RewardSuccessDialogFragment().show(getFragmentManager(), "");
                dismiss();
            });

        }

        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add(i + "");
        }
        mAdapter = new RewardAdapter(rootView.getContext(), mList);
        grid_reward.setAdapter(mAdapter);
        mAdapter.setSelectedPosition(0);
        grid_reward.setOnItemClickListener((parent, view, position, id) -> mAdapter.setSelectedPosition(position));
    }


    @Override
    protected int initGravity() {
        return Gravity.BOTTOM;
    }

}
