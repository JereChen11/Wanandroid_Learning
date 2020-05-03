package com.jere.test.article.modle.api;

import com.jere.test.util.Settings;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jere
 */
public class ApiWrapper {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.wanandroid.com";

    public static Retrofit getRetrofitInstance() {
//        if (retrofit == null) {
            OkHttpClient okHttpClient;
            if (!Settings.getInstance().getIsLogin()) {
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .addInterceptor(new ReceivedCookiesInterceptor())
                        .build();
            } else {
                okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .addInterceptor(new AddCookiesInterceptor())
                        .build();
            }

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
//        }
        return retrofit;
    }
}
