package com.a16lao.wyh.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

/**
 * date:   2018/5/17 0017 下午 4:14
 * author: caoyan
 * description:
 */

public class IntentUtils {

    //获取跳转到权限列表的intent
    public static Intent getAppDetailSettingIntent(Context mContext) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
        return localIntent;
    }

    //获取跳转到相机界面的intent
    public static Intent getCameraIntent(Uri mUri) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        return cameraIntent;

    }

    //获取跳转到图库的intent
    public static Intent getImageIntent() {
        Intent mIntent = new Intent(Intent.ACTION_PICK);
        mIntent.setType("image/*");//相片类型
        return mIntent;
    }

    //跳转到打电话的界面
    public static Intent getCallIntent(String telNumber) {
        Uri uri = Uri.parse("tel:" + telNumber);
        return new Intent(Intent.ACTION_DIAL, uri);
    }
}
