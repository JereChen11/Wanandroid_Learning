package com.wanandroid.java.ui.home;

import android.util.Log;

import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.HomeBanner;
import com.wanandroid.java.data.repository.HomeRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";
    private final MutableLiveData<List<HomeBanner>> homeBannerListLd;
    private final MutableLiveData<ArticleData> homeArticleDataLd;

    public HomeViewModel() {
        this.homeBannerListLd = new MutableLiveData<>();
        this.homeArticleDataLd = new MutableLiveData<>();
    }

    public MutableLiveData<List<HomeBanner>> getHomeBannerListLd() {
        return homeBannerListLd;
    }

    public void setHomeBannerListLd() {
        HomeRepository.newInstance().getHomeBannerList(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                List<HomeBanner> homeBannerList = (List<HomeBanner>) object;
                homeBannerListLd.postValue(homeBannerList);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }

    public void setRxJava2HomeBannerListLd() {
        HomeRepository.newInstance().getRxJava2HomeBannerList(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                homeBannerListLd.postValue((List<HomeBanner>) object);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "RxJava2 getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<ArticleData> getHomeArticleDataLd() {
        return homeArticleDataLd;
    }

    public void setHomeArticleDataLd(int pageNumber) {
        HomeRepository.newInstance().getHomeArticleList(pageNumber, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                ArticleData articleData = (ArticleData) object;
                homeArticleDataLd.postValue(articleData);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
