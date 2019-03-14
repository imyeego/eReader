package com.a16lao.wyh.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.a16lao.wyh.bean.MyObjectBox;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.config.ProtocolBase;
import com.koolearn.klibrary.ui.android.library.ZLAndroidApplication;

import io.objectbox.BoxStore;


/**
 * date:   2018/5/15 0015 上午 11:06
 * author: caoyan
 * description:
 */

public class BaseApplication extends ZLAndroidApplication {
    private static BaseApplication app;

    static {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    BoxStore mBoxStore;

    public static BaseApplication getInstance() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        UETool.showUETMenu();
        app = this;
        Latte.init(this)
                .withApiHost(ProtocolBase.BASE_URL)
                .withInterceptor(null)
                .configure();
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
    }


}
