package com.a16lao.wyh.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * date:   2018/7/3 0003 下午 1:54
 * author: caoyan
 * description:
 */

public class EditTextChangeListener implements TextWatcher {
    private TextView tv;
    private EditText[] edits;

    public EditTextChangeListener(TextView tv, EditText... edits) {
        this.tv = tv;
        this.edits = edits;
        initEditListener();
    }

    private void initEditListener() {

        if (checkAllEdit()) {
            tv.setEnabled(true);
        } else {
            tv.setEnabled(false);
        }

    }


    private boolean checkAllEdit() {
        for (EditText edit : edits) {
            if (TextUtils.isEmpty(edit.getText().toString().trim())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        initEditListener();
    }
}
