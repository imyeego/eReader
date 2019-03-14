package com.a16lao.wyh.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;


import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.net.callback.HttpCallBack;
import com.a16lao.wyh.net.callback.IRequest;
import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.utils.file.FileUtils;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;


/**
 * author: caoyan
 * time:2018/5/5 19:31
 * description:
 */
public class SaveFileTask extends AsyncTask<Object, Integer, File> {
    private final IRequest IREQUEST;
    private final HttpCallBack MHTTPCALLBACK;

    public SaveFileTask(IRequest iRequest, HttpCallBack mHttpCallBack) {
        this.IREQUEST = iRequest;
        this.MHTTPCALLBACK = mHttpCallBack;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody body = (ResponseBody) objects[2];
        final String name = (String) objects[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtils.writeToDisk(is, downloadDir, extension.toUpperCase());
        } else {
            return FileUtils.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        RxBus.getInstance().postSticky(getBundle(false, values[0], 100));
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (MHTTPCALLBACK != null) {
            RxBus.getInstance().postSticky(getBundle(true, 100, 100));
//            MHTTPCALLBACK.onSuccess();
        }
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtils.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplication().startActivity(install);
        }
    }


    private Bundle getBundle(boolean isFinish, long transferredBytes, long totalSize) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isUpdateVersion", true);
        bundle.putBoolean("isUpdateFinish", isFinish);
        bundle.putLong("total", totalSize);
        bundle.putLong("transfer", transferredBytes);
        if (totalSize != 0) {
            bundle.putDouble("percent", transferredBytes / totalSize);
        }
        return bundle;
    }
}
