package com.wanandroid.java.ui.system;

import android.util.Log;

import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.SystemCategory;
import com.wanandroid.java.data.repository.SystemRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class SystemViewModel extends ViewModel {
    private static final String TAG = "KnowledgeSystemViewMode";
    private final MutableLiveData<List<SystemCategory>> systemCategoryBeanLd;
    private final MutableLiveData<ArticleData> systemArticleDataLd;

    public SystemViewModel() {
        this.systemCategoryBeanLd = new MutableLiveData<>();
        this.systemArticleDataLd = new MutableLiveData<>();
    }

    public MutableLiveData<List<SystemCategory>> getSystemCategoryBeanLd() {
        return systemCategoryBeanLd;
    }

    public void setSystemCategoryBeanLd() {
        SystemRepository.getInstance().getKnowledgeSystemData(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                systemCategoryBeanLd.postValue((List<SystemCategory>) object);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<ArticleData> getSystemArticleDataLd() {
        return systemArticleDataLd;
    }

    public void setSystemArticleListBeanLd(int pageNumber, int cid) {
        SystemRepository.getInstance().getKnowledgeSystemArticleList(pageNumber, cid, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                ArticleData articleData = (ArticleData) object;
                systemArticleDataLd.postValue(articleData);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
