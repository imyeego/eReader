package com.a16lao.wyh.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.utils.MyAnimationDrawable;

import java.math.BigDecimal;
import java.util.Timer;
import java.util.TimerTask;

/**
 * date:   2018/7/23 0023 下午 4:58
 * author: caoyan
 * description:
 */

public class FloatingLayer {
    private static final String TAG = "FLOATINGLAYER";
    public static boolean ISSHOW = false;
    private static FloatingLayer sFloatingLayer;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    private Context mContext;

    private View mPopView;

    private AnimationTimerTask mAnimationTask;
    private Timer mAnimationTimer;
    private GetTokenRunnable mGetTokenRunnable;

    private FloatingLayerListener mListener;

    private Handler mHander = new Handler();

    private int mWidth;
    private int mHeight;
    private float mPrevX;
    private float mPrevY;
    private int mGetTokenPeriodTime = 500;
    private int mAnimatonPeriodTime = 16;
    private boolean isMove = false;
    private BigDecimal mStartClickTime;
    private ImageView icon_float_layer;

    private FloatingLayer(Context context) {
        this.mContext = context;

        initView();
        initWindowManager();
        initLayoutParams();
//        initDrag();
    }

    public static FloatingLayer getInstance(Context context) {
        if (null == sFloatingLayer) {
            synchronized (FloatingLayer.class) {
                if (null == sFloatingLayer) {
                    sFloatingLayer = new FloatingLayer(context);
                }
            }
        }

        return sFloatingLayer;
    }

    private void initView() {
        mPopView = LayoutInflater.from(mContext).inflate(R.layout.layout_floatinglayer, null);
        icon_float_layer = mPopView.findViewById(R.id.icon_float_layer);

    }

    private void initWindowManager() {
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    private void initLayoutParams() {

        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mLayoutParams.format = PixelFormat.TRANSLUCENT;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        mLayoutParams.gravity = Gravity.BOTTOM | Gravity.END;
        mLayoutParams.x = mContext.getResources().getDisplayMetrics().widthPixels * 3 / 100;
        mLayoutParams.y = mContext.getResources().getDisplayMetrics().heightPixels * 19 / 250;
        mWindowManager.addView(mPopView, mLayoutParams);
        MyAnimationDrawable.animateRawManuallyFromXML(R.drawable.floating_layer, icon_float_layer, () -> {
        },null);

    }

    private void initDrag() {
        mPopView.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    mPrevX = motionEvent.getRawX();
                    mPrevY = motionEvent.getRawY();
                    mStartClickTime = BigDecimal.valueOf(System.currentTimeMillis());
                    break;
                case MotionEvent.ACTION_MOVE:
                    float deltaX = motionEvent.getRawX() - mPrevX;
                    float deltaY = motionEvent.getRawY() - mPrevY;
                    mLayoutParams.x += deltaX;
                    mLayoutParams.y += deltaY;
                    mPrevX = motionEvent.getRawX();
                    mPrevY = motionEvent.getRawY();

                    if (mLayoutParams.x < 0) mLayoutParams.x = 0;
                    if (mLayoutParams.x > mWidth - mPopView.getWidth())
                        mLayoutParams.x = mWidth - mPopView.getWidth();
                    if (mLayoutParams.y < 0) mLayoutParams.y = 0;
                    if (mLayoutParams.y > mHeight - mPopView.getHeight() * 2)
                        mLayoutParams.y = mHeight - mPopView.getHeight() * 2;

                    try {
                        mWindowManager.updateViewLayout(mPopView, mLayoutParams);
                    } catch (Exception e) {
                        Log.d(TAG, e.toString());
                    }

                    if (deltaX > 10 | deltaY > 10) isMove = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:

                    BigDecimal now = BigDecimal.valueOf(System.currentTimeMillis());
                    if (!isMove && (Math.abs(now.subtract(mStartClickTime).floatValue()) < 500)) {
                        if (null != mListener) {
                            mListener.onClick();
                            return false;
                        }
                    }

                    mAnimationTimer = new Timer();
                    mAnimationTask = new AnimationTimerTask();
                    mAnimationTimer.schedule(mAnimationTask, 0, mAnimatonPeriodTime);

                    break;
            }

            return false;
        });
    }

    public void show(Activity activity) {

        if (!ISSHOW) {
            mGetTokenRunnable = new GetTokenRunnable(activity);
            mHander.postDelayed(mGetTokenRunnable, 500);
        }
    }

    public void close() {
        try {
            if (ISSHOW) {
                mWindowManager.removeViewImmediate(mPopView);
                ISSHOW = false;
                if (null != mListener) {
                    mListener.onClose();
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }

    public FloatingLayer setListener(FloatingLayerListener listener) {
        if (null != sFloatingLayer) {
            this.mListener = listener;
        }
        return sFloatingLayer;
    }

    public interface FloatingLayerListener {
        void onClick();

        void onShow();

        void onClose();
    }

    class AnimationTimerTask extends TimerTask {

        int mStepX;
        int mDestX;

        public AnimationTimerTask() {
            if (mLayoutParams.x > mWidth / 2) {
                mDestX = mWidth - mPopView.getWidth();
                mStepX = (mWidth - mLayoutParams.x) / 10;
            } else {
                mDestX = 0;
                mStepX = -((mLayoutParams.x) / 10);
            }

        }

        @Override
        public void run() {

            if (Math.abs(mDestX - mLayoutParams.x) <= Math.abs(mStepX)) {
                mLayoutParams.x = mDestX;
            } else {
                mLayoutParams.x += mStepX;
            }

            try {
                mHander.post(() -> mWindowManager.updateViewLayout(mPopView, mLayoutParams));
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }


            if (mLayoutParams.x == mDestX) {
                mAnimationTask.cancel();
                mAnimationTimer.cancel();
            }


        }
    }

    class GetTokenRunnable implements Runnable {

        int count = 0;
        private Activity mActivity;

        public GetTokenRunnable(Activity activity) {
            this.mActivity = activity;
        }

        @Override
        public void run() {

            if (null == mActivity)
                return;
            IBinder token = null;
            try {
                token = mActivity.getWindow().getDecorView().getWindowToken();
            } catch (Exception e) {
            }

            if (null != token) {

                try {
                    mLayoutParams.token = token;
                    mWindowManager.addView(mPopView,
                            mLayoutParams);
                    mActivity = null;
                    return;
                } catch (Exception e) {
                }
            }
            count++;
            mLayoutParams.token = null;
            if (count < 10 && null != mLayoutParams) {
                mHander.postDelayed(mGetTokenRunnable, 500);
            }

        }
    }
}
