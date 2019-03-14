package com.a16lao.wyh.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * date:   2018/5/15 0015 下午 4:01
 * author: caoyan
 * description:
 */

public class JsonUtils {
    volatile private static JsonUtils intance  = null;
    private JsonUtils(){}
    public static JsonUtils getIntance() {
        synchronized (JsonUtils.class){
            if (intance == null) {
                intance = new JsonUtils();
            }}
        return intance;
    }



    public final <T> T parseObject(String data, Class<T> clazz) {
        return JSON.parseObject(data, clazz);
    }


    public final <T> List<T> parseArray(String data, Class<T> clazz) {
        return JSON.parseArray(data, clazz);
    }
}
