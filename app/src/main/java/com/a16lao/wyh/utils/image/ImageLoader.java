package com.a16lao.wyh.utils.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * date:   2018/5/18 0018 下午 1:50
 * author: caoyan
 * description:
 */

public interface ImageLoader {
    void load(Context context, String url, ImageView imageView);
    void loadRes(Context context, int resId, ImageView imageView);
}
