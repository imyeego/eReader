package com.a16lao.wyh.ui.main.pv;

import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.BaseView;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.config.Protocol;
import com.a16lao.wyh.net.callback.HttpCallBack;
import com.a16lao.wyh.utils.LogUtils;
import com.a16lao.wyh.utils.ToastUtils;

import java.util.WeakHashMap;

import retrofit2.Response;

/**
 * date:   2018/5/15 0015 下午 2:35
 * author: caoyan
 * description:
 */

public class MainPresenter extends BasePresenter<MainPresenter.MainView> {

    public MainPresenter(MainView mainView) {
        super(mainView);
    }


    public void showDialog() {
        mView.getLoadingDialog().show();
        WeakHashMap<String, Object> map = new WeakHashMap<>();
        map.put("userId", "YH00000002");
        addJsonRequest(Protocol.TEST,map , new HttpCallBack() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("sfsgrfg",response.body().toString());
                ToastUtils.showToast(Latte.getApplication(), response.body());
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                LogUtils.e("dhfjugyuj",errorNo+"========errorNo======="+strMsg);
                ToastUtils.showToast(Latte.getApplication(), strMsg);

            }
        });
    }

    public interface MainView extends BaseView {


    }
}


