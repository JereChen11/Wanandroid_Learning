package com.jere.test.article.modle.api;

import com.jere.test.article.modle.beanfiles.homearticle.HomeBannerListBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author jere
 */
public interface ApiService {

    @GET("/banner/json")
    Call<ResponseBody> getHomeBannerList();

    /**
     * just for test Retrofit + RxJava2
     * @return io.reactivex.Observable
     */
    @GET("/banner/json")
    Observable<HomeBannerListBean> getRxJavaHomeBannerList();

    @GET("/article/list/{pageNumber}/json")
    Call<ResponseBody> getHomeArticleList(@Path("pageNumber") int pageNumber);

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

    @GET("/tree/json")
    Call<ResponseBody> getKnowledgeSystem();

    @GET("/article/list/0/json")
    Call<ResponseBody> getKnowledgeSystemArticleList(@Query("cid") int cid);

}
