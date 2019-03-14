package com.a16lao.wyh.ui.mine.activity;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.ui.mine.dialog.PhotoDialogFragment;

import com.a16lao.wyh.utils.image.GlideUtils;
import com.bumptech.glide.Glide;


public class AboutMeActivity extends BaseActivity {
    private ImageView icon_mine_avatar;
    private TextView text_personal_name, text_personal_ID, text_personal_phone;
    PhotoDialogFragment fragment;

    @Override
    protected int setLayout() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_personal_data));
        icon_mine_avatar = findViewById(R.id.icon_mine_avatar);
        text_personal_name = findViewById(R.id.text_personal_name);
        text_personal_ID = findViewById(R.id.text_personal_ID);
        text_personal_phone = findViewById(R.id.text_personal_phone);
    }

    @Override
    protected void initData() {
        fragment = new PhotoDialogFragment();
    }

    @Override
    protected void setListener() {
        icon_mine_avatar.setOnClickListener(v -> {
            fragment.show(getFragmentManager(), "");
            fragment.setOnCallBack(new PhotoDialogFragment.OnCallBack() {
                @Override
                public void onCallBack(Bitmap bitmap) {
                    Glide.with(AboutMeActivity.this).load(bitmap).into(icon_mine_avatar);
                    fragment.dismiss();
                }

                @Override
                public void onCallBack(String path) {
                    GlideUtils.loadCircle(AboutMeActivity.this, path, R.drawable.avatar, icon_mine_avatar);
                    fragment.dismiss();
                }

            });
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
