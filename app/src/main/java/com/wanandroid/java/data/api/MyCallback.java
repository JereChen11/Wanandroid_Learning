package com.wanandroid.java.data.api;

import com.wanandroid.java.data.bean.BaseResponse;

import org.jetbrains.annotations.NotNull;

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
    public void onResponse(@NotNull Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            BaseResponse<T> baseResponse = (BaseResponse<T>) response.body();
            if (baseResponse.getErrorCode() == 0) {
                getSuccessful(response.body());
            } else {
                getFailed(baseResponse.getErrorMsg());
            }
        } else {
            getFailed(response.code() + " " + response.message());
        }
    }

    @Override
    public void onFailure(@NotNull Call<T> call, Throwable t) {
        getFailed(t.getMessage());
    }

}
