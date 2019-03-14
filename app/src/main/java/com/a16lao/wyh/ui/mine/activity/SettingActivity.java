package com.a16lao.wyh.ui.mine.activity;

import com.a16lao.wyh.base.BaseActivity;

import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.bean.PushBean;
import com.a16lao.wyh.config.GlobalParam;
import com.a16lao.wyh.ui.mine.adapter.SettingAdapter;
import com.a16lao.wyh.ui.mine.dialog.ExitDialogFragment;
import com.a16lao.wyh.ui.mine.dialog.GradeDialogFragment;
import com.a16lao.wyh.utils.ClientAvailableUtils;

import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.utils.file.FileUtils;
import com.a16lao.wyh.utils.storage.SPUtils;
import com.a16lao.wyh.widget.NoScrollListView;

import java.util.Arrays;


public class SettingActivity extends BaseActivity implements SettingAdapter.CallBack {
    private NoScrollListView list_setting;
    private TextView text_setting_exit;
    private SettingAdapter adapter;
    private boolean flag = true;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_mine_setting));
        list_setting = findViewById(R.id.list_setting);
        text_setting_exit = findViewById(R.id.text_setting_exit);

    }

    @Override
    protected void initData() {
        String[] mStrings = getResources().getStringArray(R.array.arr_mine_setting);
        adapter = new SettingAdapter(this, Arrays.asList(mStrings));
        list_setting.setAdapter(adapter);
        adapter.setOnCallBack(this);
        new GradeDialogFragment().show(getFragmentManager(), "");

    }

    @Override
    protected void setListener() {
        text_setting_exit.setOnClickListener(v -> {
            new ExitDialogFragment().show(getFragmentManager(), "");
        });

        list_setting.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                //个人资料
                case 0:
                    toOtherActivity(AboutMeActivity.class, null, false);
                    break;
                //修改密码
                case 1:
                    toOtherActivity(UpdatePasswordActivity.class, null, false);
                    break;
                //阅读设置
                case 2:
                    toOtherActivity(ReadingSetActivity.class, null, false);
                    break;
                //推送通知
                case 3:
                    toOtherActivity(PushNotificationActivity.class, null, false);
                    break;
                //作家入驻规则
                case 4:
                    break;
                //清除缓存
                case 5:
                    break;
                //关于我们
                case 6:
                    toOtherActivity(AboutUsActivity.class, null, false);
                    break;
                //意见反馈
                case 7:
                    toOtherActivity(FeedBackActivity.class, null, false);
                    break;
                //给我们评分
                case 8:
                    ClientAvailableUtils.goToMarket(mActivity, "com.lao16.wyh");
                    break;

            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    public void onPushAccept(TextView view) {
        RxBus.getInstance().toObservableSticky(PushBean.class).subscribe(pushBean -> {
            if (pushBean.isFlag()) {
                view.setText("已开启");
            } else {
                view.setText("未开启");
            }
        });

    }
}
