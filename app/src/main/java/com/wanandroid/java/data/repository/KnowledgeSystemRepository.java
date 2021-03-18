package com.wanandroid.java.data.repository;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.api.MyCallback;
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
        ApiWrapper.getService().getKnowledgeSystem().enqueue(new MyCallback<KnowledgeSystemCategoryBean>() {
            @Override
            public void getSuccessful(KnowledgeSystemCategoryBean data) {
                listener.getDataSuccess(data);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });

    }

    public void getKnowledgeSystemArticleList(int pageNumber, int cid, final GetWebDataListener listener) {
        ApiWrapper.getService().getKnowledgeSystemArticleList(pageNumber, cid).enqueue(new MyCallback<ArticleListBean>() {
            @Override
            public void getSuccessful(ArticleListBean data) {
                listener.getDataSuccess(data);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });

    }

}
