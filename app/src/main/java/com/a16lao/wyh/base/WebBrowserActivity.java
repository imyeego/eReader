package com.a16lao.wyh.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.a16lao.wyh.R;


/**
 * date:   2018/5/17 0017 上午 9:23
 * author: caoyan
 * description:
 */

public class WebBrowserActivity extends BaseActivity {
    public static final String WEB_URL = "webUrl", WEB_TITLE = "title";
    private WebView webView;
    private ProgressBar progressBar;
    private String title, webUrl;

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        //启用JS
        settings.setJavaScriptEnabled(true);
        //开启DOM形式存储
        settings.setDomStorageEnabled(true);
        //任意比例缩放
        settings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                }
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
        });

    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.webView);
        initWebView();
    }

    @Override
    protected void getFromIntentData() {
        Intent intent = getIntent();
        webUrl = intent.getStringExtra(WEB_URL);
        title = intent.getStringExtra(WEB_TITLE);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_web;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
}
