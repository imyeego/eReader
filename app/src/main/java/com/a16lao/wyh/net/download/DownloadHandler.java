package com.a16lao.wyh.net.download;


import android.os.AsyncTask;


import com.a16lao.wyh.net.RestCreator;
import com.a16lao.wyh.net.callback.HttpCallBack;
import com.a16lao.wyh.net.callback.IRequest;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * author: caoyan
 * time:2018/5/5 19:23
 * description:
 */
public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final HttpCallBack MHTTPCALLBACK;
    private final IRequest IREQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           HttpCallBack mHttpCallBack,
                           IRequest iRequest,
                           String downloadDir,
                           String extension,
                           String name) {
        this.URL = url;
        this.MHTTPCALLBACK = mHttpCallBack;
        this.IREQUEST = iRequest;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }


    public final void handlerDownload() {
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(IREQUEST, MHTTPCALLBACK);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, response, NAME);
                            if (task.isCancelled()) {
                                if (IREQUEST != null) {
                                    IREQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (MHTTPCALLBACK != null) {
                                MHTTPCALLBACK.onFailure(response.code(), response.message());
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (MHTTPCALLBACK != null) {
                            MHTTPCALLBACK.onFailure(0, t.getMessage());
                        }
                    }
                });
    }
}
