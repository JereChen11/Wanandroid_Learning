package com.jere.test.article.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.jere.test.article.modle.WeChatArticleRepository;
import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.beanfiles.wechatofficialaccount.WeChatArticleBloggerList;

/**
 * @author jere
 */
public class WeChatArticleBloggerListViewModel extends ViewModel {
    private static final String TAG = "WeChatArticleBloggerListViewModel";
    private MutableLiveData<WeChatArticleBloggerList> weChatArticleBloggerListLd;

    public WeChatArticleBloggerListViewModel() {
        weChatArticleBloggerListLd = new MutableLiveData<>();
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
}
