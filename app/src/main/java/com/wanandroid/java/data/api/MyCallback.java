package com.wanandroid.java.data.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author jere
 */
public abstract class MyCallback<T> implements Callback<T> {

    /**
     * 请求数据成功
     *
     * @param
     */
    public abstract void getSuccessful(T data);

    /**
     * 请求数据失败
     *
     * @param failedMsg
     */
    public abstract void getFailed(String failedMsg);


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            getSuccessful(response.body());
        } else {
            getFailed("error, response.code() = " + response.code());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        getFailed(t.getMessage());

    }

}
