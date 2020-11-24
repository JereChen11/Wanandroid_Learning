package com.wanandroid.java.login;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.wanandroid.java.article.modle.api.AbstractRetrofitCallback;
import com.wanandroid.java.article.modle.api.ApiService;
import com.wanandroid.java.article.modle.api.ApiWrapper;
import com.wanandroid.java.login.model.LoginInfo;

import java.util.HashMap;

/**
 * @author jere
 */
public class RegisterLoginRepository {
    private static final String TAG = "RegisterLoginRepository";
    private volatile static RegisterLoginRepository instance;

    public static RegisterLoginRepository getInstance() {
        if (instance == null) {
            synchronized (RegisterLoginRepository.class) {
                if (instance == null) {
                    instance = new RegisterLoginRepository();
                }
            }
        }
        return instance;
    }

    public void register(String userName, String password, String rePassword, final RegisterListener listener) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
            listener.register(false);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);
        map.put("rePassword", rePassword);
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.register(map).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Log.e(TAG, "getSuccessful: " + responseBody);
                listener.register(true);
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
                listener.register(false);
            }
        });
    }

    public void login(String userName, String password, final LoginListener listener) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            listener.login(false);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.login(map).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Log.e(TAG, "getSuccessful: " + responseBody);
                Gson gson = new Gson();
                LoginInfo loginInfo = gson.fromJson(responseBody, LoginInfo.class);
                if (loginInfo.getErrorCode() == 0) {
                    listener.login(true);
                } else {
                    listener.login(false);
                }
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
                listener.login(false);
            }
        });
    }

    public interface LoginListener {
        /**
         * 监听是否登入成功
         * @param isLoginSuccessful 登入成功，返回true；反之，返回false
         */
        void login(boolean isLoginSuccessful);
    }

    public interface RegisterListener {
        /**
         * 监听是否注册成功
         * @param isRegisterSuccessful 注册成功，返回true；反之，返回false
         */
        void register(boolean isRegisterSuccessful);
    }

}
