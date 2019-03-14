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
import com.a16lao.wyh.bean.shelf.EssayCommentBean;
import com.a16lao.wyh.ui.shelf.adapter.EssayCommentAdapter;

import java.util.ArrayList;
import java.util.List;


public class EssayCommentsFragment extends LazyFragment {

    private Context context;
    private RecyclerView recyclerView;
    private EssayCommentAdapter adapter;
    private List<EssayCommentBean> list = new ArrayList<>();

    @Override
    public Object setLayout() {
        return R.layout.fragment_essay_comments;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        context = getContext();
        recyclerView = rootView.findViewById(R.id.rv_essay_comments);
    }

    @Override
    protected void initData() {

        String[] dates = new String[]{"2018-05-10", "2018-05-01", "2018-04-20"};
        String[] times = new String[]{"18:56", "12:01", "15:21"};
        String[] contents = new String[]{"封面真好看！！这本书的中、日译文版绝对超过原版！"
                , "美国传记的叙事风格，细节多，巨细靡遗。不习惯的会觉得啰嗦，习惯了会觉得这种传记才过瘾。个人觉得翻译很好，起码态度就很认真，应该是核对过日语原文的。"
                , "作者的一腔深情"};
        String[] titles = new String[]{"我爱故乡的杨梅", "你是我遥不可及的梦", "我与地坛"};

        for (int i = 0; i < 3; ++ i){
            EssayCommentBean bean = new EssayCommentBean();
            bean.setDate(dates[i]);
            bean.setTime(times[i]);
            bean.setContent(contents[i]);
            bean.setEssayTitle(titles[i]);

            list.add(bean);

        }
        adapter = new EssayCommentAdapter(context, list);
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
