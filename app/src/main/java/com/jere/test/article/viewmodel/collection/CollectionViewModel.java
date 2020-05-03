package com.jere.test.article.viewmodel.collection;

import android.util.Log;

import com.google.gson.Gson;
import com.jere.test.article.modle.api.AbstractRetrofitCallback;
import com.jere.test.article.modle.api.ApiService;
import com.jere.test.article.modle.api.ApiWrapper;
import com.jere.test.article.modle.beanfiles.homearticle.HomeArticleListBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class CollectionViewModel extends ViewModel {
    private static final String TAG = "CollectionViewModel";
    private MutableLiveData<HomeArticleListBean> collectionArticleListBeanLd;

    public CollectionViewModel() {
        this.collectionArticleListBeanLd = new MutableLiveData<>();
    }

    public MutableLiveData<HomeArticleListBean> getCollectionArticleListBeanLd() {
        return collectionArticleListBeanLd;
    }

    public void setCollectionArticleListBeanLd(HomeArticleListBean homeArticleListBean) {
        this.collectionArticleListBeanLd.postValue(homeArticleListBean);
    }

    public void getCollectionArticleList() {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getCollectionArticleList().enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                HomeArticleListBean homeArticleListBean = gson.fromJson(responseBody, HomeArticleListBean.class);
                setCollectionArticleListBeanLd(homeArticleListBean);
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
            }
        });
    }
}
