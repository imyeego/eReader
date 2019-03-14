package com.a16lao.wyh.base;

import android.app.Activity;


import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.config.Protocol;
import com.a16lao.wyh.net.RestClient;
import com.a16lao.wyh.net.RestCreator;
import com.a16lao.wyh.net.callback.HttpCallBack;
import com.a16lao.wyh.utils.LogUtils;
import com.a16lao.wyh.utils.ToastUtils;
import com.alibaba.fastjson.JSON;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * date:   2018/5/15 0015 下午 2:29
 * author: caoyan
 * description:
 */

public class BasePresenter<T extends BaseView> {

    protected T mView;
    protected Activity activity;
    protected final String TAG = "======" + getClass().getSimpleName() + "======";

    public BasePresenter(T t) {
        this.mView = t;
        if (mView instanceof Activity) {
            activity = (Activity) mView;
        }
    }

    public void dispatchView() {
        mView = null;
    }


    protected void addJsonRequest(String url, WeakHashMap<String, Object> params, HttpCallBack httpCallBack) {
        RestClient.builder().url(url).raw(JSON.toJSONString(params)).onHttpCallBack(httpCallBack).build().post();
    }


}
