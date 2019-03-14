package com.a16lao.wyh.ui.city.activity;

import android.widget.GridView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.adapter.MaleCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 男频分类
 */
public class MaleCategoryActivity extends BaseActivity {
    private GridView grid_male_category;
    private List<String> mList;

    @Override
    protected int setLayout() {
        return R.layout.activity_male_category;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText("分类");
        grid_male_category = findViewById(R.id.grid_male_category);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mList.add("1");
        }
        grid_male_category.setAdapter(new MaleCategoryAdapter(this, mList));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
