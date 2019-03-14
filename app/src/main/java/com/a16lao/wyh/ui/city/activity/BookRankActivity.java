package com.a16lao.wyh.ui.city.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.base.DefaultWithTabAdapter;
import com.a16lao.wyh.ui.city.fragment.WeekRankFragment;
import com.a16lao.wyh.ui.city.popupwindow.RankPopUpWindow;

import java.util.ArrayList;
import java.util.List;


/**
 * date:   2018/6/11 0011 下午 3:57
 * author: caoyan
 * description:
 */

public class BookRankActivity extends BaseActivity {
    private TextView text_book_rank;
    private View line_book_rank;
    private AppCompatImageView icon_book_rank_arrow;
    private TabLayout tab_rank_selected;
    private RankPopUpWindow popUpWindow;
    private ViewPager vp_rank_selected;
    private List<Fragment> mList;

    private String[] titles = {"男频", "女频", "短文", "听书", "漫画"};

    @Override
    protected int setLayout() {
        return R.layout.activity_book_rank;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        text_book_rank = findViewById(R.id.text_book_rank);
        line_book_rank = findViewById(R.id.line_book_rank);
        icon_book_rank_arrow = findViewById(R.id.icon_book_rank_arrow);
        tab_rank_selected = findViewById(R.id.tab_rank_selected);
        vp_rank_selected = findViewById(R.id.vp_rank_selected);
    }

    @Override
    protected void initData() {
        popUpWindow = new RankPopUpWindow(mActivity);
        mList = new ArrayList<>();

        for (String ignored : titles) {
            mList.add(new WeekRankFragment());
        }
        vp_rank_selected.setAdapter(new DefaultWithTabAdapter(getSupportFragmentManager(), mList, titles));
        tab_rank_selected.setupWithViewPager(vp_rank_selected);


    }

    @Override
    protected void setListener() {
        icon_book_rank_arrow.setOnClickListener(v -> finish());
        text_book_rank.setOnClickListener(
                v -> {
                    popUpWindow.showPopupWindow(line_book_rank);
                    if (!popUpWindow.isShowing()) {
                        text_book_rank.setCompoundDrawablesWithIntrinsicBounds(null, null, Latte.getApplication().getResources().getDrawable(R.drawable.list_down), null);
                    } else {

                        text_book_rank.setCompoundDrawablesWithIntrinsicBounds(null, null, Latte.getApplication().getResources().getDrawable(R.drawable.list_pack), null);
                    }
                }
        );


        popUpWindow.setOnDismissListener(() -> text_book_rank.setCompoundDrawablesWithIntrinsicBounds(null, null, Latte.getApplication().getResources().getDrawable(R.drawable.list_down), null));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
