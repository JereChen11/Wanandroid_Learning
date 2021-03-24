package com.wanandroid.java.util;


import android.content.SharedPreferences;

import com.wanandroid.java.MyApplication;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author jere
 */
public class SpSettings {
    private volatile static SpSettings instance;
    private final SharedPreferences mSp;
    private static final String SETTINGS_SP_KEY = "SETTINGS_SP";
    private static final String USER_NAME_KEY = "USER_NAME";
    private static final String AVATAR_URL_KEY = "AVATAR_URL";
    private static final String IS_LOGIN_KEY = "IS_LOGIN";

    private SpSettings() {
        mSp = MyApplication.getInstance().getApplicationContext().getSharedPreferences(SETTINGS_SP_KEY, MODE_PRIVATE);
    }

    public static SpSettings getInstance() {
        if (instance == null) {
            synchronized (SpSettings.class) {
                if (instance == null) {
                    instance = new SpSettings();
                }
            }
        }
        return instance;
    }

    public String getUserName() {
        return mSp.getString(USER_NAME_KEY, "JereChen");
    }

    public void setUserName(String nickname) {
        mSp.edit().putString(USER_NAME_KEY, nickname).apply();
    }

    public String getAvatarUrl() {
        return mSp.getString(AVATAR_URL_KEY, "");
    }

    public void setAvatarUrl(String avatarUrl) {
        mSp.edit().putString(AVATAR_URL_KEY, avatarUrl).apply();
    }

    public boolean getIsLogin() {
        return mSp.getBoolean(IS_LOGIN_KEY, false);
    }

    public void setIsLogin(boolean isLogin) {
        mSp.edit().putBoolean(IS_LOGIN_KEY, isLogin).apply();
    }
}
