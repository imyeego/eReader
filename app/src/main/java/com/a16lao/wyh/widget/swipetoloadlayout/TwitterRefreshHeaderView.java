package com.a16lao.wyh.widget.swipetoloadlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.a16lao.wyh.R;


public class TwitterRefreshHeaderView extends SwipeRefreshHeaderLayout {

//    private ImageView ivArrow;

    private ImageView ivSuccess;

    private TextView tvRefresh;

    private ProgressBar progressBar;

    private int mHeaderHeight;


    private boolean rotated = false;

    public TwitterRefreshHeaderView(Context context) {
        this(context, null);
    }

    public TwitterRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwitterRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeaderHeight = getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_twitter);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tvRefresh = findViewById(R.id.tvRefresh);

        ivSuccess = findViewById(R.id.ivSuccess);
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    public void onRefresh() {
        ivSuccess.setVisibility(GONE);

        progressBar.setVisibility(VISIBLE);
        tvRefresh.setVisibility(VISIBLE);
        tvRefresh.setText("刷新中...");
        if(mCallBack!=null){
            mCallBack.setOnRefresh();
        }

    }

    @Override
    public void onPrepare() {
        if (mCallBack != null) {
            mCallBack.setOnRefresh();
        }
        Log.d("TwitterRefreshHeader", "onPrepare()");
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (mCallBack != null) {
            mCallBack.setOnCallBack(y, isComplete, automatic);
        }

        if (!isComplete) {

            progressBar.setVisibility(GONE);
            ivSuccess.setVisibility(GONE);

            if (y > mHeaderHeight) {
                tvRefresh.setText("松开刷新");
                if (!rotated) {

                    rotated = true;
                }
            } else if (y < mHeaderHeight) {
                if (rotated) {

                    rotated = false;
                }

                tvRefresh.setText("下拉刷新");
            }
        }
    }

    @Override
    public void onRelease() {
        Log.d("TwitterRefreshHeader", "onRelease()");
    }

    @Override
    public void onComplete() {
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);

        progressBar.setVisibility(GONE);
        tvRefresh.setVisibility(GONE);
        tvRefresh.setText("刷新成功");
        if (mCallBack != null) {
            mCallBack.setOnComplete();
        }
    }

    @Override
    public void onReset() {
        rotated = false;
        ivSuccess.setVisibility(GONE);
        tvRefresh.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
    }


    public interface CallBack {
        void setOnCallBack(int y, boolean isComplete, boolean automatic);

        void setOnRefresh();
        void setOnComplete();
    }

    CallBack mCallBack;

    public void OnCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }


}
