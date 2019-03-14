package com.a16lao.wyh.net.callback;

import android.text.TextUtils;
import android.view.View;


import com.a16lao.wyh.R;
import com.a16lao.wyh.bean.BaseBean;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.config.ResponseType;
import com.a16lao.wyh.utils.JsonUtils;
import com.a16lao.wyh.utils.LogUtils;
import com.a16lao.wyh.utils.ToastUtils;
import com.a16lao.wyh.utils.storage.SPUtils;
import com.a16lao.wyh.widget.PromptView;
import com.a16lao.wyh.widget.loading.LoadingDialog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Response;

/**
 * date:   2018/5/15 0015 下午 4:32
 * author: caoyan
 * description:
 */

public abstract class DefaultHttpListener<T> implements HttpCallBack {
    private final String TAG = "======" + getClass().getSimpleName() + "======";
    private BaseBean baseBean;
    private Class<T> clazz;
    private LoadingDialog mLoadingDialog;
    private PromptView mErrorView;
    public static final int PROMPT_NO_DATA = 1;
    public static final int PROMPT_NET_ERROR = 2;

    public LoadingDialog getLoadingDialog() {
        return mLoadingDialog;
    }

    public PromptView getPromptView() {
        return mErrorView;
    }

    public DefaultHttpListener() {
        init(null, null);
    }

    public DefaultHttpListener(PromptView errorView) {
        init(errorView, null);
    }

    public DefaultHttpListener(PromptView errorView, LoadingDialog loadingDialog) {
        init(errorView, loadingDialog);
    }

    private void init(PromptView errorView, LoadingDialog loadingDialog) {
        this.mErrorView = errorView;
        this.mLoadingDialog = loadingDialog;
        Type modelType = getClass().getGenericSuperclass();
        if ((modelType instanceof ParameterizedType)) {
            Type[] modelTypes = ((ParameterizedType) modelType).getActualTypeArguments();
            clazz = ((Class) modelTypes[0]);
        }
    }

    protected Class<T> getParsedClass() {
        return clazz;
    }


    protected void showPrompt(int flag) {
        if (mErrorView != null) {
            int imgId = 0;
            String errorText = "";
            switch (flag) {
                case PROMPT_NO_DATA:
                    imgId = R.mipmap.ic_launcher;
                    errorText = "空空如也";
                    break;
                case PROMPT_NET_ERROR:
                    imgId = R.mipmap.ic_launcher;
                    errorText = "点击屏幕，重新加载";
                    break;
            }
            mErrorView.setLoadingImg(imgId);
            mErrorView.setLoadingText(errorText);
            mErrorView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccess(Response<String> response) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();

        }
        if (mErrorView != null) mErrorView.setVisibility(View.GONE);
        String json = response.body();
        if (!TextUtils.isEmpty(json)) {
            try {
                baseBean = JsonUtils.getIntance().parseObject(json, BaseBean.class);
            } catch (Exception e) {
                LogUtils.e(TAG, "解析错误");
            }
            if (baseBean != null) {
                int status = baseBean.getStatus();
                if (status == ResponseType.TOKEN_OVERDUE) {
                    LogUtils.e(TAG, "token过期");
                    SPUtils.setAppFlag(SPUtils.IS_LOGIN, false);
                } else if (status == ResponseType.OK) {
                    String results = baseBean.getResults();
                    if (clazz == BaseBean.class || !TextUtils.isEmpty(results)) {
                        onSuccessParsedFirst(status, results);
                    } else {
                        onFailureWithCode(status, response.code(), response.message());
                    }
                } else {
                    onFailureWithCode(status, response.code(), response.message());

                }
            } else {
                onFailureWithCode(ResponseType.RESPONSE_NULL, ResponseType.RESPONSE_NULL, ResponseType.RESPONSE_NULL_STR);

            }

        } else {
            onFailureWithCode(ResponseType.RESPONSE_NULL, ResponseType.RESPONSE_NULL, ResponseType.RESPONSE_NULL_STR);

        }
    }

    @Override
    public void onFailure(int errorNo, String strMsg) {
        ToastUtils.showToast(Latte.getApplication(), "失败");
        onFailureWithCode(0, 0, strMsg);
    }

    private void onFailureWithCode(int code, int errorNo, String strMsg) {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();

        }
        if (mErrorView != null) {
            showPrompt(PROMPT_NET_ERROR);
        }
        LogUtils.e(TAG, "errorNo:" + errorNo + ",strMsg:" + strMsg);
    }

    protected abstract void onSuccessParsedFirst(int code, String data);
}
