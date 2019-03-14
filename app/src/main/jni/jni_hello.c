//
// Created by Administrator on 2018/6/12 0012.
//

#include "com_a16lao_wyh_jni_JNIKit.h"
#include "math.h"

JNIEXPORT jint JNICALL Java_com_a16lao_wyh_jni_JNIKit_pow(JNIEnv *env, jclass cls, jint num) {
    return pow(num, 3);
}

JNIEXPORT jstring JNICALL Java_com_a16lao_wyh_jni_JNIKit_getString
        (JNIEnv *env, jclass cls){
    return (*env)->NewStringUTF(env, "From C");
}

