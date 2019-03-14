package com.a16lao.wyh.ui.mine.dialog;

import android.view.Gravity;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;
import com.a16lao.wyh.utils.storage.SPUtils;


public class ExitDialogFragment extends BaseDialogFragment {

    @Override
    protected Object setLayout() {
        return R.layout.dialog_exit;
    }

    @Override
    protected void initView() {
        rootView.findViewById(R.id.text_exit_sure).setOnClickListener(v -> {
            SPUtils.setAppFlag(SPUtils.IS_LOGIN, false);
            getActivity().finish();
        });
    }

    @Override
    protected int initGravity() {
        return Gravity.CENTER;
    }
}
