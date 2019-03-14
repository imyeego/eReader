package com.a16lao.wyh.zxing.decode;

import com.google.zxing.Result;


public interface DecodeImgCallback {
     void onImageDecodeSuccess(Result result);

     void onImageDecodeFailed();
}
