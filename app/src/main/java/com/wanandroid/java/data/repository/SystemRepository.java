package com.wanandroid.java.data.repository;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.BaseResponse;
import com.wanandroid.java.data.bean.SystemCategory;

import java.util.List;

/**
 * @author jere
 */
public class SystemRepository {

    /**
     * 使用静态内部类来创建单例
     */
    private static class KnowledgeSystemRepositoryHelper {
        private static final SystemRepository INSTANCE = new SystemRepository();
    }

    public static SystemRepository getInstance() {
        return KnowledgeSystemRepositoryHelper.INSTANCE;
    }

    public void getKnowledgeSystemData(final GetWebDataListener listener) {
        ApiWrapper.getService().getKnowledgeSystemCategory().enqueue(new MyCallback<BaseResponse<List<SystemCategory>>>() {
            @Override
            public void getSuccessful(BaseResponse<List<SystemCategory>> baseResponse) {
                listener.getDataSuccess(baseResponse.getData());
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });

    }

    public void getKnowledgeSystemArticleList(int pageNumber, int cid, final GetWebDataListener listener) {
        ApiWrapper.getService().getKnowledgeSystemArticleList(pageNumber, cid).enqueue(new MyCallback<BaseResponse<ArticleData>>() {
            @Override
            public void getSuccessful(BaseResponse<ArticleData> baseResponse) {
                listener.getDataSuccess(baseResponse.getData());
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });

    }

}
