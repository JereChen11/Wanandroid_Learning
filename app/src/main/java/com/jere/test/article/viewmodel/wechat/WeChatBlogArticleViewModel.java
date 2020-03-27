package com.jere.test.article.viewmodel.wechat;

import android.util.Log;

import com.jere.test.article.modle.WeChatArticleRepository;
import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.beanfiles.wechat.WeChatArticleBloggerList;
import com.jere.test.article.modle.beanfiles.wechat.WeChatArticleList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class WeChatBlogArticleViewModel extends ViewModel {
    private static final String TAG = "WeChatBlogArticleVm";
    private MutableLiveData<WeChatArticleBloggerList> weChatArticleBloggerListLd;
    private MutableLiveData<WeChatArticleList> weChatArticleListLd;

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

    public MutableLiveData<WeChatArticleList> getWeChatArticleListLd() {
        return weChatArticleListLd;
    }

    public void setWeChatArticleListLd(int authorId, int pageNumber) {
        WeChatArticleRepository.newInstance().getWeChatArticleList(authorId, pageNumber, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                WeChatArticleList weChatArticleList = (WeChatArticleList) object;
                weChatArticleListLd.postValue(weChatArticleList);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
