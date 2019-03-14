package com.a16lao.wyh.ui.mine.activity;

import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.utils.AppUtils;

import java.text.MessageFormat;

public class AboutUsActivity extends BaseActivity {
    private TextView text_us_version_code;

    @Override
    protected int setLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_about_us));
        text_us_version_code = findViewById(R.id.text_us_version_code);
    }

    @Override
    protected void initData() {
        text_us_version_code.setText(MessageFormat.format("V{0}", AppUtils.getVersionCode(this)));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
