package com.wanandroid.java.data.api;

import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.BaseResponse;
import com.wanandroid.java.data.bean.HomeBanner;
import com.wanandroid.java.data.bean.ProjectType;
import com.wanandroid.java.data.bean.SystemCategory;
import com.wanandroid.java.data.bean.UserInfo;
import com.wanandroid.java.data.bean.WeChatBlogger;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<BaseResponse<List<HomeBanner>>> getHomeBannerList();

    /**
     * just for test Retrofit + RxJava2
     *
     * @return io.reactivex.Observable
     */
    @GET("/banner/json")
    Observable<BaseResponse<List<HomeBanner>>> getRxJavaHomeBannerList();

    /**
     * 获取主页文章列表
     *
     * @param pageNumber
     * @return
     */
    @GET("/article/list/{pageNumber}/json")
    Call<BaseResponse<ArticleData>> getHomeArticleList(@Path("pageNumber") int pageNumber);

    /**
     * 获取项目分类
     *
     * @return
     */
    @GET("/project/tree/json")
    Call<BaseResponse<List<ProjectType>>> getProjectTreeItems();

    /**
     * 根据项目ID，获取项目列表
     *
     * @param pageNumber
     * @param cid
     * @return
     */
    @GET("/project/list/{pageNumber}/json?")
    Call<BaseResponse<ArticleData>> getProjectItemList(@Path("pageNumber") int pageNumber,
                                                       @Query("cid") int cid);

    /**
     * 获取微信公众号博主信息
     *
     * @return
     */
    @GET("/wxarticle/chapters/json")
    Call<BaseResponse<List<WeChatBlogger>>> getWeChatBloggerList();

    /**
     * 获取微信公众号文章列表
     *
     * @param authorId
     * @param pageNumber
     * @return
     */
    @GET("/wxarticle/list/{authorId}/{pageNumber}/json")
    Call<BaseResponse<ArticleData>> getWeChatArticleList(@Path("authorId") int authorId,
                                                         @Path("pageNumber") int pageNumber);

    /**
     * 注册
     *
     * @param registerInfoMap
     * @return
     */
    @FormUrlEncoded
    @POST("/user/register")
    Call<BaseResponse<UserInfo>> register(@FieldMap Map<String, String> registerInfoMap);

    /**
     * 登入
     *
     * @param loginInfoMap
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    Call<BaseResponse<UserInfo>> login(@FieldMap Map<String, String> loginInfoMap);

    /**
     * 获取知识体系
     *
     * @return
     */
    @GET("/tree/json")
    Call<BaseResponse<List<SystemCategory>>> getKnowledgeSystemCategory();

    /**
     * 获取知识体系文章列表
     *
     * @param cid
     * @return
     */
    @GET("/article/list/{pageNumber}/json")
    Call<BaseResponse<ArticleData>> getKnowledgeSystemArticleList(@Path("pageNumber") int pageNumber,
                                                                  @Query("cid") int cid);

    /**
     * 获取收藏的文章列表
     *
     * @return
     */
    @GET("/lg/collect/list/{pageNumber}/json")
    Call<BaseResponse<ArticleData>> getCollectionArticleList(@Path("pageNumber") int pageNumber);

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
