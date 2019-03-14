package com.a16lao.wyh.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.ui.main.activity.LoginActivity;
import com.a16lao.wyh.utils.StatusBarUtils;
import com.a16lao.wyh.utils.storage.SPUtils;
import com.a16lao.wyh.widget.PromptView;
import com.a16lao.wyh.widget.loading.BeforeLoadingDialog;
import com.a16lao.wyh.widget.loading.LoadingDialog;

import rx.subscriptions.CompositeSubscription;

import static android.support.v7.app.AppCompatDelegate.setDefaultNightMode;

/**
 * date:   2018/5/15 0015 下午 1:45
 * author: caoyan
 * description:
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    public final String TAG = "=======" + getClass().getSimpleName() + "======";
    public Activity mActivity;
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();
    protected boolean isTranslucent;
    LinearLayout mRootBaseView;//根布局
    private LoadingDialog mLoadingDialog;
    private boolean isNeedLoadingDialog = true;
    private boolean isNeedAutoBackView = true;
    private boolean isNeedSystemResConfig = false;
    protected PromptView promptView_view;

    public boolean isTranslucent() {
        return isTranslucent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

//        mSubscriptions.add(RxBus.getInstance().toObservableSticky(ThemeBean.class).subscribe(themeBean -> {
//            LogUtils.w("===============", themeBean.isFlag() + "");
//            if (themeBean.isFlag()) {
//                getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                SPUtils.setAppFlag(SPUtils.IS_NIGHT, false);
//            } else {
//                getDelegate().setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                SPUtils.setAppFlag(SPUtils.IS_NIGHT, true);
//            }
//            RxBus.getInstance().removeStickyEvent(ThemeBean.class);
//
//            recreate();
//        }));


        super.onCreate(savedInstanceState);

        setContentView(setLayout());

    }


    @Override
    public void setContentView(int layoutResID) {
//        super.setContentView(layoutResID);

        if (!isTranslucent()) {
            StatusBarUtils.setColor(this, getResources().getColor(R.color.colorPrimary));
        } else {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                StatusBarUtils.setTranslucentForCoordinatorLayout(this);
            } else {
                StatusBarUtils.setTranslucentForCoordinatorLayout(this, Color.parseColor("#30000000"));
            }

        }

        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        //设置填充activity_base布局
        super.setContentView(view);


        //加载子类Activity的布局
        initDefaultView(layoutResID);

//        initTheme();
//        SPUtils.setAppInt(SPUtils.BRIGHTNESS, BrightnessUtils.getSystemBrightness());
//        if (BrightnessUtils.isAutoBrightness()) {
//            SPUtils.setAppFlag(SPUtils.AUTO_BRIGHTNESS, true);
//        }
//        else {
//            SPUtils.setAppFlag(SPUtils.AUTO_BRIGHTNESS, false);
//        }
        mActivity = this;
        mLoadingDialog = new BeforeLoadingDialog(this);
        tryToAddBackClick();
        getFromIntentData();
        initView();
        initData();
        setListener();


    }

    private void initDefaultView(int layoutResId) {
        promptView_view = findViewById(R.id.promptView_view);
        FrameLayout container = findViewById(R.id.fl_activity_child_container);
        View childView = LayoutInflater.from(this).inflate(layoutResId, null);
        container.addView(childView, 0);
    }



//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (SPUtils.getAppFlag(SPUtils.AUTO_BRIGHTNESS)) {
//            BrightnessUtils.closeAutoBrightness(this);
//        }
//        BrightnessUtils.saveBrightness(18);
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (SPUtils.getAppFlag(SPUtils.AUTO_BRIGHTNESS)) {
//            BrightnessUtils.openAutoBrightness(this);
//        }
//        BrightnessUtils.saveBrightness(BrightnessUtils.getSystemBrightness());
//    }

    private void initTheme() {
        if (isNightMode()) {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    public boolean isNightMode() {

        return SPUtils.getAppFlag(SPUtils.IS_NIGHT);
    }


    protected abstract int setLayout();

    protected abstract void getFromIntentData();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();


    @Override
    public LoadingDialog getLoadingDialog() {
        return isNeedLoadingDialog ? mLoadingDialog : null;
    }


    public abstract BasePresenter getPresenter();

    /**
     * 设置toolbar中间标题
     */
    protected void setTitleMiddleText(int visibility, String text) {
        TextView text_toolbar_middle = findViewById(R.id.text_toolbar_middle);
        if (text_toolbar_middle != null) {
            text_toolbar_middle.setVisibility(visibility);
            text_toolbar_middle.setText(text);
        }
    }

    protected void setTitleMiddleText(String text) {
        setTitleMiddleText(View.VISIBLE, text);
    }


    /**
     * 设置toolbar右边文字
     */
    protected void setTitleRightText(String text, View.OnClickListener onClickListener) {
        TextView text_toolbar_right = findViewById(R.id.text_toolbar_right);
        if (text_toolbar_right != null) {
            text_toolbar_right.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(text)) {
                text_toolbar_right.setText(text);
            }
            text_toolbar_right.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置toolbar右边图标
     */
    protected void setTitleRightNavigator(int resId, View.OnClickListener onClickListener) {
        ImageView image_toolbar_right = findViewById(R.id.image_toolbar_right);
        if (image_toolbar_right != null) {
            image_toolbar_right.setVisibility(View.VISIBLE);
            image_toolbar_right.setImageResource(resId);
            image_toolbar_right.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置toolbar左边图标
     */

    protected void setTitleLeftNavigator(int visibility, int resId, View.OnClickListener onClickListener) {
        ImageView image_toolbar_left = findViewById(R.id.image_toolbar_back);
        mActivity.findViewById(R.id.text_toolbar_left).setVisibility(View.GONE);
        if (image_toolbar_left != null) {
            if (visibility == View.VISIBLE) {
                image_toolbar_left.setVisibility(View.VISIBLE);
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


    /**
     * 设置toolbar左边文字
     */

    protected void setTitleLeftText(int visibility, String text, View.OnClickListener onClickListener) {
        TextView text_toolbar_left = mActivity.findViewById(R.id.text_toolbar_left);
        mActivity.findViewById(R.id.image_toolbar_back).setVisibility(View.GONE);
        if (text_toolbar_left != null) {
            if (visibility == View.VISIBLE) {
                if (!TextUtils.isEmpty(text)) {
                    text_toolbar_left.setVisibility(visibility);
                    text_toolbar_left.setText(text);
                    text_toolbar_left.setOnClickListener(onClickListener);
                }
            } else {
                text_toolbar_left.setVisibility(visibility);
            }

        }
    }

    protected void setTitleLeftText(String text, View.OnClickListener onClickListener) {
        setTitleLeftText(View.VISIBLE, text, onClickListener);
    }

    protected void setTitleLeftText(int visibility) {
        setTitleLeftText(View.GONE, "", null);
    }

    protected void setTitleLeftText(String text) {
        setTitleLeftText(View.VISIBLE, text, null);
    }

    //设置是否需要默认返回
    public void setNeedAutoBackView(boolean isNeedAutoBackView) {
        this.isNeedAutoBackView = isNeedAutoBackView;
    }

    //默认返回键
    protected void tryToAddBackClick() {
        View backView = findViewById(R.id.image_toolbar_back);
        if (backView != null) {
            if (isNeedAutoBackView) {
                backView.setOnClickListener(v -> finish());
            } else {
                backView.setVisibility(View.GONE);
            }
        }

    }

    protected void setNightMode() {
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF;
        getWindow().setAttributes(attrs);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().dispatchView();
        }
        mSubscriptions.unsubscribe();
    }


    @Override
    public Resources getResources() {
        if (isNeedSystemResConfig) {
            return super.getResources();
        } else {
            Resources res = super.getResources();
            Configuration config = new Configuration();
            config.setToDefaults();
            res.updateConfiguration(config, res.getDisplayMetrics());
            return res;
        }

    }

    /**
     * @param clazz   目标类
     * @param bundle  参数
     * @param is_need 是否需要判断登录状态  true需要  false 不需要
     */
    protected void toOtherActivity(Class clazz, Bundle bundle, boolean is_need) {
        if (!is_need) {
            Intent intent = new Intent(mActivity, clazz);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            mActivity.startActivity(intent);
        } else {
            if (isLogin()) {
                Intent intent = new Intent(mActivity, clazz);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                mActivity.startActivity(intent);
            }
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
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
            if (isLogin()) {
                Intent intent = new Intent(mActivity, clazz);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                mActivity.startActivityForResult(intent, code);
            }
        }

    }

    //toWebActivity
    protected void toWebActivity(String webUrl, String title) {
        Intent intent = new Intent(this, WebBrowserActivity.class);
        intent.putExtra(WebBrowserActivity.WEB_URL, webUrl);
        intent.putExtra(WebBrowserActivity.WEB_TITLE, title);
        startActivity(intent);
    }

    protected boolean isLogin() {
        boolean login = SPUtils.getAppFlag(SPUtils.IS_LOGIN);
        if (!login) {
            toLoginActivity();
        }
        return login;
    }

    protected void toLoginActivity() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
    }

    protected void needLoadingDialog(boolean isNeed) {
        this.isNeedLoadingDialog = isNeed;
    }

    protected void needSystemResConfig(boolean isNeed) {
        this.isNeedSystemResConfig = isNeed;
    }

}
