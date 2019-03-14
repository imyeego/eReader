package com.a16lao.wyh.jni;

/**
 * Created by lz on 2018/6/12 0012.
 */

public class JNIKit {
    static {
        System.loadLibrary("Jni-v1");
    }

    public static native int pow(int num);
    public static native String getString();
}
