package com.wanandroid.java.article.viewmodel.knowledgesystem;

import android.util.Log;

import com.wanandroid.java.article.modle.KnowledgeSystemRepository;
import com.wanandroid.java.article.modle.api.GetWebDataListener;
import com.wanandroid.java.article.modle.beanfiles.homearticle.ArticleListBean;
import com.wanandroid.java.article.modle.beanfiles.knowledgesystem.KnowledgeSystemCategoryBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class KnowledgeSystemViewModel extends ViewModel {
    private static final String TAG = "KnowledgeSystemViewMode";
    private MutableLiveData<KnowledgeSystemCategoryBean> knowledgeSystemCategoryBeanLd;
    private MutableLiveData<ArticleListBean> knowledgeSystemArticleListBeanLd;

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

    public MutableLiveData<ArticleListBean> getKnowledgeSystemArticleListBeanLd() {
        return knowledgeSystemArticleListBeanLd;
    }

    public void setKnowledgeSystemArticleListBeanLd(int pageNumber, int cid) {
        KnowledgeSystemRepository.getInstance().getKnowledgeSystemArticleList(pageNumber, cid, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                ArticleListBean articleListBean = (ArticleListBean) object;
                knowledgeSystemArticleListBeanLd.postValue(articleListBean);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
