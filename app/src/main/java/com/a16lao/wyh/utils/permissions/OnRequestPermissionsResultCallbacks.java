package com.a16lao.wyh.utils.permissions;

import java.util.List;

/**
 * date:   2018/7/2 0002 下午 3:46
 * author: caoyan
 * description:
 */

public interface OnRequestPermissionsResultCallbacks {
    /**
     * @param isAllGranted 是否全部同意
     */
    void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted);

    /**
     * @param isAllDenied 是否全部拒绝
     */
    void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied);
}
