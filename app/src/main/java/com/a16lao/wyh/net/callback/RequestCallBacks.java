package com.a16lao.wyh.net.callback;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author: caoyan
 * time:2018/5/4 13:06
 * description:
 */
public class RequestCallBacks implements Callback<String> {
    private final IRequest IREQUEST;
    private final HttpCallBack MHTTPCALLBACK;

    public RequestCallBacks(IRequest iRequest,HttpCallBack mHttpCallBack) {
        this.IREQUEST = iRequest;
        this.MHTTPCALLBACK = mHttpCallBack;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (MHTTPCALLBACK != null) {
                    MHTTPCALLBACK.onSuccess(response);
                }
            }
        } else {
            if (MHTTPCALLBACK != null) {
                MHTTPCALLBACK.onFailure(response.code(), response.message());
            }
        }


    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (MHTTPCALLBACK != null) {
            MHTTPCALLBACK.onFailure(1, t.getMessage().equals("") ? "请求服务器有误" : t.getMessage());
        }
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
    }


}
