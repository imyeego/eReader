package com.a16lao.wyh.ui.city.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.city.activity.BookDiscountActivity;
import com.a16lao.wyh.ui.city.activity.BookListActivity;
import com.a16lao.wyh.ui.city.activity.BookRankActivity;
import com.a16lao.wyh.ui.city.activity.FreeForLimitedTimeActivity;
import com.a16lao.wyh.ui.city.activity.TestActivity;
import com.a16lao.wyh.ui.city.adapter.SelectionAdapter;
import com.a16lao.wyh.utils.ToastUtils;
import com.a16lao.wyh.widget.CustomRing;
import com.a16lao.wyh.widget.PromptView;
import com.a16lao.wyh.widget.banner.Banner;
import com.a16lao.wyh.widget.banner.OnBannerListener;
import com.a16lao.wyh.widget.swipetoloadlayout.OnLoadMoreListener;
import com.a16lao.wyh.widget.swipetoloadlayout.OnRefreshListener;
import com.a16lao.wyh.widget.swipetoloadlayout.SwipeToLoadLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/29 0029 上午 10:21
 * author: caoyan
 * description:精选页面
 */

public class SelectionFragment extends LazyFragment implements View.OnClickListener, OnBannerListener {
    private TextView text_selection_part, text_selection_rank, text_selection_free, text_selection_discount;
    private Banner banner_selection;
    private List<String> urlList;
    private ListView swipe_target;
    private View layout_selection_banner;
    private List<String> mList;
    private SelectionAdapter mAdapter;
    private SwipeToLoadLayout list_selection;

    @Override
    public Object setLayout() {
        return R.layout.fragment_selection;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        swipe_target = rootView.findViewById(R.id.swipe_target);
        list_selection = rootView.findViewById(R.id.list_selection);

    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        mList.add("0");
        mList.add("01");
        mList.add("002");
        mList.add("0000");
        mList.add("00000");
        mList.add("000000");
        mAdapter = new SelectionAdapter(mActivity, mList);
        swipe_target.setAdapter(mAdapter);

        /**
         * 广告+排行榜，书单，限时免费，折扣
         */
        layout_selection_banner = LayoutInflater.from(mActivity).inflate(R.layout.layout_selection_banner, null);
        banner_selection = layout_selection_banner.findViewById(R.id.banner_selection);
        text_selection_part = layout_selection_banner.findViewById(R.id.text_selection_part);
        text_selection_rank = layout_selection_banner.findViewById(R.id.text_selection_rank);
        text_selection_free = layout_selection_banner.findViewById(R.id.text_selection_free);
        text_selection_discount = layout_selection_banner.findViewById(R.id.text_selection_discount);
        swipe_target.addHeaderView(layout_selection_banner);


        urlList = new ArrayList<>();
        urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
        urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");

        banner_selection.setImages(urlList);
        banner_selection.setOffscreenPageLimit(3);
//        banner_selection.setPageTransformer(true, new ScaleTransformer());
        banner_selection.setOnBannerListener(this).start();
        text_selection_part.setOnClickListener(this);
        text_selection_rank.setOnClickListener(this);
        text_selection_free.setOnClickListener(this);
        text_selection_discount.setOnClickListener(this);
        list_selection.setOnRefreshListener(() -> {
            mList.clear();
            mList.add("0");
            mList.add("01");
            mList.add("002");
            mList.add("0000");
            mList.add("00000");
            mList.add("000000");
            mAdapter.notifyDataSetChanged();
            list_selection.setRefreshing(false);
        });
        list_selection.setOnLoadMoreListener(() -> {
            mAdapter.notifyDataSetChanged();
            list_selection.setLoadingMore(false);
        });



    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //排行榜
            case R.id.text_selection_rank:
                toOtherActivity(BookRankActivity.class, null, false);
                break;
            //书单
            case R.id.text_selection_part:
//                toOtherActivity(BookPartActivity.class, null, false);
                toOtherActivity(BookListActivity.class, null, false);
                break;
            //限时免费
            case R.id.text_selection_free:
                toOtherActivity(FreeForLimitedTimeActivity.class, null, false);
                break;
            //折扣
            case R.id.text_selection_discount:
                toOtherActivity(BookDiscountActivity.class, null, false);
                break;
        }
    }

    @Override
    public void OnBannerClick(int position) {

        ToastUtils.showToast(mActivity, position + "");
    }

    @Override
    public void onResume() {
        super.onResume();
        banner_selection.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner_selection.stopAutoPlay();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        banner_selection.releaseBanner();
    }


}
