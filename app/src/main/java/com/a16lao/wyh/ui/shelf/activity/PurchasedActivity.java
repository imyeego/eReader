package com.a16lao.wyh.ui.shelf.activity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.bean.shelf.BookHistoryBean;
import com.a16lao.wyh.bean.shelf.PurchasedBookBean;
import com.a16lao.wyh.ui.shelf.adapter.ListPurchasedBookAdapter;
import com.a16lao.wyh.ui.shelf.fragment.DividerItemDecoration;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

import static com.a16lao.wyh.widget.pageindecator.ScrollState.SCROLL_STATE_IDLE;


public class PurchasedActivity extends BaseActivity {
    private Context context;
    private RecyclerView recyclerView;
    private ListPurchasedBookAdapter adapter;

    private List<PurchasedBookBean> list = new ArrayList<>();


    @Override
    protected int setLayout() {
        return R.layout.activity_purchased;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        context = this;
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(context, R.color.white) , true);

        setTitleMiddleText((String) getResources().getText(R.string.text_shelf_book_purchased));
        recyclerView = findViewById(R.id.rv_book_purchased);
    }

    @Override
    protected void initData() {

        String[] imgUrls = new String[]{"https://img1.doubanio.com/view/subject/m/public/s29774838.jpg"
                ,"https://img3.doubanio.com/view/subject/m/public/s29767895.jpg"
                ,"https://img3.doubanio.com/view/subject/m/public/s29767895.jpg"
                ,"https://img3.doubanio.com/view/subject/m/public/s29816532.jpg"
                ,"https://img1.doubanio.com/view/subject/m/public/s29779138.jpg"
                ,"https://img1.doubanio.com/view/subject/m/public/s28982109.jpg"};

        String[] bookNames = new String[]{"道岳独尊", "荣耀法兰西","娱乐春秋","致命亲爱的","生活有泪", "步履不停"};
        String[] loadingStates = new String[]{"连载","完结","连载","完结","连载", "完结"};
        String[] tags = new String[]{"言情", "历史架空", "言情", "现代军事", "言情", "文学"};
        String[] progress = new String[]{"92/323","100/568", "43/7547", "55/200", "252/298", "25/199"};
        boolean[] addShelfs = {false, true, true, false, false, false};

        for (int i = 0; i < 6; ++i){
            PurchasedBookBean bean = new PurchasedBookBean();
            bean.setImgUrl(imgUrls[i]);
            bean.setBookName(bookNames[i]);
            bean.setIsFinished(loadingStates[i]);
            bean.setTag(tags[i]);
            bean.setProgress(progress[i]);
            bean.setAddedShelf(addShelfs[i]);

            list.add(bean);
        }

        adapter = new ListPurchasedBookAdapter(context, list);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源
                    adapter.setScrolling(false);
                    adapter.notifyDataSetChanged(); // notify调用后onBindViewHolder会响应调用
                } else
                    adapter.setScrolling(true);
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(8);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
