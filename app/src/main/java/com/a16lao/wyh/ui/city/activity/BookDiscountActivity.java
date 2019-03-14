package com.a16lao.wyh.ui.city.activity;

import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.utils.StringUtils;

/**
 * date:   2018/6/11 0011 下午 5:36
 * author: caoyan
 * description:折扣
 */

public class BookDiscountActivity extends BaseActivity {
    private TextView text_book_discount;

    @Override
    protected int setLayout() {
        return R.layout.activity_book_discount;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        text_book_discount = findViewById(R.id.text_book_discount);
        text_book_discount.setText(StringUtils.setColoredString(text_book_discount.getText().toString(), 9, 12, getResources().getColor(R.color.color_1)));
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
