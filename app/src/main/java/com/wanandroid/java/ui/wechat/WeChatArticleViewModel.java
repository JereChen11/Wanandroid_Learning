package com.wanandroid.java.ui.wechat;

import android.util.Log;

import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.WeChatBlogger;
import com.wanandroid.java.data.repository.WeChatArticleRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class WeChatArticleViewModel extends ViewModel {
    private static final String TAG = "WeChatBlogArticleVm";
    private final MutableLiveData<List<WeChatBlogger>> weChatBloggerListLd;
    private final MutableLiveData<ArticleData> weChatArticleDataLd;
    private final WeChatArticleRepository repository;

    public WeChatArticleViewModel(WeChatArticleRepository repository) {
        weChatBloggerListLd = new MutableLiveData<>();
        weChatArticleDataLd = new MutableLiveData<>();
        this.repository = repository;
    }

    public MutableLiveData<List<WeChatBlogger>> getWeChatBloggerListLd() {
        return weChatBloggerListLd;
    }

    public void setWeChatBloggerListLd() {
        repository.getWeChatArticleBloggerList(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                weChatBloggerListLd.postValue((List<WeChatBlogger>) object);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<ArticleData> getWeChatArticleDataLd() {
        return weChatArticleDataLd;
    }

    public void setWeChatArticleListLd(int pageNumber, int authorId) {
        repository.getWeChatArticleList(authorId, pageNumber, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                weChatArticleDataLd.postValue((ArticleData) object);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
