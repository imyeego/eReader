package com.a16lao.wyh.net.callback;


import android.view.View;

import com.a16lao.wyh.utils.LogUtils;
import com.a16lao.wyh.widget.PromptView;
import com.a16lao.wyh.widget.loading.LoadingDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;


import java.util.List;

/**
 * date:   2018/5/15 0015 下午 4:49
 * author: caoyan
 * description:
 */

public abstract class DefaultListHttpListener<T> extends DefaultHttpListener<T> {
    private final String TAG = "======" + getClass().getSimpleName() + "======";
    private Class<T> clazz;

    public DefaultListHttpListener() {
        super();
        init();
    }

    public DefaultListHttpListener(PromptView errorView) {
        super(errorView);
        init();
    }

    public DefaultListHttpListener(PromptView errorView, LoadingDialog loadingDialog) {
        super(errorView, loadingDialog);
        init();
    }

    private void init() {
        clazz = getParsedClass();

    }

    @Override
    protected void onSuccessParsedFirst(int code, String data) {
        try {
            if (data.charAt(0) == '[' || data.charAt(0) == '{') {
                parseArray(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseArray(String data) {
        List<T> tt = parse(data, clazz);
        if (tt != null && tt.size() > 0) {
            LogUtils.e(TAG, tt.get(0).getClass().getSimpleName());
            onSuccessParsed(tt);
        } else {
            if (getPromptView() != null) {
                showPrompt(PROMPT_NO_DATA);
            }
            onListSizeZero();
        }
    }

    private List<T> parse(String json, Class<T> cls) {
        return JSON.parseArray(json, cls);
    }

    public abstract void onSuccessParsed(List<T> result);

    public abstract void onListSizeZero();
}
