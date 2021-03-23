package com.wanandroid.java.data.repository;

import android.util.Log;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.BaseResponse;
import com.wanandroid.java.data.bean.HomeBanner;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author jere
 */
public class HomeRepository {
    private static final String TAG = "HomeRepository";

    /**
     * 为了防止出现DCL失效问题，所以用 volatile 关键字来修饰 instance。
     */
    private volatile static HomeRepository instance;

    public static HomeRepository newInstance() {
        if (instance == null) {
            synchronized (HomeRepository.class) {
                if (instance == null) {
                    instance = new HomeRepository();
                }
            }
        }
        return instance;
    }

    public void getHomeBannerList(final GetWebDataListener listener) {
        ApiWrapper.getService().getHomeBannerList().enqueue(new MyCallback<BaseResponse<List<HomeBanner>>>() {
            @Override
            public void getSuccessful(BaseResponse<List<HomeBanner>> baseResponse) {
                listener.getDataSuccess(baseResponse.getData());
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

    public void getHomeArticleList(int pageNumber, final GetWebDataListener listener) {
        ApiWrapper.getService().getHomeArticleList(pageNumber).enqueue(new MyCallback<BaseResponse<ArticleData>>() {
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

    public void getRxJava2HomeBannerList(final GetWebDataListener listener) {
        ApiWrapper.getService().getRxJavaHomeBannerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<List<HomeBanner>>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse<List<HomeBanner>> homeBanners) {
                        listener.getDataSuccess(homeBanners.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }


}
