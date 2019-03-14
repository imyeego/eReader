package com.a16lao.wyh.ui.category.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.category.adapter.EssayCategoryDetailAdapter;
import com.a16lao.wyh.ui.category.popupwindow.ChooseTypePopupWindow;
import com.a16lao.wyh.ui.category.popupwindow.DetailOrderPopupWindow;
import com.a16lao.wyh.ui.main.CallBack;

import java.util.ArrayList;
import java.util.List;


/**
 * date:   2018/5/23 0023 下午 3:06
 * author: caoyan
 * description:
 */

public class EssayCategoryDetailActivity extends BaseActivity implements CallBack {
    private TextView text_detail_order, text_detail_item;
    private String title;
    private ImageView icon_detail_order, icon_detail_item;
    private DetailOrderPopupWindow pop;
    private ChooseTypePopupWindow choose;
    private LinearLayout view_detail_order, view_detail_item;
    private View line_category;
    private List<String> mList;
    private ListView list_category_item;

    @Override
    protected int setLayout() {
        return R.layout.activity_category_detail;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        title = getIntent().getExtras().getString("position");
        setTitleMiddleText(title);
        text_detail_order = findViewById(R.id.text_detail_order);
        text_detail_item = findViewById(R.id.text_detail_item);
        icon_detail_order = findViewById(R.id.icon_detail_order);
        view_detail_order = findViewById(R.id.view_detail_order);
        view_detail_item = findViewById(R.id.view_detail_item);
        icon_detail_item = findViewById(R.id.icon_detail_item);
        line_category = findViewById(R.id.line_category);
        list_category_item = findViewById(R.id.list_category_item);
    }

    @Override
    protected void initData() {
        pop = new DetailOrderPopupWindow(this);
        choose = new ChooseTypePopupWindow(this);
        pop.setOnCallBack(this);

        mList = new ArrayList<>();
        mList.add("1");
        mList.add("1");
        list_category_item.setAdapter(new EssayCategoryDetailAdapter(this, mList));

    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void setListener() {
        view_detail_order.setOnClickListener(v -> {
            pop.showPopupWindow(line_category);
            icon_detail_order.setImageResource(pop.isShowing() ? R.drawable.det_up : R.drawable.det_down);
        });
        view_detail_item.setOnClickListener(v -> {
            choose.showPopupWindow(line_category);
            icon_detail_item.setImageResource(choose.isShowing() ? R.drawable.det_up : R.drawable.det_down);
        });
        pop.setOnDismissListener(() -> icon_detail_order.setImageResource(R.drawable.det_down));
        choose.setOnDismissListener(() -> icon_detail_item.setImageResource(R.drawable.det_down));
        choose.setOnCallBack(s -> text_detail_item.setText(s));
    }


    @Override
    public void onCallBack(int position, String str) {
        text_detail_order.setText(str);

    }
}
