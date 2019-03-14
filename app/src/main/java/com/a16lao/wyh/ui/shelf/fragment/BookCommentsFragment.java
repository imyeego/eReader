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
import com.a16lao.wyh.bean.shelf.BookCommentBean;
import com.a16lao.wyh.ui.shelf.adapter.BookCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.a16lao.wyh.widget.pageindecator.ScrollState.SCROLL_STATE_IDLE;


public class BookCommentsFragment extends LazyFragment {

    private Context context;
    private RecyclerView recyclerView;
    private BookCommentAdapter adapter;
    private List<BookCommentBean> list = new ArrayList<>();

    @Override
    public Object setLayout() {
        return R.layout.fragment_book_comments;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        context = getContext();
        recyclerView = rootView.findViewById(R.id.rv_book_comments);


    }

    @Override
    protected void initData() {

        String[] dates = new String[]{"2018-05-10", "2018-05-01", "2018-04-20"};
        String[] times = new String[]{"18:56", "12:01", "15:21"};
        String[] contents = new String[]{"封面真好看！！这本书的中、日译文版绝对超过原版！"
                , "美国传记的叙事风格，细节多，巨细靡遗。不习惯的会觉得啰嗦，习惯了会觉得这种传记才过瘾。个人觉得翻译很好，起码态度就很认真，应该是核对过日语原文的。"
                , "作者的一腔深情"};

        String[] imgUrls = new String[]{"https://img1.doubanio.com/view/subject/l/public/s29799429.jpg"
                , "https://img1.doubanio.com/view/subject/l/public/s29799429.jpg"
                , "https://img1.doubanio.com/view/subject/l/public/s29799429.jpg"};
        String[] bookNames = new String[]{"道岳独尊", "荣耀法兰西", "娱乐春秋"};
        String[] chapterNames = new String[]{"远航之帆", "梦枕溪", "然澈"};
        String[] loadingStates = new String[]{"连载", "完结", "连载"};

        for (int i = 0; i < 3; ++ i){

            BookCommentBean bean = new BookCommentBean();
            bean.setDate(dates[i]);
            bean.setTime(times[i]);
            bean.setImgUrl(imgUrls[i]);
            bean.setContent(contents[i]);
            bean.setChapterName(chapterNames[i]);
            bean.setLoadingState(loadingStates[i]);
            bean.setBookName(bookNames[i]);
            list.add(bean);
        }

        adapter = new BookCommentAdapter(context, list);
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
