package com.jere.test.article.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

/**
 * @author jere
 */
public interface ApiService {
    @GET("/article/list/0/json")
    Call<ResponseBody> getHomeArticleList();

    @PUT("/mock/36/reagent/material/detection")
    Call<ResponseBody> updateReagentMaterialAmount(@Body RequestBody requestBody);
}
