package com.a16lao.wyh.ui.mine.dialog;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseDialogFragment;
import com.a16lao.wyh.utils.IntentUtils;
import com.a16lao.wyh.utils.StringUtils;
import com.a16lao.wyh.utils.file.FileUtils;
import com.a16lao.wyh.utils.permissions.OnRequestPermissionsResultCallbacks;
import com.a16lao.wyh.utils.permissions.PermissionUtils;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * date:   2018/6/29 0029 下午 4:48
 * author: caoyan
 * description:
 */

public class PhotoDialogFragment extends BaseDialogFragment implements OnRequestPermissionsResultCallbacks {
    private TextView text_us_camera, text_us_photo, text_us_cancel;
    private final int REQUEST_CODE_CAMERA = 101;
    private final int REQUEST_CODE_PHOTO = 102;

    @Override
    protected Object setLayout() {
        return R.layout.dialog_photo;
    }

    @Override
    protected void initView() {
        text_us_camera = rootView.findViewById(R.id.text_us_camera);
        text_us_photo = rootView.findViewById(R.id.text_us_photo);
        text_us_cancel = rootView.findViewById(R.id.text_us_cancel);
        text_us_camera.setOnClickListener(v -> {
            if (PermissionUtils.getCameraPermissions(getActivity(), REQUEST_CODE_CAMERA)) {
                //相机
                Intent intent = IntentUtils.getCameraIntent(Uri.fromFile(new File(FileUtils.CAMERA_PHOTO_DIR)));
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }


        });
        text_us_photo.setOnClickListener(v -> {
            if (PermissionUtils.getCameraPermissions(getActivity(), REQUEST_CODE_PHOTO)) {
                //相册
                Intent intent = IntentUtils.getImageIntent();
                startActivityForResult(intent, REQUEST_CODE_PHOTO);
            }
        });
        text_us_cancel.setOnClickListener(v -> dismiss());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case REQUEST_CODE_CAMERA:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    mOnCallBack.onCallBack(bitmap);
                    break;
                case REQUEST_CODE_PHOTO:
                    Uri uri = data.getData();
                    String path = getImagePath(uri, null);
                    mOnCallBack.onCallBack(path);
                    break;
            }
        }


    }


    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    protected int initGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms, boolean isAllGranted) {
//相机
        Intent intent = IntentUtils.getCameraIntent(Uri.fromFile(new File(FileUtils.CAMERA_PHOTO_DIR)));
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms, boolean isAllDenied) {

    }

    OnCallBack mOnCallBack;

    public void setOnCallBack(OnCallBack mOnCallBack) {
        this.mOnCallBack = mOnCallBack;
    }

    public interface OnCallBack {
        void onCallBack(Bitmap bitmap);

        void onCallBack(String path);
    }


}
