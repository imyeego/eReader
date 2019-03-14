package com.a16lao.wyh.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.a16lao.wyh.utils.LogUtils;


/**
 * date:   2018/5/16 0016 下午 3:27
 * author: caoyan
 * description:
 */

public abstract class LazyFragment extends BaseFragment {
    private boolean hasCreateView;
    private boolean isFragmentVisible;
    private final String TAG = "======" + getClass().getSimpleName() + "======";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.e(TAG, getUserVisibleHint() + "getUserVisibleHint()");
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mRootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    protected void onFragmentVisibleChange(boolean isVisible) { }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasCreateView = false;
        isFragmentVisible = false;
    }
}
