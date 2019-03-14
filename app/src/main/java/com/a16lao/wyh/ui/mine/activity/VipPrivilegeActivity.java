package com.a16lao.wyh.ui.mine.activity;

import android.widget.ListView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.bean.mine.VipBean;
import com.a16lao.wyh.ui.mine.adapter.VipPrivilegeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VipPrivilegeActivity extends BaseActivity {
    private ListView list_vip_explain;
    private List<VipBean> mVipBeanList;

    @Override
    protected int setLayout() {
        return R.layout.activity_vip_privilege;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        setTitleMiddleText(getString(R.string.text_vip_privilege));
        list_vip_explain = findViewById(R.id.list_vip_explain);
    }

    @Override
    protected void initData() {
        mVipBeanList = new ArrayList<>();
        String[] array = getResources().getStringArray(R.array.arr_vip_privilege);
        mVipBeanList.add(new VipBean(array[0], true, true));
        mVipBeanList.add(new VipBean(array[1], true, true));
        mVipBeanList.add(new VipBean(array[2], true, true));
        mVipBeanList.add(new VipBean(array[3], true, true));
        mVipBeanList.add(new VipBean(array[4], false, true));
        mVipBeanList.add(new VipBean(array[5], false, true));
        mVipBeanList.add(new VipBean(array[6], false, true));
        mVipBeanList.add(new VipBean(array[7], false, true));

        list_vip_explain.setAdapter(new VipPrivilegeAdapter(this, mVipBeanList));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
