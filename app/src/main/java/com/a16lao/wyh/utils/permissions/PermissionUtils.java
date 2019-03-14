package com.a16lao.wyh.utils.permissions;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.a16lao.wyh.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * date:   2018/7/2 0002 下午 3:04
 * author: caoyan
 * description:
 */

public class PermissionUtils {
    private static String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    /**
     * 是否需要检查权限
     *
     * @return true 需要检查 false不需要
     */
    private static boolean needCheckPermission() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 获取sd存储卡读写权限
     *
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getExternalStoragePermissions(@NonNull Activity activity, int requestCode) {
        return requestPermissions(activity, requestCode, permission[0], permission[1]);
    }

    /**
     * 获取拍照权限
     *
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getCameraPermissions(@NonNull Activity activity, int requestCode) {
        return requestPermissions(activity, requestCode, permission[2], permission[0], permission[1]);
    }

    /**
     * 获取麦克风权限
     *
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getAudioPermissions(@NonNull Activity activity, int requestCode) {
        return requestPermissions(activity, requestCode, Manifest.permission.RECORD_AUDIO, permission[0], permission[1]);
    }

    /**
     * 获取定位权限
     *
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getLocationPermissions(@NonNull Activity activity, int requestCode) {
        return requestPermissions(activity, requestCode, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    /**
     * 获取读取联系人权限
     *
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getContactsPermissions(@NonNull Activity activity, int requestCode) {
        return requestPermissions(activity, requestCode, Manifest.permission.READ_CONTACTS);
    }

    /**
     * 获取发送短信权限
     *
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getSendSMSPermissions(@NonNull Activity activity, int requestCode) {
        return requestPermissions(activity, requestCode, Manifest.permission.SEND_SMS);
    }

    /**
     * 获取拨打电话权限
     *
     * @return 是否已经获取权限，没有自动申请
     */
    public static boolean getCallPhonePermissions(@NonNull Activity activity, int requestCode) {
        return requestPermissions(activity, requestCode, Manifest.permission.CALL_PHONE);
    }


    public static List<String> getDeniedPermissions(@NonNull Activity activity, @NonNull String... permissions) {
        if (!needCheckPermission()) {
            return null;
        }
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
        }
        if (!deniedPermissions.isEmpty()) {
            return deniedPermissions;
        }

        return null;
    }

    /**
     * 是否拥有权限
     */
    public static boolean hasPermissions(@NonNull Activity activity, @NonNull String... permissions) {
        if (!needCheckPermission()) {
            return true;
        }
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否拒绝了再次申请权限的请求（点击了不再询问）
     */
    public static boolean deniedRequestPermissionsAgain(@NonNull Activity activity, @NonNull String... permissions) {
        if (!needCheckPermission()) {
            return false;
        }
        List<String> deniedPermissions = getDeniedPermissions(activity, permissions);
        for (String permission : deniedPermissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_DENIED) {

                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    //当用户之前已经请求过该权限并且拒绝了授权这个方法返回true
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 申请权限<br/>
     * 使用onRequestPermissionsResult方法，实现回调结果或者自己普通处理
     *
     * @return 是否已经获取权限
     */
    public static boolean requestPermissions(Activity activity, int requestCode, String... permissions) {

        if (!needCheckPermission()) {
            return true;
        }

        if (!hasPermissions(activity, permissions)) {
            if (deniedRequestPermissionsAgain(activity, permissions)) {
                Intent intent = IntentUtils.getAppDetailSettingIntent(activity);
                activity.startActivityForResult(intent, requestCode);
                //返回结果onActivityResult
            } else {
                List<String> deniedPermissions = getDeniedPermissions(activity, permissions);
                if (deniedPermissions != null) {
                    ActivityCompat.requestPermissions(activity, deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
                    //返回结果onRequestPermissionsResult
                }
            }
            return false;
        }
        return true;
    }

    /**
     * 申请权限返回方法
     */
    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                  @NonNull int[] grantResults, @NonNull OnRequestPermissionsResultCallbacks callBack) {
        List<String> granted = new ArrayList<>();
        List<String> denied = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String perm = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                granted.add(perm);
            } else {
                denied.add(perm);
            }
        }

        if (null != callBack) {
            if (!granted.isEmpty()) {
                callBack.onPermissionsGranted(requestCode, granted, denied.isEmpty());
            }
            if (!denied.isEmpty()) {
                callBack.onPermissionsDenied(requestCode, denied, granted.isEmpty());
            }
        }


    }

}
