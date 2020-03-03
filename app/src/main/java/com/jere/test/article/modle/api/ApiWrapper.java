package com.jere.test.article.modle.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author jere
 */
public class ApiWrapper {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.wanandroid.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            // customize retrofit network timeouts, default timeouts is ten seconds.
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
