package com.a16lao.wyh.ui.mine.dialog;

import android.content.Intent;
import android.view.Gravity;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;
import com.a16lao.wyh.ui.main.activity.LoginActivity;
import com.a16lao.wyh.ui.mine.activity.FeedBackActivity;
import com.a16lao.wyh.utils.ClientAvailableUtils;
import com.a16lao.wyh.utils.storage.SPUtils;

/**
 * date:   2018/7/17 0017 上午 11:54
 * author: caoyan
 * description:
 */

public class GradeDialogFragment extends BaseDialogFragment {
    private TextView text_grade_go, text_grade_feedback, text_grade_cancel;

    @Override
    protected Object setLayout() {
        return R.layout.dialog_grade;
    }

    @Override
    protected void initView() {
        text_grade_go = rootView.findViewById(R.id.text_grade_go);
        text_grade_feedback = rootView.findViewById(R.id.text_grade_feedback);
        text_grade_cancel = rootView.findViewById(R.id.text_grade_cancel);
        setListener();
    }

    private void setListener() {
        text_grade_go.setOnClickListener(v -> {
            ClientAvailableUtils.goToMarket(getActivity(), "com.lao16.wyh");
            dismiss();
        });
        text_grade_feedback.setOnClickListener(v -> {
            if (SPUtils.getAppFlag(SPUtils.IS_LOGIN)) {
                getActivity().startActivity(new Intent(getActivity(), FeedBackActivity.class));
            } else {
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            }
            dismiss();
        });
        text_grade_cancel.setOnClickListener(v -> dismiss());
    }

    @Override
    protected int initGravity() {
        return Gravity.CENTER;
    }
}
