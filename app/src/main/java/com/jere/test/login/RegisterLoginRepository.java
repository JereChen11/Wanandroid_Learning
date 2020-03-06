package com.jere.test.login;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.jere.test.article.modle.api.AbstractRetrofitCallback;
import com.jere.test.article.modle.api.ApiService;
import com.jere.test.article.modle.api.ApiWrapper;
import com.jere.test.login.model.LoginInfo;

import java.util.HashMap;

/**
 * @author jere
 */
public class RegisterLoginRepository {
    private static final String TAG = "RegisterLoginRepository";
    private static RegisterLoginRepository instance;

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

    public void register(String userName, String password, String rePassword) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
            //todo show Toast to reminder user.
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
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
            }
        });
    }

    public void login(String userName, String password, final LoginListener listener) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            //todo show Toast to reminder user.
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
                    listener.isLogin(true);
                } else {
                    listener.isLogin(false);
                }
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
                listener.isLogin(false);
            }
        });
    }

    public interface LoginListener {
        void isLogin(boolean isLogin);
    }

}
