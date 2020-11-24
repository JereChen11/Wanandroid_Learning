package com.wanandroid.java.article.modle.api;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author jere
 */
public abstract class AbstractRetrofitCallback implements Callback<ResponseBody> {
    private static final int REQUEST_SUCCESSFUL = 200;

    /**
     * 请求数据成功
     * @param responseBody
     */
    public abstract void getSuccessful(String responseBody);

    /**
     * 请求数据失败
     * @param failedMsg
     */
    public abstract void getFailed(String failedMsg);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.code() == REQUEST_SUCCESSFUL) {
            try {
                String requestBody = response.body().string();
                getSuccessful(requestBody);
            } catch (IOException e) {
                e.printStackTrace();
                getFailed("parse responseBody error: " + e.getMessage());
            }
        } else {
            getFailed("error, response.code() = " + response.code());
        }

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        getFailed(t.getMessage());
    }
}
