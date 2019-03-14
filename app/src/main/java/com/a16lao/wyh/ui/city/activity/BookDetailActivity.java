package com.a16lao.wyh.ui.city.activity;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.dialog.RewardDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 书籍详情
 */
public class BookDetailActivity extends BaseActivity {
    private ListView list_book_detail;
    private List<String> mList = new ArrayList<>();
    private TextView text_book_count, text_book_read, text_book_recommend;
    private LinearLayout view_book_fab, view_book_reward, view_book_catalog;
    private ImageView icon_book_fab;
    private RewardDialogFragment mDialogFragment;

    @Override
    protected int setLayout() {
        return R.layout.activity_book_detail;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(View.GONE, "");
        setTitleRightNavigator(R.drawable.det_share, null);
        list_book_detail = findViewById(R.id.list_book_detail);
        list_book_detail.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList));

        View view1 = LayoutInflater.from(this).inflate(R.layout.list_book_detail_item1, null);

        text_book_read = findViewById(R.id.text_book_read);
        text_book_count = view1.findViewById(R.id.text_book_count);
        text_book_count.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        list_book_detail.addHeaderView(view1);
        View view2 = LayoutInflater.from(this).inflate(R.layout.list_book_detail_item2, null);
        view_book_fab = view2.findViewById(R.id.view_book_fab);
        icon_book_fab = view2.findViewById(R.id.icon_book_fab);
        view_book_reward = view2.findViewById(R.id.view_book_reward);
        view_book_catalog = view2.findViewById(R.id.view_book_catalog);
        list_book_detail.addHeaderView(view2);
        View view3 = LayoutInflater.from(this).inflate(R.layout.list_book_detail_item3, null);
        text_book_recommend = view3.findViewById(R.id.text_book_recommend);
        list_book_detail.addHeaderView(view3);
        list_book_detail.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_book_detail_item4, null));
        list_book_detail.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_book_detail_item5, null));
        list_book_detail.addHeaderView(LayoutInflater.from(this).inflate(R.layout.list_book_detail_item6, null));
    }

    @Override
    protected void initData() {
        mDialogFragment = new RewardDialogFragment();
    }

    @Override
    protected void setListener() {
        view_book_fab.setOnClickListener(v -> {
            icon_book_fab.setImageResource(R.drawable.det_fab_ed);
            icon_book_fab.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fab_scale));
        });
        view_book_reward.setOnClickListener(v -> {
            if (mDialogFragment.isAdded()) {
                mDialogFragment.getDialog().show();
            } else {
                mDialogFragment.show(getFragmentManager(), "reward");
            }

        });
        //目录
        view_book_catalog.setOnClickListener(v ->
                toOtherActivity(BookCatalogActivity.class, null, false));
        text_book_read.setOnClickListener(v ->
                toOtherActivity(ListenBookActivity.class, null, false)
        );
        //书评
        text_book_recommend.setOnClickListener(v -> {
            toOtherActivity(EssayDetailActivity.class, null, false);
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
