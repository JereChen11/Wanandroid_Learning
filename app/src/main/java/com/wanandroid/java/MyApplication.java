package com.wanandroid.java;

import android.app.Application;

/**
 * @author jere
 */
public class MyApplication extends Application {
    public static MyApplication app;

    public static MyApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }


}
