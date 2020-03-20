package com.jere.test.article.modle;

import com.google.gson.Gson;
import com.jere.test.article.modle.api.AbstractRetrofitCallback;
import com.jere.test.article.modle.api.ApiService;
import com.jere.test.article.modle.api.ApiWrapper;
import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.beanfiles.homearticle.HomeArticleListBean;
import com.jere.test.article.modle.beanfiles.homearticle.HomeBannerListBean;

/**
 * @author jere
 */
public class HomeRepository {
    /**
     * 为了防止出现DCL失效问题，所以用 volatile 关键字来修饰 instance。
     */
    private volatile static HomeRepository instance;

    public static HomeRepository newInstance() {
        if (instance == null) {
            synchronized (HomeRepository.class) {
                if (instance == null) {
                    instance = new HomeRepository();
                }
            }
        }
        return instance;
    }

    public void getHomeBannerList(final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getHomeBannerList().enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                HomeBannerListBean homeBannerListBean = gson.fromJson(responseBody, HomeBannerListBean.class);
                listener.getDataSuccess(homeBannerListBean);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

    public void getHomeArticleList(int pageNumber, final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getHomeArticleList(pageNumber).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                HomeArticleListBean homeArticleListBean = gson.fromJson(responseBody, HomeArticleListBean.class);
                listener.getDataSuccess(homeArticleListBean);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

}
