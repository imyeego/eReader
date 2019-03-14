package com.a16lao.wyh.net;




import com.a16lao.wyh.config.ConfigKeys;
import com.a16lao.wyh.config.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author: caoyan
 * time:2018/5/4 12:10
 * description:
 */
public class RestCreator {

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigurations(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 1;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfigurations(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterception() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    if (interceptor != null) {
                        BUILDER.addInterceptor(interceptor);
                    }

                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT =
                addInterception()
                        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                        .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE
                = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

//    private static final class RxRestServiceHolder {
//        private static final RxRestService REST_SERVICE
//                = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
//    }
//
//    public static RxRestService getRxRestService() {
//        return RxRestServiceHolder.REST_SERVICE;
//    }
}
