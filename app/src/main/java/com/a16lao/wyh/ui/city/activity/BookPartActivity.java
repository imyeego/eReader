package com.a16lao.wyh.ui.city.activity;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;


import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.adapter.BookPartAdapter;

import com.a16lao.wyh.widget.RecyclerViewItemDecoration;
import com.a16lao.wyh.widget.banner.Banner;
import com.a16lao.wyh.widget.banner.OnBannerListener;
import com.a16lao.wyh.widget.banner.ScaleTransformer;

import com.a16lao.wyh.widget.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/5/29 0029 上午 11:17
 * author: caoyan
 * description:书单
 */

public class BookPartActivity extends BaseActivity implements OnBannerListener {

    private List<String> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private Banner banner_book_part;
    private List<String> urlList;
    private List<View> mImageViews;
    private BookPartAdapter mPartAdapter;
    private SwipeToLoadLayout swipe_book_part;
    private AppBarLayout appbar;
    private android.support.v7.widget.Toolbar toolbar;
    int num = 1;
    private CollapsingToolbarLayout coll;


    @Override
    public boolean isTranslucent() {
        return true;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_book_part;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
//        appbar = findViewById(R.id.appbar);
//        coll = findViewById(R.id.coll);
//        toolbar = findViewById(R.id.toolbar);
//        swipe_book_part = findViewById(R.id.swipe_book_part);
        recyclerView = findViewById(R.id.swipe_target);
        banner_book_part = findViewById(R.id.banner_book_part);
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }


    }

    @Override
    protected void initData() {
        setTitleMiddleText("书单");
        urlList = new ArrayList<>();
        urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
        urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
        urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");

        banner_book_part.setImages(urlList);
        banner_book_part.setOffscreenPageLimit(3);
        banner_book_part.setViewPagerMargin();
        banner_book_part.setPageTransformer(true, new ScaleTransformer());
        banner_book_part.setOnBannerListener(this).start();

        mImageViews = new ArrayList<>();
        mImageViews.add(View.inflate(mActivity, R.layout.activity_book_discount, null));
        mImageViews.add(View.inflate(mActivity, R.layout.list_item, null));
        mImageViews.add(View.inflate(mActivity, R.layout.list_free_item, null));


        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                manager.invalidateSpanAssignments();
//            }
//
//        });


        RecyclerViewItemDecoration decoration = new RecyclerViewItemDecoration(14);

        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(manager);

        mPartAdapter = new BookPartAdapter(urlList, mActivity);
        recyclerView.setAdapter(mPartAdapter);

//
//        swipe_book_part.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                num = 1;
//                urlList.clear();
//                urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
//                urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
//                urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
//                mPartAdapter.notifyDataSetChanged();
//                swipe_book_part.setRefreshing(false);
//            }
//        });
//
//        swipe_book_part.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                num++;
//
//                if (num > 3) {
//
//                } else {
//                    urlList.add("http://pic39.nipic.com/20140226/18071023_164300608000_2.jpg");
//                    urlList.add("http://pic34.photophoto.cn/20150308/0017030549871516_b.jpg");
//                    urlList.add("http://pic102.huitu.com/res/20171205/1301968_20171205200627325080_1.jpg");
//
//
//                }
//                mPartAdapter.notifyDataSetChanged();
//                swipe_book_part.setLoadingMore(false);
//            }
//        });
        appbar.addOnOffsetChangedListener((appBarLayout, i) -> {
            if (i != 0) {
                coll.setContentScrimResource(R.drawable.list_bg);
            }
        });


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
