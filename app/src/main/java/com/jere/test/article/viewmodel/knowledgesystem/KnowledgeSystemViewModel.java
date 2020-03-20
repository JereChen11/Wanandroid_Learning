package com.jere.test.article.viewmodel.knowledgesystem;

import android.util.Log;

import com.jere.test.article.modle.KnowledgeSystemRepository;
import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.beanfiles.knowledgesystem.KnowledgeSystemArticleListBean;
import com.jere.test.article.modle.beanfiles.knowledgesystem.KnowledgeSystemCategoryBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class KnowledgeSystemViewModel extends ViewModel {
    private static final String TAG = "KnowledgeSystemViewMode";
    private MutableLiveData<KnowledgeSystemCategoryBean> knowledgeSystemCategoryBeanLd;
    private MutableLiveData<KnowledgeSystemArticleListBean> knowledgeSystemArticleListBeanLd;

    public KnowledgeSystemViewModel() {
        this.knowledgeSystemCategoryBeanLd = new MutableLiveData<>();
        this.knowledgeSystemArticleListBeanLd = new MutableLiveData<>();
    }

    public MutableLiveData<KnowledgeSystemCategoryBean> getKnowledgeSystemCategoryBeanLd() {
        return knowledgeSystemCategoryBeanLd;
    }

    public void setKnowledgeSystemCategoryBeanLd() {
        KnowledgeSystemRepository.getInstance().getKnowledgeSystemData(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                KnowledgeSystemCategoryBean knowledgeSystemCategoryBean = (KnowledgeSystemCategoryBean) object;
                knowledgeSystemCategoryBeanLd.postValue(knowledgeSystemCategoryBean);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<KnowledgeSystemArticleListBean> getKnowledgeSystemArticleListBeanLd() {
        return knowledgeSystemArticleListBeanLd;
    }

    public void setKnowledgeSystemArticleListBeanLd(int cid) {
        KnowledgeSystemRepository.getInstance().getKnowledgeSystemArticleList(cid, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                KnowledgeSystemArticleListBean knowledgeSystemArticleListBean = (KnowledgeSystemArticleListBean) object;
                knowledgeSystemArticleListBeanLd.postValue(knowledgeSystemArticleListBean);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
