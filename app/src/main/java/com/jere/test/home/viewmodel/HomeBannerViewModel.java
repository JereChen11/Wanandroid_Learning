package com.jere.test.home.viewmodel;

import android.util.Log;

import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.home.model.HomeRepository;
import com.jere.test.home.model.beanfiles.HomeBannerListBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class HomeBannerViewModel extends ViewModel {
    private static final String TAG = "HomeBannerViewModel";
    private MutableLiveData<HomeBannerListBean> homeBannerListLd;

    public HomeBannerViewModel() {
        this.homeBannerListLd = new MutableLiveData<>();
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

}
