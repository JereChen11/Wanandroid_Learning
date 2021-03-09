package com.wanandroid.java.data.repository;

import com.wanandroid.java.data.api.AbstractRetrofitCallback;
import com.wanandroid.java.data.api.ApiService;
import com.wanandroid.java.data.api.ApiWrapper;

/**
 * @author jere
 */
public class CollectRepository {

    private volatile static CollectRepository instance;

    public static CollectRepository newInstance() {
        if (instance == null) {
            synchronized (CollectRepository.class) {
                if (instance == null) {
                    instance = new CollectRepository();
                }
            }
        }
        return instance;
    }

    public void collectArticle(int articleId, final CollectOrUnCollectListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.collectArticle(articleId).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                listener.isSuccessful(true);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.isSuccessful(false);
            }
        });
    }

    public void unCollectArticle(int articleId, final CollectOrUnCollectListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.unCollectArticle(articleId).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                listener.isSuccessful(true);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.isSuccessful(false);
            }
        });
    }

    public interface CollectOrUnCollectListener {
        void isSuccessful(boolean isSuccess);
    }
}
