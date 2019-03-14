package com.a16lao.wyh.ui.mine.activity;

import android.widget.Switch;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;

public class ReadingSetActivity extends BaseActivity {
    private Switch switch_read_video, switch_read_comic;


    @Override
    protected int setLayout() {
        return R.layout.activity_reading_set;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_reading_setting));
        switch_read_video = findViewById(R.id.switch_read_video);
        switch_read_comic = findViewById(R.id.switch_read_comic);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        switch_read_video.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switch_read_video.setChecked(isChecked);
        });
        switch_read_comic.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switch_read_comic.setChecked(isChecked);
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
