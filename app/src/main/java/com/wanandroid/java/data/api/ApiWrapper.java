package com.wanandroid.java.data.api;

import com.wanandroid.java.data.api.cookie.PersistentCookieJar;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author jere
 */
public class ApiWrapper {
    public static final String BASE_HOST = "https://www.wanandroid.com";

    public static ApiService getService() {
        return RetrofitHolder.getRetrofitInstance().create(ApiService.class);
    }


    public static class RetrofitHolder {
        public static Retrofit getRetrofitInstance() {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .cookieJar(new PersistentCookieJar())
                    .build();

            return new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_HOST)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }


}
