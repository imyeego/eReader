package com.a16lao.wyh.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.ui.main.activity.LoginActivity;
import com.a16lao.wyh.utils.storage.SPUtils;
import com.a16lao.wyh.widget.loading.BeforeLoadingDialog;
import com.a16lao.wyh.widget.loading.LoadingDialog;

import rx.subscriptions.CompositeSubscription;

/**
 * date:   2018/5/16 0016 上午 11:11
 * author: caoyan
 * description:
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    public final String TAG = "======" + getClass().getSimpleName() + "======";
    protected Activity mActivity;
    protected View mRootView;
    private LoadingDialog mLoadingDialog;
    private boolean isNeedAutoBackView = false;
    private boolean isNeedLoadingDialog = true;
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayout() instanceof Integer) {
            mRootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            mRootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() type must be int or view");
        }
        onBindView(savedInstanceState, mRootView);
        return mRootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        mLoadingDialog = new BeforeLoadingDialog(mActivity);
        tryToAddBackClick();
        initData();
    }

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    protected abstract void initData();

    @Override
    public LoadingDialog getLoadingDialog() {
        return isNeedLoadingDialog ? mLoadingDialog : null;
    }

    protected void needLoadingDialog(boolean isNeed) {
        this.isNeedLoadingDialog = isNeed;
    }

    /**
     * @param clazz   目标类
     * @param bundle  参数
     * @param is_need 是否需要判断登录状态  true需要  false 不需要
     */
    protected void toOtherActivity(Class clazz, Bundle bundle, boolean is_need) {
        Intent intent = new Intent(mActivity, clazz);
        if (!is_need) {
            if (bundle != null) intent.putExtras(bundle);
            mActivity.startActivity(intent);
        } else {
            if (isLogin(is_need)) {
                if (bundle != null) intent.putExtras(bundle);
                mActivity.startActivity(intent);
            }

        }

    }


    /**
     * @param clazz   目标类
     * @param bundle  参数
     * @param is_need 是否需要判断登录状态  true需要  false 不需要
     */
    protected void toOtherBackActivity(Class clazz, Bundle bundle, boolean is_need, int code) {
        if (!is_need) {
            Intent intent = new Intent(mActivity, clazz);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            mActivity.startActivityForResult(intent, code);
        } else {
            if (isLogin(is_need)) {
                Intent intent = new Intent(mActivity, clazz);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                mActivity.startActivityForResult(intent, code);
            }
        }

    }

    protected boolean isLogin(boolean toActivity) {
        boolean login = SPUtils.getAppFlag(SPUtils.IS_LOGIN);
        if (toActivity && !login) {
            toLoginActivity();
        }
        return login;
    }

    protected void toLoginActivity() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
    }

    public abstract BasePresenter getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().dispatchView();
        }
        mSubscriptions.unsubscribe();
    }


    protected void setTitleMiddleText(int visibility, String text) {
        TextView text_toolbar_middle = mRootView.findViewById(R.id.text_toolbar_middle);
        if (text_toolbar_middle != null) {
            text_toolbar_middle.setVisibility(visibility);
            text_toolbar_middle.setText(text);
        }
    }

    protected void setTitleMiddleText(String text) {
        setTitleMiddleText(View.VISIBLE, text);
    }


    //设置toolbar右边的textView
    protected void setTitleRightText(String text, View.OnClickListener onClickListener) {
        TextView text_toolbar_right = mRootView.findViewById(R.id.text_toolbar_right);
        if (text_toolbar_right != null) {
            text_toolbar_right.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(text)) {
                text_toolbar_right.setText(text);
            }
            text_toolbar_right.setOnClickListener(onClickListener);
        }
    }


    protected void setTitleRightNavigator(int resId, View.OnClickListener onClickListener) {
        ImageView image_toolbar_right = mRootView.findViewById(R.id.image_toolbar_right);
        if (image_toolbar_right != null) {
            image_toolbar_right.setVisibility(View.VISIBLE);
            image_toolbar_right.setImageResource(resId);
            image_toolbar_right.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置左边图标
     *
     * @param visibility
     * @param resId
     * @param onClickListener
     */

    protected void setTitleLeftNavigator(int visibility, int resId, View.OnClickListener onClickListener) {
        ImageView image_toolbar_left = mRootView.findViewById(R.id.image_toolbar_back);
        if (image_toolbar_left != null) {
            if (visibility == View.VISIBLE) {
                if (resId > 0) image_toolbar_left.setImageResource(resId);
                image_toolbar_left.setOnClickListener(onClickListener);
            } else {
                image_toolbar_left.setVisibility(visibility);
            }
        }
    }

    protected void setTitleLeftNavigator(int resId, View.OnClickListener onClickListener) {
        setTitleLeftNavigator(View.VISIBLE, resId, onClickListener);
    }

    //默认返回键
    protected void setTitleLeftNavigator(View.OnClickListener onClickListener) {
        setTitleLeftNavigator(-1, onClickListener);
    }


    //设置是否需要默认返回
    public void setNeedAutoBackView(boolean isNeedAutoBackView) {
        this.isNeedAutoBackView = isNeedAutoBackView;
    }

    //默认返回键
    protected void tryToAddBackClick() {
        View backView = mRootView.findViewById(R.id.image_toolbar_back);
        if (backView != null) {
            if (isNeedAutoBackView) {
                backView.setOnClickListener(v -> mActivity.finish());
            } else {
                backView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置toolbar左边的textView
     *
     * @param visibility
     * @param text
     * @param onClickListener
     */

    protected void setTitleLeftText(int visibility, String text, View.OnClickListener onClickListener) {
        TextView text_toolbar_left = mRootView.findViewById(R.id.text_toolbar_left);
        mActivity.findViewById(R.id.image_toolbar_back).setVisibility(View.GONE);
        if (text_toolbar_left != null) {
            text_toolbar_left.setVisibility(visibility);
            if (visibility == View.VISIBLE) {
                if (!TextUtils.isEmpty(text)) {
                    text_toolbar_left.setText(text);
                    text_toolbar_left.setOnClickListener(onClickListener);
                }
            }

        }
    }

    protected void setTitleLeftText(String text, View.OnClickListener onClickListener) {
        setTitleLeftText(View.VISIBLE, text, onClickListener);
    }

    //设置toolbar左边的textView
    protected void setTitleLeftText(int visibility) {
        setTitleLeftText(View.GONE, "", null);
    }

    protected void setTitleLeftText(String text) {
        setTitleLeftText(View.VISIBLE, text, null);
    }

}
