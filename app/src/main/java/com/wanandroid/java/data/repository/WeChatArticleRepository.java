package com.wanandroid.java.data.repository;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.ArticleListBean;
import com.wanandroid.java.data.bean.WeChatArticleBloggerList;

/**
 * @author jere
 */
public class WeChatArticleRepository {
    private static final String TAG = "WeChatArticleRepository";
    private volatile static WeChatArticleRepository instance;

    public static WeChatArticleRepository newInstance() {
        if (instance == null) {
            synchronized (WeChatArticleRepository.class) {
                if (instance == null) {
                    instance = new WeChatArticleRepository();
                }
            }
        }
        return instance;
    }

    public void getWeChatArticleBloggerList(final GetWebDataListener listener) {
        ApiWrapper.getService().getWeChatOfficialAccountBloggerList().enqueue(new MyCallback<WeChatArticleBloggerList>() {
            @Override
            public void getSuccessful(WeChatArticleBloggerList data) {
                listener.getDataSuccess(data);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }


    public void getWeChatArticleList(int authorId, int pageNumber, final GetWebDataListener listener) {
        ApiWrapper.getService().getWeChatArticleList(authorId, pageNumber).enqueue(new MyCallback<ArticleListBean>() {
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
