package com.wanandroid.java.data.repository;

import android.text.TextUtils;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.BaseResponse;
import com.wanandroid.java.data.bean.UserInfo;
import com.wanandroid.java.data.bean.local.LoginRegisterResult;

import java.util.HashMap;

import retrofit2.Callback;

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

    private Callback<BaseResponse<UserInfo>> loginRegisterCallback(LoginRegisterListener listener) {
        return new MyCallback<BaseResponse<UserInfo>>() {
            @Override
            public void getSuccessful(BaseResponse<UserInfo> baseResponse) {
                listener.onResult(new LoginRegisterResult(true, "successful"));
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.onResult(new LoginRegisterResult(true, "successful"));
            }
        };
    }

    public void register(String userName, String password, String rePassword, LoginRegisterListener listener) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
            listener.onResult(new LoginRegisterResult(false, "请输入完整格式"));
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);
        map.put("repassword", rePassword);
        ApiWrapper.getService().register(map).enqueue(loginRegisterCallback(listener));
    }

    public void login(String userName, String password, LoginRegisterListener listener) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            listener.onResult(new LoginRegisterResult(false, "请输入完整格式"));
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);
        ApiWrapper.getService().login(map).enqueue(loginRegisterCallback(listener));
    }

    public interface LoginRegisterListener {
        /**
         * 监听是否注册成功
         *
         * @param result 注册成功，返回true；反之，返回false
         */
        void onResult(LoginRegisterResult result);
    }

}
