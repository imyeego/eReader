package com.a16lao.wyh.ui.mine.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;

import com.a16lao.wyh.bean.PushBean;
import com.a16lao.wyh.config.GlobalParam;

import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.utils.storage.SPUtils;


public class PushNotificationActivity extends BaseActivity {
    private TextView text_push_prompt, text_push_explain, text_accept_prompt, text_update_prompt,
            text_reward_prompt, text_recommend_prompt, text_system_prompt;
    private RelativeLayout view_push_accept, view_push_update, view_push_reward, view_push_recommend,
            view_push_system;
    private LinearLayout view_push_prompt;
    private View line_push_space;
    private Switch switch_accept_item, switch_update_item, switch_reward_item, switch_recommend_item,
            switch_system_item;


    @Override
    protected int setLayout() {
        return R.layout.activity_push_notification;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_push_notice));
        text_push_prompt = findViewById(R.id.text_push_prompt);
        text_push_explain = findViewById(R.id.text_push_explain);
        view_push_prompt = findViewById(R.id.view_push_prompt);
        line_push_space = findViewById(R.id.line_push_space);
        //接受推送通知
        view_push_accept = findViewById(R.id.view_push_accept);
        text_accept_prompt = view_push_accept.findViewById(R.id.switch_push_prompt);
        switch_accept_item = view_push_accept.findViewById(R.id.switch_push_item);
        text_accept_prompt.setText(R.string.text_push_accept);

        //书架书籍更新
        view_push_update = findViewById(R.id.view_push_update);
        text_update_prompt = view_push_update.findViewById(R.id.switch_push_prompt);
        switch_update_item = view_push_update.findViewById(R.id.switch_push_item);
        text_update_prompt.setText(R.string.text_update_prompt);
        //收到打赏
        view_push_reward = findViewById(R.id.view_push_reward);
        text_reward_prompt = view_push_reward.findViewById(R.id.switch_push_prompt);
        switch_reward_item = view_push_reward.findViewById(R.id.switch_push_item);
        text_reward_prompt.setText(R.string.text_reward_prompt);
        //收到书评、评文、评论、赞
        view_push_recommend = findViewById(R.id.view_push_recommend);
        text_recommend_prompt = view_push_recommend.findViewById(R.id.switch_push_prompt);
        switch_recommend_item = view_push_recommend.findViewById(R.id.switch_push_item);
        text_recommend_prompt.setText(R.string.text_recommend_prompt);
        //系统通知
        view_push_system = findViewById(R.id.view_push_system);
        text_system_prompt = view_push_system.findViewById(R.id.switch_push_prompt);
        switch_system_item = view_push_system.findViewById(R.id.switch_push_item);
        text_system_prompt.setText(R.string.text_system_prompt);


    }

    @Override
    protected void initData() {
        if (!SPUtils.getAppFlag(GlobalParam.IS_OPEN_PUSH)) {
            SPUtils.setAppFlag(GlobalParam.IS_OPEN_PUSH, true);
            isOpenPush();

        } else {

            if (SPUtils.getAppFlag(GlobalParam.PUSH_ACCEPT)) {
                isOpenPushVisible(true);
                isOtherOpenPush();
            } else {
                isOpenPushVisible(false);
            }
            switch_accept_item.setChecked(SPUtils.getAppFlag(GlobalParam.PUSH_ACCEPT));

        }
    }

    @Override
    protected void setListener() {
        switch_accept_item.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            boolean flag = isChecked;

            switch_accept_item.setChecked(flag);
            SPUtils.setAppFlag(GlobalParam.PUSH_ACCEPT, flag);
            if (flag) {

                isOpenPushVisible(true);
                isOtherOpenPush();
            } else {
                isOpenPushVisible(false);
            }
            RxBus.getInstance().postSticky(new PushBean(isChecked));

        });
        switch_update_item.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            switch_update_item.setChecked(isChecked);
            SPUtils.setAppFlag(GlobalParam.PUSH_UPDATE, isChecked);
        });
        switch_reward_item.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            switch_reward_item.setChecked(isChecked);
            SPUtils.setAppFlag(GlobalParam.PUSH_REWARD, isChecked);
        });
        switch_recommend_item.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            switch_recommend_item.setChecked(isChecked);
            SPUtils.setAppFlag(GlobalParam.PUSH_RECOMMEND, isChecked);
        });
        switch_system_item.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            switch_system_item.setChecked(isChecked);
            SPUtils.setAppFlag(GlobalParam.PUSH_SYSTEM, isChecked);
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    public void isOpenPush() {

        switch_accept_item.setChecked(true);
        SPUtils.setAppFlag(GlobalParam.PUSH_ACCEPT, true);
        SPUtils.setAppFlag(GlobalParam.PUSH_UPDATE, true);
        SPUtils.setAppFlag(GlobalParam.PUSH_REWARD, true);
        SPUtils.setAppFlag(GlobalParam.PUSH_RECOMMEND, true);
        SPUtils.setAppFlag(GlobalParam.PUSH_SYSTEM, true);

        isOpenPushVisible(true);

        isOtherOpenPush();

    }

    public void isOtherOpenPush() {
        switch_update_item.setChecked(SPUtils.getAppFlag(GlobalParam.PUSH_UPDATE));
        switch_reward_item.setChecked(SPUtils.getAppFlag(GlobalParam.PUSH_REWARD));
        switch_recommend_item.setChecked(SPUtils.getAppFlag(GlobalParam.PUSH_RECOMMEND));
        switch_system_item.setChecked(SPUtils.getAppFlag(GlobalParam.PUSH_SYSTEM));
    }

    public void isOpenPushVisible(boolean flag) {
        text_push_prompt.setVisibility(flag ? View.GONE : View.VISIBLE);
        text_push_explain.setVisibility(flag ? View.GONE : View.VISIBLE);
        view_push_prompt.setVisibility(flag ? View.VISIBLE : View.GONE);
        line_push_space.setVisibility(flag ? View.VISIBLE : View.GONE);
    }

}
