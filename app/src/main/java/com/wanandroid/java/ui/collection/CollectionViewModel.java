package com.wanandroid.java.ui.collection;

import android.util.Log;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.MyCallback;
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
        ApiWrapper.getService().getCollectionArticleList(pageNumber).enqueue(new MyCallback<ArticleListBean>() {
            @Override
            public void getSuccessful(ArticleListBean data) {
                setCollectionArticleListBeanLd(data);
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
            }
        });
    }
}
