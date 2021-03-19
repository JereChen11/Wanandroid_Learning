package com.wanandroid.java.ui.system;

import android.util.Log;

import com.wanandroid.java.data.repository.SystemRepository;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ArticleListBean;
import com.wanandroid.java.data.bean.SystemCategoryBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class SystemViewModel extends ViewModel {
    private static final String TAG = "KnowledgeSystemViewMode";
    private final MutableLiveData<SystemCategoryBean> systemCategoryBeanLd;
    private final MutableLiveData<ArticleListBean> systemArticleListBeanLd;

    public SystemViewModel() {
        this.systemCategoryBeanLd = new MutableLiveData<>();
        this.systemArticleListBeanLd = new MutableLiveData<>();
    }

    public MutableLiveData<SystemCategoryBean> getSystemCategoryBeanLd() {
        return systemCategoryBeanLd;
    }

    public void setSystemCategoryBeanLd() {
        SystemRepository.getInstance().getKnowledgeSystemData(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                SystemCategoryBean systemCategoryBean = (SystemCategoryBean) object;
                systemCategoryBeanLd.postValue(systemCategoryBean);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<ArticleListBean> getSystemArticleListBeanLd() {
        return systemArticleListBeanLd;
    }

    public void setSystemArticleListBeanLd(int pageNumber, int cid) {
        SystemRepository.getInstance().getKnowledgeSystemArticleList(pageNumber, cid, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                ArticleListBean articleListBean = (ArticleListBean) object;
                systemArticleListBeanLd.postValue(articleListBean);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
