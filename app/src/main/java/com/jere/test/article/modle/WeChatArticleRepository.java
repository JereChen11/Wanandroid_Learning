package com.jere.test.article.modle;

import com.google.gson.Gson;
import com.jere.test.article.modle.api.AbstractRetrofitCallback;
import com.jere.test.article.modle.api.ApiService;
import com.jere.test.article.modle.api.ApiWrapper;
import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.beanfiles.wechatofficialaccount.WeChatArticleBloggerList;
import com.jere.test.article.modle.beanfiles.wechatofficialaccount.WeChatArticleList;

/**
 * @author jere
 */
public class WeChatArticleRepository {
    private static final String TAG = "WeChatArticleRepository";
    private static WeChatArticleRepository instance;

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
                WeChatArticleList weChatArticleList = gson.fromJson(responseBody, WeChatArticleList.class);
                listener.getDataSuccess(weChatArticleList);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }


}
