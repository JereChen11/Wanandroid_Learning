package com.wanandroid.java.data.repository;

import com.google.gson.Gson;
import com.wanandroid.java.data.api.AbstractRetrofitCallback;
import com.wanandroid.java.data.api.ApiService;
import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ArticleListBean;
import com.wanandroid.java.data.bean.KnowledgeSystemCategoryBean;

/**
 * @author jere
 */
public class KnowledgeSystemRepository {

    /**
     * 使用静态内部类来创建单例
     */
    private static class KnowledgeSystemRepositoryHelper {
        private static final KnowledgeSystemRepository INSTANCE = new KnowledgeSystemRepository();
    }

    public static KnowledgeSystemRepository getInstance() {
        return KnowledgeSystemRepositoryHelper.INSTANCE;
    }

    public void getKnowledgeSystemData(final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getKnowledgeSystem().enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                KnowledgeSystemCategoryBean knowledgeSystemCategoryBean = gson.fromJson(responseBody, KnowledgeSystemCategoryBean.class);
                listener.getDataSuccess(knowledgeSystemCategoryBean);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

    public void getKnowledgeSystemArticleList(int pageNumber, int cid, final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getKnowledgeSystemArticleList(pageNumber, cid).enqueue(new AbstractRetrofitCallback() {
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
