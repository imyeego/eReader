package com.a16lao.wyh.ui.main.pv;

import android.text.TextUtils;

import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.BaseView;

import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.net.callback.DefaultHttpListener;
import com.a16lao.wyh.net.callback.HttpCallBack;
import com.a16lao.wyh.utils.StringUtils;
import com.a16lao.wyh.utils.ToastUtils;
import com.a16lao.wyh.widget.PromptView;


import retrofit2.Response;

/**
 * date:   2018/5/25 0025 下午 3:37
 * author: caoyan
 * description:
 */

public class RegisterPresenter extends BasePresenter<RegisterPresenter.RegisterView> {

    public RegisterPresenter(RegisterView registerView) {
        super(registerView);
    }


    public void checkNumber(String phone) {
        if (TextUtils.isEmpty(phone) || StringUtils.checkPhoneNumber(phone)) {
            return;
        }


        addJsonRequest("", null, new HttpCallBack() {
            @Override
            public void onSuccess(Response<String> response) {

            }

            @Override
            public void onFailure(int errorNo, String strMsg) {

            }
        });

    }

    public void register(String verifyNum, String password) {
        if (TextUtils.isEmpty(verifyNum) || TextUtils.isEmpty(password)) {
            return;
        }
        if (verifyNum.length() != 6) {
            ToastUtils.showToast(Latte.getApplication(), "请输入6位验证码");
        }
        if (password.length() < 6) {
            ToastUtils.showToast(Latte.getApplication(), "6-16字符，支持大小写字母、数字 、_");
        }
        if (!StringUtils.validatePassword(password)) {
            ToastUtils.showToast(Latte.getApplication(), "密码设置不符合规定");
        }



    }


    public interface RegisterView extends BaseView {

    }
}
