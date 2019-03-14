package com.a16lao.wyh.base;

import android.app.NotificationManager;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;


import com.a16lao.wyh.R;
import com.a16lao.wyh.net.RestClient;

import com.a16lao.wyh.ui.main.activity.MainActivity;
import com.a16lao.wyh.utils.RxBus;


import rx.functions.Action1;


/**
 * date:   2018/5/28 0028 下午 3:55
 * author: caoyan
 * description:
 */

public class VersionService extends Service {
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private long cc = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        RxBus.getInstance().toObservable(Bundle.class).subscribe(bundle -> {
            if (bundle != null) {
                if (bundle.getBoolean("isUpdateFinish")) {
                    setNotificationClick();
                    stopSelf();

                }
                if (bundle.getBoolean("isUpdateFail")) {
                    notifyNotification("下载失败", 0);
                    stopSelf();
                }
                if (bundle.getBoolean("isUpdate")) {
                    long l = System.currentTimeMillis();
                    if (l - cc >= 1000) {
                        cc = l;
                        notifyNotification("下载中", (int) (bundle.getLong("transfer") * 100 / bundle.getLong("totalSize")));

                    }
                }

            }
        });


        if (mNotificationManager == null) {
            initNotification();
        }
        String url = "";
        if (intent != null) {
            //todo 傳遞過來的url路徑
        }
        if (!TextUtils.isEmpty(url)) {
            downloadFile(url);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void downloadFile(String url) {
        RestClient.builder().url(url).dir("").extension("").name("").build().download();
    }

    private void setNotificationClick() {
        Intent notifyIntent = new Intent(this, MainActivity.class);
//        notifyIntent.putExtra("apkPath", getDownloadFilePath());
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingNotifyIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(pendingNotifyIntent);
    }


    /**
     * 更新通知
     */
    private void notifyNotification(String title, int percent) {
        if (!TextUtils.isEmpty(title)) {
            mBuilder.setContentTitle(title);
        }
        mBuilder.setContentText(percent + "%").setProgress(100, percent, false);
        mNotificationManager.notify(12, mBuilder.build());
    }


    private void initNotification() {
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("正在下载")
                .setContentText("0%")
                .setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setAutoCancel(true);
    }
}
