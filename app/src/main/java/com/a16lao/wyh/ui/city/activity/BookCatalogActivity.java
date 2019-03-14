package com.a16lao.wyh.ui.city.activity;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.bean.city.BookCatalogChildBean;
import com.a16lao.wyh.bean.city.BookCatalogGroupBean;
import com.a16lao.wyh.ui.city.adapter.BookCatalogExpandAdapter;
import com.a16lao.wyh.ui.mine.activity.VipActivity;
import com.a16lao.wyh.utils.storage.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class BookCatalogActivity extends BaseActivity {
    public String[] groupStrings = {"西游记", "水浒传", "三国演义", "红楼梦"};
    public String[][] childStrings = {
            {"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
            {"宋江", "林冲", "李逵", "鲁智深"},
            {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
            {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}
    };
    private ExpandableListView expanded_book_catalog;
    private BookCatalogExpandAdapter adapter;
    private List<BookCatalogGroupBean> groups;
    private boolean flag = true, isVip = true;
    private TextView text_toolbar_right, text_book_catalog_prompt, text_book_catalog_buy;

    @Override

    protected int setLayout() {
        return R.layout.activity_book_catalog;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        expanded_book_catalog = findViewById(R.id.expanded_book_catalog);
        text_book_catalog_prompt = findViewById(R.id.text_book_catalog_prompt);
        text_book_catalog_buy = findViewById(R.id.text_book_catalog_buy);
        text_toolbar_right = findViewById(R.id.text_toolbar_right);

        text_toolbar_right.setVisibility(View.VISIBLE);
        text_toolbar_right.setText("全选");
        text_toolbar_right.setOnClickListener(v -> isAllSelected());
    }


    @Override
    protected void initData() {
        if (SPUtils.getAppFlag(SPUtils.IS_LOGIN)) {
            text_book_catalog_prompt.setVisibility(View.VISIBLE);
            if (isVip) {
                text_book_catalog_prompt.setVisibility(View.GONE);
            } else {
                text_book_catalog_prompt.setText("开通会员，享全场7.5折优惠，更有海量免费书籍》");
                text_book_catalog_prompt.setOnClickListener(v -> toOtherActivity(VipActivity.class, null, false));
            }


        } else {
            text_book_catalog_prompt.setVisibility(View.VISIBLE);
            text_book_catalog_prompt.setText("登录后可以下载更多章节，去登录》");
            text_book_catalog_prompt.setOnClickListener(v ->
                    toLoginActivity());
        }

        text_book_catalog_prompt.setVisibility(SPUtils.getAppFlag(SPUtils.IS_LOGIN) ? View.GONE : View.VISIBLE);
        groups = new ArrayList<>();
        for (int i = 0; i < groupStrings.length; i++) {
            BookCatalogGroupBean group = new BookCatalogGroupBean();
            group.setGroupName(groupStrings[i]);
            group.setSelected(false);
            List<BookCatalogChildBean> childs = new ArrayList<>();
            for (int j = 0; j < childStrings[i].length; j++) {
                BookCatalogChildBean child = new BookCatalogChildBean();
                child.setSelected(false);
                child.setChildName(childStrings[i][j]);
                childs.add(child);
                child.addObserver(group);
                group.addObserver(child);
            }
            group.setChilds(childs);
            groups.add(group);
        }

        if (adapter == null) {
            adapter = new BookCatalogExpandAdapter(this, groups);
            expanded_book_catalog.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }


    }

    @Override
    protected void setListener() {
        expanded_book_catalog.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            boolean expanded = parent.isGroupExpanded(groupPosition);
            if (expanded) {
                parent.collapseGroup(groupPosition);
            } else {
                parent.expandGroup(groupPosition);
            }
            adapter.setIndicatorState(groupPosition, expanded);
            return true;
        });
        expanded_book_catalog.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            BookCatalogChildBean child = groups.get(groupPosition).getChilds().get(childPosition);
            child.changeChecked();
            isSelectedGroup();

            return false;
        });


    }


    //是否全选
    public void isAllSelected() {

        if (flag) {
            for (BookCatalogGroupBean group : groups) {
                if (!group.isSelected()) {
                    group.changeChecked();
                }
            }
        } else {
            for (BookCatalogGroupBean group : groups) {
                if (group.isSelected()) {
                    group.changeChecked();
                }
            }
        }
        isSelectedGroup();


    }

    //是否有選中章節
    public boolean isSelectedChild() {

        for (BookCatalogGroupBean group : groups) {
            for (BookCatalogChildBean child : group.getChilds()) {
                if (child.isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }


    public void isSelectedGroup() {
        int num = 0;
        for (BookCatalogGroupBean group : groups) {
            if (group.isSelected()) {
                num++;
            }
        }
        if (num == groups.size()) {
            text_toolbar_right.setText("取消全选");
            flag = false;

        } else {
            text_toolbar_right.setText("全选");
            flag = true;
        }
        text_book_catalog_buy.setEnabled(isSelectedChild());
        adapter.notifyDataSetChanged();
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
