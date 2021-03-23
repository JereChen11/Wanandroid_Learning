package com.wanandroid.java.data.repository;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.BaseResponse;
import com.wanandroid.java.data.bean.WeChatBlogger;

import java.util.List;

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
        ApiWrapper.getService().getWeChatBloggerList().enqueue(new MyCallback<BaseResponse<List<WeChatBlogger>>>() {
            @Override
            public void getSuccessful(BaseResponse<List<WeChatBlogger>> baseResponse) {
                listener.getDataSuccess(baseResponse.getData());
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }


    public void getWeChatArticleList(int authorId, int pageNumber, final GetWebDataListener listener) {
        ApiWrapper.getService().getWeChatArticleList(authorId, pageNumber).enqueue(new MyCallback<BaseResponse<ArticleData>>() {
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
