package com.wanandroid.java.data.api;

import com.wanandroid.java.data.bean.ArticleListBean;
import com.wanandroid.java.data.bean.HomeBannerListBean;
import com.wanandroid.java.data.bean.SystemCategoryBean;
import com.wanandroid.java.data.bean.LoginInfo;
import com.wanandroid.java.data.bean.ProjectItemList;
import com.wanandroid.java.data.bean.ProjectTreeItem;
import com.wanandroid.java.data.bean.WeChatArticleBloggerList;

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
    Call<HomeBannerListBean> getHomeBannerList();

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
    Call<ArticleListBean> getHomeArticleList(@Path("pageNumber") int pageNumber);

    /**
     * 获取项目分类
     *
     * @return
     */
    @GET("/project/tree/json")
    Call<ProjectTreeItem> getProjectTreeItems();

    /**
     * 根据项目ID，获取项目列表
     *
     * @param pageNumber
     * @param cid
     * @return
     */
    @GET("/project/list/{pageNumber}/json?")
    Call<ProjectItemList> getProjectItemList(@Path("pageNumber") int pageNumber,
                                             @Query("cid") int cid);

    /**
     * 获取微信公众号博主信息
     *
     * @return
     */
    @GET("/wxarticle/chapters/json")
    Call<WeChatArticleBloggerList> getWeChatOfficialAccountBloggerList();

    /**
     * 获取微信公众号文章列表
     *
     * @param authorId
     * @param pageNumber
     * @return
     */
    @GET("/wxarticle/list/{authorId}/{pageNumber}/json")
    Call<ArticleListBean> getWeChatArticleList(@Path("authorId") int authorId,
                                            @Path("pageNumber") int pageNumber);

    /**
     * 注册
     *
     * @param registerInfoMap
     * @return
     */
    @FormUrlEncoded
    @POST("/user/register")
    Call<LoginInfo> register(@FieldMap Map<String, String> registerInfoMap);

    /**
     * 登入
     *
     * @param loginInfoMap
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    Call<LoginInfo> login(@FieldMap Map<String, String> loginInfoMap);

    /**
     * 获取知识体系
     *
     * @return
     */
    @GET("/tree/json")
    Call<SystemCategoryBean> getKnowledgeSystem();

    /**
     * 获取知识体系文章列表
     *
     * @param cid
     * @return
     */
    @GET("/article/list/{pageNumber}/json")
    Call<ArticleListBean> getKnowledgeSystemArticleList(@Path("pageNumber") int pageNumber,
                                                     @Query("cid") int cid);

    /**
     * 获取收藏的文章列表
     *
     * @return
     */
    @GET("/lg/collect/list/{pageNumber}/json")
    Call<ArticleListBean>getCollectionArticleList(@Path("pageNumber") int pageNumber);

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
