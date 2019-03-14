package com.a16lao.wyh.ui.mine.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.DefaultWithTabAdapter;
import com.a16lao.wyh.ui.mine.fragment.NoticeFragment;
import com.a16lao.wyh.utils.TabSetting;
import com.a16lao.wyh.utils.view.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity {
    private ViewPager vp_message_selected;
    private TabLayout tab_message_selected;
    private List<Fragment> mFragments;
    private String[] mStrings = new String[2];

    @Override
    protected int setLayout() {
        return R.layout.activity_message;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_mine_message));
        tab_message_selected = findViewById(R.id.tab_message_selected);
        vp_message_selected = findViewById(R.id.vp_message_selected);
    }

    @Override
    protected void initData() {
        mStrings[0] = getString(R.string.text_mine_notice);
        mStrings[1] = getString(R.string.text_mine_remind);
        mFragments = new ArrayList<>();
        mFragments.add(new NoticeFragment());
        mFragments.add(new NoticeFragment());
        tab_message_selected.setupWithViewPager(vp_message_selected);
        vp_message_selected.setAdapter(new DefaultWithTabAdapter(getSupportFragmentManager(), mFragments, mStrings));

    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
