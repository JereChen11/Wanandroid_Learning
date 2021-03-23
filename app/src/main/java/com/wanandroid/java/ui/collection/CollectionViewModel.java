package com.wanandroid.java.ui.collection;

import android.util.Log;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.BaseResponse;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class CollectionViewModel extends ViewModel {
    private static final String TAG = "CollectionViewModel";
    private final MutableLiveData<ArticleData> collectionArticleDataLd;

    public CollectionViewModel() {
        this.collectionArticleDataLd = new MutableLiveData<>();
    }

    public MutableLiveData<ArticleData> getCollectionArticleDataLd() {
        return collectionArticleDataLd;
    }

    public void setCollectionArticleDataLd(ArticleData articleData) {
        this.collectionArticleDataLd.postValue(articleData);
    }

    public void getCollectionArticleList(int pageNumber) {
        ApiWrapper.getService().getCollectionArticleList(pageNumber).enqueue(new MyCallback<BaseResponse<ArticleData>>() {
            @Override
            public void getSuccessful(BaseResponse<ArticleData> data) {
                setCollectionArticleDataLd(data.getData());
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
            }
        });
    }
}
