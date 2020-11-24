package com.wanandroid.java.article.modle.api;

import android.preference.PreferenceManager;

import com.wanandroid.java.MyApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author jere
 */
public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> cookies = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance()).getStringSet("cookie", null);
        if (cookies != null) {
            for (String cookie: cookies) {
                builder.addHeader("Cookie", cookie);
            }
        }

        return chain.proceed(builder.build());
    }
}
