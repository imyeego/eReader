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
import com.a16lao.wyh.bean.shelf.EssayHistoryBean;
import com.a16lao.wyh.ui.shelf.adapter.EssayHistoryAdapter;

import java.util.ArrayList;
import java.util.List;


public class EssayHistoryFragment extends LazyFragment {

    private Context context;
    private List<EssayHistoryBean> list;
    private RecyclerView recyclerView;
    private EssayHistoryAdapter adapter;

    @Override
    public Object setLayout() {
        return R.layout.fragment_essay_history;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        context = getContext();
        recyclerView = rootView.findViewById(R.id.rv_essay_history);
    }

    @Override
    protected void initData() {

        list = new ArrayList<>();

        String[] titles = new String[]{"爱逛菜地的汪曾祺", "听勇气讲座", "我的夏威夷之恋", "你是我遥不可及的梦"};
        String[] subTitleList = new String[]{"平凡的送貨員──青柳雅春和多年不見的老友森田森吾偶遇，卻突然被告知首相即將被暗殺，而青柳"
                , "《我的职业是小说家》是村上春树首部自传性作品，历时六年完成。 一个人，写作三十五年，十三"
                , "这是一部动人心弦的、平缓舒雅的、略带感伤的恋爱小说。小说主人公渡边以第一人称展开他同两"
                , "2011的夏天，东京郊外一对普通夫妇惨遭杀害。凶手山神一也作案后逗留屋内长达六个小时，并用被害人的血在墙上留了一个“怒”字"};

        boolean[] addShelfs = {false, true, true, false};

        for (int i = 0 ; i < titles.length; ++ i){

            EssayHistoryBean bean = new EssayHistoryBean();
            bean.setTitle(titles[i]);
            bean.setSubTitle(subTitleList[i]);
            bean.setAddShelf(addShelfs[i]);

            list.add(bean);
        }


        adapter = new EssayHistoryAdapter(list);

        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
