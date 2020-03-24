package com.jere.test.article.viewmodel.homearticle;

import android.util.Log;

import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.HomeRepository;
import com.jere.test.article.modle.beanfiles.homearticle.HomeArticleListBean;
import com.jere.test.article.modle.beanfiles.homearticle.HomeBannerListBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";
    private MutableLiveData<HomeBannerListBean> homeBannerListLd;
    private MutableLiveData<HomeArticleListBean> homeArticleListBeanLd;

    public HomeViewModel() {
        this.homeBannerListLd = new MutableLiveData<>();
        this.homeArticleListBeanLd = new MutableLiveData<>();
    }

    public MutableLiveData<HomeBannerListBean> getHomeBannerListLd() {
        return homeBannerListLd;
    }

    public void setHomeBannerListLd() {
        HomeRepository.newInstance().getHomeBannerList(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                HomeBannerListBean homeBannerListBean = (HomeBannerListBean) object;
                homeBannerListLd.postValue(homeBannerListBean);
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
                HomeBannerListBean homeBannerListBean = (HomeBannerListBean) object;
                homeBannerListLd.postValue(homeBannerListBean);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "RxJava2 getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<HomeArticleListBean> getHomeArticleListBeanLd() {
        return homeArticleListBeanLd;
    }

    public void setHomeArticleListBeanLd() {
        HomeRepository.newInstance().getHomeArticleList(0, new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                HomeArticleListBean homeArticleListBean = (HomeArticleListBean) object;
                homeArticleListBeanLd.postValue(homeArticleListBean);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }
}
