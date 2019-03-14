package com.a16lao.wyh.utils.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.a16lao.wyh.config.Latte;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * author: caoyan
 * time:2018/5/7 23:31
 * description:
 */
public class SPUtils {
    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplication());
    private static final String APP_PREFERENCES_KEY = "profile";
    public static final String IS_LOGIN = "isLogin";
    public static final String IS_FIRST = "isFirst";
    public static final String IS_NIGHT = "isNight";
    public static final String BRIGHTNESS = "brightness";
    public static final String AUTO_BRIGHTNESS = "auto";

    private static SharedPreferences getAppPreference() {
        return PREFERENCES;
    }

    public static void setAppInt(String key, int flag) {
        getAppPreference().edit().putInt(key, flag).apply();

    }

    public static int getAppInt(String key) {
        return getAppPreference().getInt(key, 0);
    }


//    public static JSONObject getAppProfileJson() {
//        final String profile = getAppProfile();
//        return JSON.parseObject(profile);
//    }


    public static void clearAppPrefenences() {
        getAppPreference().edit().clear().apply();
    }

    public static void setAppFlag(String key, boolean flag) {
        getAppPreference()
                .edit()
                .putBoolean(key, flag)
                .apply();
    }

    public static boolean getAppFlag(String key) {
        return getAppPreference()
                .getBoolean(key, false);
    }

    public static void addCustomAppProfile(String key, String val) {
        getAppPreference()
                .edit()
                .putString(key, val)
                .apply();
    }

    public static String getCustomAppProfile(String key) {
        return getAppPreference().getString(key, "");
    }

    public static void saveObject(String key, Object object){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);

            String base64 = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            getAppPreference().edit().putString(key, base64).commit();

            oos.close();
            baos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Object getObject(String key){
        Object object = null;
        String str = getAppPreference().getString(key, "");
        byte[] base64 = Base64.decode(str, Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);

        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            object = ois.readObject();
            if (object != null){
                return object;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveStringSet(String key, Set<String> set){
        getAppPreference().edit().putStringSet(key, set).commit();
    }

    public static Set<String> getStringSet(String key){
        return getAppPreference().getStringSet(key, null);
    }

}
