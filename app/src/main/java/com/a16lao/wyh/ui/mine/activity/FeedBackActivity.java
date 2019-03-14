package com.a16lao.wyh.ui.mine.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.utils.AppUtils;
import com.a16lao.wyh.utils.SystemUtils;
import com.a16lao.wyh.utils.ToastUtils;

import java.text.MessageFormat;

public class FeedBackActivity extends BaseActivity implements TextWatcher {
    private EditText edit_feed_back;
    private TextView text_toolbar_right, text_feed_back;

    @Override
    protected int setLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_title_feed_back));
        text_toolbar_right = findViewById(R.id.text_toolbar_right);
        text_toolbar_right.setVisibility(View.VISIBLE);
        text_toolbar_right.setText(R.string.text_feed_back_submit);

        edit_feed_back = findViewById(R.id.edit_feed_back);
        edit_feed_back.addTextChangedListener(this);
        text_feed_back = findViewById(R.id.text_feed_back);
        if (TextUtils.isEmpty(edit_feed_back.getText().toString().trim())) {
            text_toolbar_right.setEnabled(false);
            text_toolbar_right.setTextColor(getResources().getColor(R.color.color_6));
        }
    }

    @Override
    protected void initData() {
//        if (temp.length() > 10) {
//            Toast.makeText(TextWatcherDemo.this,
//                    "你输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
//                    .show();
//            s.delete(editStart-1, editEnd);
//            int tempSelection = editStart;
//            mEditText.setText(s);
//            mEditText.setSelection(tempSelection);
//        }
    }

    @Override
    protected void setListener() {
        text_toolbar_right.setOnClickListener(v -> {
            SystemUtils.getSystemModel();
            SystemUtils.getSystemVersion();
            AppUtils.getVersionCode(this);
            ToastUtils.show(this, "提交成功，感谢您的反馈");
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(edit_feed_back.getText().toString().trim())) {
            text_toolbar_right.setEnabled(false);
            text_toolbar_right.setTextColor(getResources().getColor(R.color.color_6));
        } else {
            text_toolbar_right.setEnabled(true);
            text_toolbar_right.setTextColor(getResources().getColor(R.color.color_3));
        }

        if (edit_feed_back.length() > 500) {
            text_feed_back.setText(MessageFormat.format("{0}/500", 500));
        } else {
            text_feed_back.setText(MessageFormat.format("{0}/500", edit_feed_back.length()));
        }
    }
}
