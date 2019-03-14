package com.a16lao.wyh.ui.city.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.city.activity.BookDiscountActivity;
import com.a16lao.wyh.ui.city.activity.BookPartActivity;
import com.a16lao.wyh.ui.city.activity.BookRankActivity;
import com.a16lao.wyh.ui.city.activity.FreeForLimitedTimeActivity;
import com.a16lao.wyh.utils.ToastUtils;
import com.a16lao.wyh.widget.banner.Banner;
import com.a16lao.wyh.widget.banner.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/29 0029 上午 10:21
 * author: caoyan
 * description:女频
 */

public class ComicFragment extends LazyFragment implements View.OnClickListener, OnBannerListener {
    private TextView text_selection_part, text_selection_rank, text_selection_free, text_selection_discount;
    private Banner banner_selection;
    private List<String> urlList;


    @Override
    public Object setLayout() {
        return R.layout.fragment_comic;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        text_selection_part = rootView.findViewById(R.id.text_selection_part);
        text_selection_rank = rootView.findViewById(R.id.text_selection_rank);
        text_selection_free = rootView.findViewById(R.id.text_selection_free);
        text_selection_discount = rootView.findViewById(R.id.text_selection_discount);
        banner_selection = rootView.findViewById(R.id.banner_selection);

    }

    @Override
    protected void initData() {
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
                toOtherActivity(BookPartActivity.class, null, false);
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
