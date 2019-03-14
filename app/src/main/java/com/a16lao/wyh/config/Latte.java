package com.a16lao.wyh.config;

import android.content.Context;


/**
 * author: caoyan
 * time:2018/5/3 21:49
 * description:
 */
public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();

    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfigurations(Object key) {
        return getConfigurator().getConfiguration(key);
    }


    public static Context getApplication() {
        return getConfigurations(ConfigKeys.APPLICATION_CONTEXT);
    }
}
