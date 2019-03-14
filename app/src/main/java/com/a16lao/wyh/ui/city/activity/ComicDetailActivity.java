package com.a16lao.wyh.ui.city.activity;

import android.view.View;
import android.widget.ImageView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.city.popupwindow.ComicMorePopUpWindow;

public class ComicDetailActivity extends BaseActivity {
    private View line_comic;
    private ImageView icon_comic_detail;

    @Override
    protected int setLayout() {
        return R.layout.activity_comic_detail;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        line_comic = findViewById(R.id.line_comic);
        icon_comic_detail = findViewById(R.id.icon_comic_detail);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        icon_comic_detail.setOnClickListener(v -> {
            ComicMorePopUpWindow morePopUpWindow = new ComicMorePopUpWindow(this);
            morePopUpWindow.showPopupWindow(line_comic);
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
