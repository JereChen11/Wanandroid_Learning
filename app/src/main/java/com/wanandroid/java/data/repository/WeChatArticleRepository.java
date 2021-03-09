package com.wanandroid.java.data.repository;

import com.google.gson.Gson;
import com.wanandroid.java.data.api.AbstractRetrofitCallback;
import com.wanandroid.java.data.api.ApiService;
import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ArticleListBean;
import com.wanandroid.java.data.bean.WeChatArticleBloggerList;

/**
 * @author jere
 */
public class WeChatArticleRepository {
    private static final String TAG = "WeChatArticleRepository";
    private volatile static WeChatArticleRepository instance;

    public static WeChatArticleRepository newInstance() {
        if (instance == null) {
            synchronized (WeChatArticleRepository.class) {
                if (instance == null) {
                    instance = new WeChatArticleRepository();
                }
            }
        }
        return instance;
    }

    public void getWeChatArticleBloggerList(final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getWeChatOfficialAccountBloggerList().enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                WeChatArticleBloggerList bloggerList = gson.fromJson(responseBody, WeChatArticleBloggerList.class);
                listener.getDataSuccess(bloggerList);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }


    public void getWeChatArticleList(int authorId, int pageNumber, final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getWeChatArticleList(authorId, pageNumber).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                ArticleListBean articleListBean = gson.fromJson(responseBody, ArticleListBean.class);
                listener.getDataSuccess(articleListBean);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }


}
