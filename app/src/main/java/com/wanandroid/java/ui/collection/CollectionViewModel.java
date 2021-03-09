package com.wanandroid.java.ui.collection;

import android.util.Log;

import com.google.gson.Gson;
import com.wanandroid.java.data.api.AbstractRetrofitCallback;
import com.wanandroid.java.data.api.ApiService;
import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.bean.ArticleListBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class CollectionViewModel extends ViewModel {
    private static final String TAG = "CollectionViewModel";
    private MutableLiveData<ArticleListBean> collectionArticleListBeanLd;

    public CollectionViewModel() {
        this.collectionArticleListBeanLd = new MutableLiveData<>();
    }

    public MutableLiveData<ArticleListBean> getCollectionArticleListBeanLd() {
        return collectionArticleListBeanLd;
    }

    public void setCollectionArticleListBeanLd(ArticleListBean articleListBean) {
        this.collectionArticleListBeanLd.postValue(articleListBean);
    }

    public void getCollectionArticleList(int pageNumber) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getCollectionArticleList(pageNumber).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                ArticleListBean articleListBean = gson.fromJson(responseBody, ArticleListBean.class);
                setCollectionArticleListBeanLd(articleListBean);
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
            }
        });
    }
}
