package com.a16lao.wyh.ui.shelf.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.bean.shelf.BookHistoryBean;
import com.a16lao.wyh.ui.shelf.adapter.BookHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.a16lao.wyh.widget.pageindecator.ScrollState.SCROLL_STATE_IDLE;


public class BookHistoryFragment extends LazyFragment{

    private Context context;
    private RecyclerView recyclerView;
    private BookHistoryAdapter adapter;
    private List<BookHistoryBean> list = new ArrayList<>();



    @Override
    public Object setLayout() {
        return R.layout.fragment_book_history;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        context = getContext();
        recyclerView = rootView.findViewById(R.id.rv_book_history);
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
            BookHistoryBean bean = new BookHistoryBean();
            bean.setImgUrl(imgUrls[i]);
            bean.setBookName(bookNames[i]);
            bean.setIsFinished(loadingStates[i]);
            bean.setTag(tags[i]);
            bean.setProgress(progress[i]);
            bean.setAddedShelf(addShelfs[i]);

            list.add(bean);
        }

        adapter = new BookHistoryAdapter(context, list);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源，极大提升流畅度
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
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL_LIST));

    }



    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
