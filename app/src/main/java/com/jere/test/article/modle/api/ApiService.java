package com.jere.test.article.modle.api;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author jere
 */
public interface ApiService {
    @GET("/article/list/0/json")
    Call<ResponseBody> getHomeArticleList();

    @PUT("/mock/36/reagent/material/detection")
    Call<ResponseBody> updateReagentMaterialAmount(@Body RequestBody requestBody);

    @GET("/project/tree/json")
    Call<ResponseBody> getProjectTreeItems();

    @GET("/project/list/{pageNumber}/json?")
    Call<ResponseBody> getProjectItemList(@Path("pageNumber") int pageNumber,
                                          @Query("cid") int cid);

    @GET("/wxarticle/chapters/json")
    Call<ResponseBody> getWeChatOfficialAccountBloggerList();

    @GET("/wxarticle/list/{authorId}/{pageNumber}/json")
    Call<ResponseBody> getWeChatArticleList(@Path("authorId") int authorId,
                                            @Path("pageNumber") int pageNumber);

    @POST("/user/register")
    Call<ResponseBody> register(@QueryMap Map<String, String> registerInfoMap);

    @POST("/user/login")
    Call<ResponseBody> login(@QueryMap Map<String, String> loginInfoMap);

    @GET("/banner/json")
    Call<ResponseBody> getHomeBannerList();

}
