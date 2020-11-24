package com.wanandroid.java.article.modle.api;

import com.wanandroid.java.article.modle.beanfiles.homearticle.HomeBannerListBean;

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

    /**
     * 获取主页Banner
     *
     * @return
     */
    @GET("/banner/json")
    Call<ResponseBody> getHomeBannerList();

    /**
     * just for test Retrofit + RxJava2
     *
     * @return io.reactivex.Observable
     */
    @GET("/banner/json")
    Observable<HomeBannerListBean> getRxJavaHomeBannerList();

    /**
     * 获取主页文章列表
     *
     * @param pageNumber
     * @return
     */
    @GET("/article/list/{pageNumber}/json")
    Call<ResponseBody> getHomeArticleList(@Path("pageNumber") int pageNumber);

    /**
     * 获取项目分类
     *
     * @return
     */
    @GET("/project/tree/json")
    Call<ResponseBody> getProjectTreeItems();

    /**
     * 根据项目ID，获取项目列表
     *
     * @param pageNumber
     * @param cid
     * @return
     */
    @GET("/project/list/{pageNumber}/json?")
    Call<ResponseBody> getProjectItemList(@Path("pageNumber") int pageNumber,
                                          @Query("cid") int cid);

    /**
     * 获取微信公众号博主信息
     *
     * @return
     */
    @GET("/wxarticle/chapters/json")
    Call<ResponseBody> getWeChatOfficialAccountBloggerList();

    /**
     * 获取微信公众号文章列表
     *
     * @param authorId
     * @param pageNumber
     * @return
     */
    @GET("/wxarticle/list/{authorId}/{pageNumber}/json")
    Call<ResponseBody> getWeChatArticleList(@Path("authorId") int authorId,
                                            @Path("pageNumber") int pageNumber);

    /**
     * 注册
     *
     * @param registerInfoMap
     * @return
     */
    @POST("/user/register")
    Call<ResponseBody> register(@QueryMap Map<String, String> registerInfoMap);

    /**
     * 登入
     *
     * @param loginInfoMap
     * @return
     */
    @POST("/user/login")
    Call<ResponseBody> login(@QueryMap Map<String, String> loginInfoMap);

    /**
     * 获取知识体系
     *
     * @return
     */
    @GET("/tree/json")
    Call<ResponseBody> getKnowledgeSystem();

    /**
     * 获取知识体系文章列表
     *
     * @param cid
     * @return
     */
    @GET("/article/list/{pageNumber}/json")
    Call<ResponseBody> getKnowledgeSystemArticleList(@Path("pageNumber") int pageNumber,
                                                     @Query("cid") int cid);

    /**
     * 获取收藏的文章列表
     *
     * @return
     */
    @GET("/lg/collect/list/{pageNumber}/json")
    Call<ResponseBody>getCollectionArticleList(@Path("pageNumber") int pageNumber);

    /**
     * 收藏文章
     *
     * @param id
     * @return
     */
    @POST("/lg/collect/{id}/json")
    Call<ResponseBody> collectArticle(@Path("id") int id);

    /**
     * 取消文章收藏
     *
     * @param id
     * @return
     */
    @POST("/lg/uncollect_originId/{id}/json")
    Call<ResponseBody> unCollectArticle(@Path("id") int id);

}
