package com.a16lao.wyh.utils.image;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * date:   2018/5/18 0018 下午 1:50
 * author: caoyan
 * description:
 */

public class GlideImageLoader implements ImageLoader {
    private int default_placeHolder, default_error;

    public GlideImageLoader() {
        default_placeHolder = 0;
        default_error = 0;
    }

    @Override
    public void load(Context context, String url, ImageView imageView) {
        load(context, url, 0, imageView, default_placeHolder, default_error, null);
    }

    public void load(Context context, String url, ImageView imageView, RequestOptions requestOptions) {
        load(context, url, 0, imageView, default_placeHolder, default_error, requestOptions);
    }

    @Override
    public void loadRes(Context context, int resId, ImageView imageView) {
        load(context, null, resId, imageView, default_placeHolder, default_error, null);
    }

    public void loadRes(Context context, int resId, ImageView imageView, RequestOptions requestOptions) {
        load(context, null, resId, imageView, default_placeHolder, default_error, requestOptions);
    }


    public void load(Context context, String url, int resId, ImageView imageView, int placeHolder, int error, RequestOptions requestOptions) {
        if (requestOptions == null) {
            requestOptions = new RequestOptions().placeholder(placeHolder).error(error);
        }
        Glide.with(context).load(TextUtils.isEmpty(url) ? resId : url).apply(requestOptions).into(imageView);
    }


    //圆形图片
    public void loadCircle(Context context, String url, int resId, ImageView imageView) {
        loadCircle(context, url, resId, imageView, default_placeHolder, default_error);
    }

    public void loadCircle(Context context, String url, int resId, ImageView imageView, int placeHolder, int error) {

        RequestOptions requestOptions = new RequestOptions().placeholder(placeHolder).error(error).transform(new GlideCircleTransform());
        Glide.with(context).load(TextUtils.isEmpty(url) ? resId : url).apply(requestOptions).into(imageView);
    }


    //圆角图片
    public void loadRound(Context context, String url, int resId, ImageView imageView) {
        loadRound(context, url, resId, 0, imageView,
                default_placeHolder, default_error);
    }

    public void loadRound(Context context, String url, int resId, int dp, ImageView imageView,
                          int placeHolder, int error) {
        GlideRoundTransform glideRoundTransform = dp == 0 ? new GlideRoundTransform(context) :
                new GlideRoundTransform(context, dp);

        RequestOptions requestOptions = new RequestOptions().placeholder(placeHolder).error(error).transform(glideRoundTransform);
        Glide.with(context).load(TextUtils.isEmpty(url) ? resId : url).apply(requestOptions).into(imageView);
    }
}
