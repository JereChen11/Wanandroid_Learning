package com.wanandroid.java.ui.login;

import android.text.TextUtils;

import com.wanandroid.java.data.api.ApiService;
import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.LoginInfo;
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

    private Callback<LoginInfo> loginRegisterCallback(LoginRegisterListener listener) {
        return new MyCallback<LoginInfo>() {
            @Override
            public void getSuccessful(LoginInfo data) {
                if (data.getErrorCode() == 0) {
                    listener.onResult(new LoginRegisterResult(true, "successful"));
                } else {
                    listener.onResult(new LoginRegisterResult(true, data.getErrorMsg()));
                }
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.onResult(new LoginRegisterResult(true, failedMsg));
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
        map.put("rePassword", rePassword);
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
