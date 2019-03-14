package com.a16lao.wyh.net.callback;

import retrofit2.Response;

/**
 * author: caoyan
 * time:2018/5/4 12:24
 * description:
 */
public interface HttpCallBack {
    void onSuccess(Response<String> response);

    void onFailure(int errorNo, String strMsg);
}
