package com.a16lao.wyh.ui.city.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.bean.city.BookListBean;
import com.a16lao.wyh.ui.city.adapter.BookListAdapter;
import com.a16lao.wyh.widget.RecyclerViewItemDecoration;
import com.a16lao.wyh.widget.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;


public class BookListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private Context context;
    private List<BookListBean> list = new ArrayList<>();
    private List<String> urlList = new ArrayList<>();
    private BookListAdapter adapter;
    private ImageView ivToolBarBack;
    private TextView banner;
    @Override
    protected int setLayout() {
        return R.layout.activity_book_list;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        context = this;
        recyclerView = findViewById(R.id.rv_book_list);
        ivToolBarBack = findViewById(R.id.iv_toolbar_back);
        banner = findViewById(R.id.banner);
//        view = LayoutInflater.from(context).inflate(R.layout.a1, recyclerView, false);
//        banner = view.findViewById(R.id.banner);

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        RecyclerViewItemDecoration decoration = new RecyclerViewItemDecoration(14);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new BookListAdapter(list, context);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        list.clear();
        urlList.clear();
        String imgUrls[] = new String[]{"https://img3.doubanio.com/view/subject/m/public/s29663440.jpg"
            ,"https://img3.doubanio.com/view/subject/m/public/s29788263.jpg"
            ,"https://img3.doubanio.com/view/subject/m/public/s29762735.jpg"
            ,"https://img3.doubanio.com/view/subject/m/public/s29826875.jpg"
            ,"https://img3.doubanio.com/view/subject/m/public/s29825685.jpg"
            ,"https://img3.doubanio.com/view/subject/m/public/s29817052.jpg"
            ,"https://img1.doubanio.com/view/subject/m/public/s29774299.jpg"
            ,"https://img1.doubanio.com/view/subject/m/public/s29799269.jpg"
            ,"https://img1.doubanio.com/view/subject/m/public/s29756387.jpg"
            ,"https://img1.doubanio.com/view/subject/m/public/s29791279.jpg"
            ,"https://img3.doubanio.com/view/subject/m/public/s29769915.jpg"
            ,"https://img3.doubanio.com/view/subject/m/public/s29779230.jpg"};

        String listName[] = new String[]{"为什么我们都爱穿越小说", "愿化作春风，为你吹来柔情",
            "同样青春，不同故事", "你一定很忙", "柿子红了", "呆萌男友囧事记录",
            "观山海", "失踪的孩子","断代", "扫地出门","偷影子的人", "呼啸山庄"};
        String listDescription[] = new String[]{"他单枪匹马，把中国科幻提升到了世界水平。雷军力荐！"
            ,"你不可不读的世界文学名著大合集，中央级专业翻译出版社权威打造！"
            ,"最“信达雅”的朱生豪译本，在哈姆雷特身上发现我们共同的影子"
            ,"当代散文大家”林清玄自选集，人生至美是清欢"
            ,"法兰西的莎士比亚”雨果作品大全集，人民文学出品！"
            ,"风靡中日，感动无数人的“治愈系”漫画小说化！"
            ,"自在独行"
            ,"费兰特的野心从来不止于描写一种女性的处境，女性的命运。"
            ,"观山海无边，感万物有灵"
            ,"要记着为时代划下油彩的人，要记得从地下走上地面的路。"
            ,"用一种轻情节化的方式叙述了一个人的一生，字里行间散发着优美的诗意。"
            ,"风格和结构是一部书的精华，伟大的思想不过是空洞的废话。"};

        for (int i = 0; i < 12; ++i){
            BookListBean bean = new BookListBean();
            if (i < 3){
                urlList.add(imgUrls[i]);
            }
            bean.setBookListName(listName[i]);
            bean.setBookListDescription(listDescription[i]);
            bean.setBookListCover(imgUrls[i]);

            list.add(bean);
        }
        adapter.notifyDataSetChanged();



    }

    @Override
    protected void setListener() {
        ivToolBarBack.setOnClickListener(view -> finish());
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
