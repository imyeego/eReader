package com.a16lao.wyh.ui.main.pv;


import android.text.TextUtils;

import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.BaseView;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.net.callback.HttpCallBack;
import com.a16lao.wyh.ui.main.activity.MainActivity;
import com.a16lao.wyh.utils.StringUtils;
import com.a16lao.wyh.utils.ToastUtils;

import java.util.WeakHashMap;

import retrofit2.Response;

/**
 * date:   2018/5/15 0015 下午 2:35
 * author: caoyan
 * description:
 */

public class LoginPresenter extends BasePresenter<LoginPresenter.LoginView> {

    public LoginPresenter(LoginView loginView) {
        super(loginView);
    }


    public void login(String phone, String password) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || StringUtils.checkPhoneNumber(phone)) {
            return;
        }

        mView.getLoadingDialog().show();
        WeakHashMap<String, Object> map = new WeakHashMap<>();
        map.put("phone", "18234156132");
        map.put("password", "123456");

        addJsonRequest("", map, new HttpCallBack() {
            @Override
            public void onSuccess(Response<String> response) {
                mView.onLoginSuccess();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                mView.onLoginFail();

            }
        });
    }


    public interface LoginView extends BaseView {
        void onLoginSuccess();

        void onLoginFail();

    }
}


