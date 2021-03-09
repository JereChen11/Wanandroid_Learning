package com.wanandroid.java.ui.wechat;

import android.util.Log;

import com.wanandroid.java.data.repository.WeChatArticleRepository;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ArticleListBean;
import com.wanandroid.java.data.bean.WeChatArticleBloggerList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class WeChatBlogArticleViewModel extends ViewModel {
    private static final String TAG = "WeChatBlogArticleVm";
    private final MutableLiveData<WeChatArticleBloggerList> weChatArticleBloggerListLd;
    private final MutableLiveData<ArticleListBean> weChatArticleListLd;

    public WeChatBlogArticleViewModel() {
        weChatArticleBloggerListLd = new MutableLiveData<>();
        weChatArticleListLd = new MutableLiveData<>();
    }

    public MutableLiveData<WeChatArticleBloggerList> getWeChatArticleBloggerListLd() {
        return weChatArticleBloggerListLd;
    }

    public void setWeChatArticleBloggerListLd() {
        WeChatArticleRepository.newInstance().getWeChatArticleBloggerList(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                WeChatArticleBloggerList weChatArticleBloggerList = (WeChatArticleBloggerList) object;
                weChatArticleBloggerListLd.postValue(weChatArticleBloggerList);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<ArticleListBean> getWeChatArticleListLd() {
        return weChatArticleListLd;
    }

    public void setWeChatArticleListLd(int authorId, int pageNumber) {
        WeChatArticleRepository.newInstance().getWeChatArticleList(authorId, pageNumber, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                ArticleListBean articleListBean = (ArticleListBean) object;
                weChatArticleListLd.postValue(articleListBean);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
