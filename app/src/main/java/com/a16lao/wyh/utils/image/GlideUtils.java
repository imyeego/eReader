package com.a16lao.wyh.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

/**
 * date:   2018/5/18 0018 下午 2:29
 * author: caoyan
 * description:
 */

public class GlideUtils {

    private static GlideImageLoader sGlideImageLoader = new GlideImageLoader();

    //网络图片
    public static void load(Context context, String url, ImageView imageView) {
        sGlideImageLoader.load(context, url, imageView);
    }

    public static void load(Context context, String url, ImageView imageView, RequestOptions requestOptions) {
        sGlideImageLoader.load(context, url, imageView, requestOptions);
    }

    //本地图片
    public static void loadRes(Context context, int resId, ImageView imageView) {
        sGlideImageLoader.loadRes(context, resId, imageView);
    }

    public static void loadRes(Context context, int resId, ImageView imageView, RequestOptions requestOptions) {
        sGlideImageLoader.loadRes(context, resId, imageView, requestOptions);
    }


    //圆形图片(默认)
    public static void loadCircle(Context context, String url, int resId, ImageView imageView) {
        sGlideImageLoader.loadCircle(context, url, resId, imageView);
    }

    //圆形图片
    public static void loadCircle(Context context, String url, int resId, ImageView imageView,
                                  int placeHolder, int error) {
        sGlideImageLoader.loadCircle(context, url, resId, imageView, placeHolder, error);
    }

    //圆角图片（默认）
    public static void loadRound(Context context, String url, int resId, ImageView imageView) {
        sGlideImageLoader.loadRound(context, url, resId, imageView);
    }

    //圆角图片
    public static void loadRound(Context context, String url, int resId, int dp, ImageView imageView,
                              int placeHolder, int error) {
        sGlideImageLoader.loadRound(context, url, resId, dp, imageView, placeHolder, error);
    }
}

