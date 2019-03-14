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
import com.a16lao.wyh.ui.shelf.fragment.BookCommentsFragment;
import com.a16lao.wyh.ui.shelf.fragment.EssayCommentsFragment;
import com.a16lao.wyh.widget.pageindecator.PageIndicatorSetup;
import com.a16lao.wyh.widget.pageindecator.ViewPagerIndicator;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommentHistoryActivity extends BaseActivity implements View.OnClickListener{

    private Context context;
    private List<String> titles = Arrays.asList(new String[]{"书评", "评论"});
    private List<Fragment> fragments;
    private ViewPager vpCommentHistory;
    private ViewPagerIndicator vpiCommentHistory;

    @Override
    protected int setLayout() {
        return R.layout.activity_comment_history;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        context = this;
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(context, R.color.white) , true);

        setTitleMiddleText((String) getResources().getText(R.string.text_shelf_comments_history));
        setTitleRightText((String) getResources().getText(R.string.text_shelf_list_clear), this);

        vpCommentHistory = findViewById(R.id.vp_comment_history);
        vpiCommentHistory = findViewById(R.id.vpi_comment_history);

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new BookCommentsFragment());
        fragments.add(new EssayCommentsFragment());

        vpCommentHistory.setAdapter(new DefaultFrgPagerAdapter(getSupportFragmentManager(), fragments));
        vpCommentHistory.setCurrentItem(0);
        PageIndicatorSetup.setupPageIndicator(context, vpiCommentHistory, vpCommentHistory, titles);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }
}
