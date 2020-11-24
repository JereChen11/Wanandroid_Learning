package com.wanandroid.java.article.modle.api;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wanandroid.java.MyApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author jere
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    private static final String SET_COOKIE_KEY = "Set-Cookie";

    public ReceivedCookiesInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers(SET_COOKIE_KEY).isEmpty()) {
            HashSet<String> cookies = new HashSet<>(originalResponse.headers(SET_COOKIE_KEY));

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
            SharedPreferences.Editor editor = sp.edit();
            editor.putStringSet("cookie", cookies);
            editor.apply();
        }
        return originalResponse;
    }


}
