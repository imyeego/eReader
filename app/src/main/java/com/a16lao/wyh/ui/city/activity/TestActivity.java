package com.a16lao.wyh.ui.city.activity;

import android.annotation.SuppressLint;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


import android.util.DisplayMetrics;
import android.view.LayoutInflater;


import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.widget.ScrollView;
import android.widget.TextView;


import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.adapter.BookPartAdapter;


import com.a16lao.wyh.utils.ToastUtils;

import com.a16lao.wyh.widget.RecyclerViewItemDecoration;
import com.a16lao.wyh.widget.banner.Banner;
import com.a16lao.wyh.widget.banner.OnBannerListener;

import com.a16lao.wyh.widget.banner.ScaleTransformer;

import com.a16lao.wyh.widget.swipetoloadlayout.SwipeToLoadLayout;
import com.a16lao.wyh.widget.swipetoloadlayout.TwitterRefreshHeaderView;


import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/29 0029 上午 11:17
 * author: caoyan
 * description:书单
 */

public class TestActivity extends BaseActivity implements OnBannerListener {

    private List<String> list = new ArrayList<>();

    private Banner banner;
    private List<String> urlList;
    private List<View> mImageViews;
    private BookPartAdapter mPartAdapter;
    int num = 1;

    private TextView textView;
    private RecyclerView recyclerView;
    private SwipeToLoadLayout swipe_book_part;
    private ImageView iv_banner, icon_book_part;
    private View view;
    // 是否正在放大
    private Boolean mScaling = false;
    private DisplayMetrics metric;

    private TwitterRefreshHeaderView swipe_refresh_header;
    private TwitterRefreshHeaderView.CallBack mCallBack;
    private ScrollView mScrollView;

    @Override
    public boolean isTranslucent() {
        return true;
    }

    @Override
    protected int setLayout() {
        return R.layout.a;
    }

    @Override
    protected void getFromIntentData() {

    }


    @Override
    protected void initView() {

        recyclerView = findViewById(R.id.swipe_target);
        icon_book_part = findViewById(R.id.icon_book_part);
        swipe_book_part = findViewById(R.id.list_a);
        iv_banner = findViewById(R.id.iv_banner);
        swipe_refresh_header = findViewById(R.id.swipe_refresh_header);
        textView = findViewById(R.id.textview);
        mScrollView = findViewById(R.id.scrollView);
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }
        metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);


    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {
        setTitleMiddleText("书单");
        urlList = new ArrayList<>();

        urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
        urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
        urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
        urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
        urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
        urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
        urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
        urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
        urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RecyclerViewItemDecoration decoration = new RecyclerViewItemDecoration(14);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(manager);
//        recyclerView.setNestedScrollingEnabled(false);

//        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
//        recyclerView.setHasFixedSize(true);
        mPartAdapter = new BookPartAdapter(urlList, mActivity);
        recyclerView.setAdapter(mPartAdapter);


        view = LayoutInflater.from(TestActivity.this).inflate(R.layout.a1, recyclerView, false);

        banner = view.findViewById(R.id.banner);

        banner.setImages(urlList);
        banner.setOffscreenPageLimit(3);
        banner.setViewPagerMargin();
        banner.setPageTransformer(true, new ScaleTransformer());
        banner.setOnBannerListener(this).start();

        mImageViews = new ArrayList<>();
        mImageViews.add(View.inflate(mActivity, R.layout.activity_book_discount, null));
        mImageViews.add(View.inflate(mActivity, R.layout.list_item, null));
        mImageViews.add(View.inflate(mActivity, R.layout.list_free_item, null));

        mPartAdapter.setHeaderView(view);
        int offset = (int) getResources().getDimension(R.dimen.refresh_header_height_twitter);
        swipe_refresh_header.OnCallBack(new TwitterRefreshHeaderView.CallBack() {
            @Override
            public void setOnCallBack(int y, boolean isComplete, boolean automatic) {
                if (!isComplete) {

                    if (y >= offset) {
                        iv_banner.setVisibility(View.VISIBLE);
                        icon_book_part.setVisibility(View.GONE);

                    } else if (y < offset) {
                        iv_banner.setVisibility(View.VISIBLE);
                        icon_book_part.setVisibility(View.GONE);
                    }
                } else {
                    iv_banner.setVisibility(View.VISIBLE);
                    icon_book_part.setVisibility(View.GONE);


                }
            }

            @Override
            public void setOnRefresh() {
                iv_banner.startAnimation(AnimationUtils.loadAnimation(TestActivity.this, R.anim.scale));
            }

            @Override
            public void setOnComplete() {
                iv_banner.clearAnimation();
            }
        });

        swipe_book_part.setOnRefreshListener(() -> {
            num = 1;
            urlList.clear();
            urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
            urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
            urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
            mPartAdapter.notifyDataSetChanged();
            swipe_book_part.setRefreshing(false);


        });


        swipe_book_part.setOnLoadMoreListener(() -> {
            int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
            int lastVisiblePos = getMaxElem(lastVisiblePositions);
            //加载更多功能的代码
            ToastUtils.showToast(mActivity, "刷新");
            urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
            urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
            urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
            urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
            mPartAdapter.notifyItemInserted(lastVisiblePos);

            swipe_book_part.setLoadingMore(false);
        });

    }


    private int getMaxElem(int[] arr) {
        int maxVal = Integer.MIN_VALUE;
        for (int anArr : arr) {
            if (anArr > maxVal)
                maxVal = anArr;
        }
        return maxVal;
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void OnBannerClick(int position) {

    }

}
