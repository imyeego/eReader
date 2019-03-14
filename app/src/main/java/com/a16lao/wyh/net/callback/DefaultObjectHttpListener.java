package com.a16lao.wyh.net.callback;

import com.a16lao.wyh.bean.BaseBean;
import com.a16lao.wyh.utils.LogUtils;
import com.a16lao.wyh.widget.PromptView;
import com.a16lao.wyh.widget.loading.LoadingDialog;
import com.alibaba.fastjson.JSON;


/**
 * date:   2018/5/15 0015 下午 4:38
 * author: caoyan
 * description:
 */

public abstract class DefaultObjectHttpListener<T> extends DefaultHttpListener<T> {
    private final String TAG = "======" + getClass().getSimpleName() + "======";
    private Class<T> clazz;

    public DefaultObjectHttpListener() {
        super();
        init();
    }


    public DefaultObjectHttpListener(PromptView errorView) {
        super(errorView);
        init();
    }

    public DefaultObjectHttpListener(PromptView errorView, LoadingDialog loadingDialog) {
        super(errorView, loadingDialog);
        init();
    }

    protected void init() {
        clazz = getParsedClass();
    }


    @Override
    protected void onSuccessParsedFirst(int code, String data) {
        LogUtils.e(TAG, "" + data);
        if (clazz == BaseBean.class) {
            try {
                onSuccessParsed(null);
            } catch (Exception e) {
                LogUtils.e(TAG, "解析的是BaseBean");
            }

        } else {
            T tt = JSON.parseObject(data, clazz);
            if (tt != null) {
                onSuccessParsed(tt);
            }
        }
    }

    public abstract void onSuccessParsed(T result);

}
