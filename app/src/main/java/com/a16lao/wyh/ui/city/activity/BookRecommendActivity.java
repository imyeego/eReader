package com.a16lao.wyh.ui.city.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.utils.AppUtils;
import com.a16lao.wyh.utils.SystemUtils;
import com.a16lao.wyh.utils.ToastUtils;

import java.text.MessageFormat;

public class BookRecommendActivity extends BaseActivity implements TextWatcher {
    private EditText edit_book_recommend;
    private TextView text_book_recommend, text_toolbar_right;

    @Override
    protected int setLayout() {
        return R.layout.activity_book_recommend;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText("写书评");
        text_toolbar_right = findViewById(R.id.text_toolbar_right);
        text_toolbar_right.setVisibility(View.VISIBLE);
        text_toolbar_right.setText(R.string.text_feed_back_submit);
        edit_book_recommend = findViewById(R.id.edit_book_recommend);
        edit_book_recommend.addTextChangedListener(this);
        text_book_recommend = findViewById(R.id.text_book_recommend);
        if (TextUtils.isEmpty(edit_book_recommend.getText().toString().trim())) {
            text_toolbar_right.setEnabled(false);
            text_toolbar_right.setTextColor(getResources().getColor(R.color.color_6));
        }
    }

    @Override
    protected void initData() {

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
        if (TextUtils.isEmpty(edit_book_recommend.getText().toString().trim())) {
            text_toolbar_right.setEnabled(false);
            text_toolbar_right.setTextColor(getResources().getColor(R.color.color_6));
        } else {
            text_toolbar_right.setEnabled(true);
            text_toolbar_right.setTextColor(getResources().getColor(R.color.color_3));
        }

        if (edit_book_recommend.length() > 500) {
            text_book_recommend.setText(MessageFormat.format("{0}/500", 500));
        } else {
            text_book_recommend.setText(MessageFormat.format("{0}/500", edit_book_recommend.length()));
        }
    }
}
