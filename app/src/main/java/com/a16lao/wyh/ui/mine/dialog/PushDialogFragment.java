package com.a16lao.wyh.ui.mine.dialog;


import android.view.Gravity;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;


/**
 * date:   2018/7/5 0005 上午 9:21
 * author: caoyan
 * description:
 */

public class PushDialogFragment extends BaseDialogFragment {


    @Override
    protected Object setLayout() {
        return R.layout.dialog_push;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int initGravity() {
        return Gravity.CENTER;
    }

}
