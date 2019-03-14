package com.a16lao.wyh.net;

import android.content.Context;


import com.a16lao.wyh.net.callback.HttpCallBack;
import com.a16lao.wyh.net.callback.IRequest;
import com.a16lao.wyh.utils.storage.SPUtils;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * author: caoyan
 * time:2018/5/4 12:31
 * description:
 */
public class RestClientBuilder {
    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private HttpCallBack mHttpCallBack = null;
    private IRequest mIrequest = null;
    private RequestBody mBody = null;
    //    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {

//        PARAMS.put("token", SPUtils.getAppFlag(""));
        PARAMS.putAll(params);

        return this;
    }


    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIrequest = iRequest;
        return this;
    }

    public final RestClientBuilder onHttpCallBack(HttpCallBack mHttpCallBack) {
        this.mHttpCallBack = mHttpCallBack;
        return this;
    }


//    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
//        this.mContext = context;
////        this.mLoaderStyle = loaderStyle;
//        return this;
//    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
//        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mHttpCallBack, mIrequest, mDownloadDir, mExtension, mName, mBody, mFile, mContext);
    }
}
