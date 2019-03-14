package com.a16lao.wyh.ui.shelf.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.bean.shelf.Essay;
import com.a16lao.wyh.ui.shelf.adapter.EssayAdapter;
import com.a16lao.wyh.ui.shelf.event.AllSelected;
import com.a16lao.wyh.ui.shelf.event.EssayMultiSelector;
import com.a16lao.wyh.ui.shelf.event.ItemSelected;
import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.utils.storage.SPUtils;
import com.a16lao.wyh.widget.MultiOptionView.MultiOptionView;
import com.koolearn.android.kooreader.RecyclerItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class EssaySetFragment extends LazyFragment implements EssayAdapter.DataChangedListener{

    private Context context;
    private List<Essay> list = new ArrayList<>();
    private EssayAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvEssaySum;

    @Override
    public Object setLayout() {
        return R.layout.fragment_essayset;
    }

    @Override
    public void onBindView(Bundle savedInstanceState, View rootView) {
        context = getContext();
        EventBus.getDefault().register(this);
        recyclerView = rootView.findViewById(R.id.rv_essaySet);
        tvEssaySum = rootView.findViewById(R.id.tv_essay_sum);

    }

    @Override
    protected void initData() {
        list.clear();
        String[] titleList = new String[]{"爱逛菜地的汪曾祺", "听勇气讲座", "我的夏威夷之恋"};
        String[] subTitleList = new String[]{"认识汪曾祺是在上世纪八十年代"
                , "在东莞听一个讲座是很平常和普遍的事，没有什么能够阻挡"
                , "白房子里的美人鱼从哪里来？此文献"};
        String[] imgList = new String[]{"https://wx2.sinaimg.cn/mw690/6c3e6b13gy1ftjpef9x45j20zk0jltd4.jpg"
                , ""
                , "https://wx4.sinaimg.cn/mw690/70707858gy1ftjsw0e2eqj21kw11tteo.jpg"};
        String[] authorNameList = new String[]{"李辉", "占文革", "顾艳"};
        String[] authorAvatarList = new String[]{"https://tva1.sinaimg.cn/crop.83.0.351.351.180/6c3e6b13jw8eqzofyx03kj20c80hkmxf.jpg"
                , "https://tvax1.sinaimg.cn/crop.0.0.512.512.180/006QzXeHly8fjzaipqsu4j30e80e8wfc.jpg"
                , "https://tvax1.sinaimg.cn/crop.238.63.375.375.180/70707858ly8fhjbnuhjh8j20ir0d8mz0.jpg"};
        String[] countList = new String[]{"245", "137", "589"};


        Essay essay;
        for (int i = 0; i < 3;  ++i){
            essay = new Essay();
            essay.setTitle(titleList[i]);
            essay.setSubTitle(subTitleList[i]);
            essay.setImg(imgList[i]);
            essay.setAuthorAvatar(authorAvatarList[i]);
            essay.setAuthorName(authorNameList[i]);
            essay.setCount(countList[i]);
            essay.setSelected(false);
            essay.setClicked(false);
            list.add(essay);
        }

        adapter = new EssayAdapter(list, context);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, onItemClickListener));
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL_LIST));

        tvEssaySum.setText(String.format("共%d篇", list.size()));
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMultiSelected(EssayMultiSelector selector){
        for (Essay book : list){
            book.setSelected(selector.isSelected());
        }
        adapter.notifyDataSetChanged();

        if (selector.isSelected()){
            Activity activity = getActivity();
            MultiOptionView view = new MultiOptionView(activity, getContext());
            view.setAdapter(adapter);
            RxBus.getInstance().postSticky(view);
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onAllSelected(AllSelected selected){
        for (Essay essay : list){
            essay.setClicked(true);
        }
        adapter.notifyDataSetChanged();
    }

    private RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (list.get(position).isSelected()){
                if (list.get(position).isClicked()){
                    list.get(position).setClicked(false);
                }else{
                    list.get(position).setClicked(true);
                }
                adapter.notifyDataSetChanged();
                RxBus.getInstance().postSticky(new ItemSelected());
            }
        }

        @Override
        public void onItemLongClick(final View view, final int position) {

        }
    };

    @Override
    public void dataSetChanged() {
        tvEssaySum.setText(String.format("共%d篇", adapter.getItemCount()));
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
