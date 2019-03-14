package com.a16lao.wyh.ui.city.activity;

import android.widget.GridView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.adapter.EssayCategoryAdapter;
import com.a16lao.wyh.ui.city.adapter.MaleCategoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 书城短文分类
 */
public class EssayCategoryActivity extends BaseActivity {
    private GridView grid_essay_category;
    private List<String> mList;
    @Override
    protected int setLayout() {
        return R.layout.activity_essay_category;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText("分类");
        grid_essay_category = findViewById(R.id.grid_essay_category);
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mList.add("1");
        }
        grid_essay_category.setAdapter(new EssayCategoryAdapter(this, mList));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
