package com.a16lao.wyh.ui.shelf.activity;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.DefaultFrgPagerAdapter;
import com.a16lao.wyh.ui.shelf.fragment.BookHistoryFragment;
import com.a16lao.wyh.ui.shelf.fragment.EssayHistoryFragment;
import com.a16lao.wyh.widget.pageindecator.PageIndicatorSetup;
import com.a16lao.wyh.widget.pageindecator.ViewPagerIndicator;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * date:   2018/5/24 0024 下午 2:54
 * author: caoyan
 * description:
 */

public class RecentlyReadActivity extends BaseActivity implements View.OnClickListener {

    private Context context;
    private List<String> titles = Arrays.asList(new String[]{"书籍历史", "短文历史"});
    private List<Fragment> fragments;
    private ViewPager vpRecentlyReaded;
    private ViewPagerIndicator vpiRecentlyReaded;


    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new BookHistoryFragment());
        fragments.add(new EssayHistoryFragment());

        vpRecentlyReaded.setAdapter(new DefaultFrgPagerAdapter(getSupportFragmentManager(), fragments));
        vpRecentlyReaded.setCurrentItem(0);
        PageIndicatorSetup.setupPageIndicator(context, vpiRecentlyReaded, vpRecentlyReaded, titles);
    }

    @Override
    protected void initView() {
        context = this;
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(context, R.color.white) , true);

        setTitleMiddleText((String) getResources().getText(R.string.text_shelf_recently_readed));
        setTitleRightText((String) getResources().getText(R.string.text_shelf_list_clear), this);

        vpRecentlyReaded = findViewById(R.id.vp_recently_reader);
        vpiRecentlyReaded = findViewById(R.id.vpi_recently_reader);
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_recently_read;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
