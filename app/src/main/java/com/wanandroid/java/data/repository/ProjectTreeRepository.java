package com.wanandroid.java.data.repository;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.BaseResponse;
import com.wanandroid.java.data.bean.ProjectType;

import java.util.List;

/**
 * @author jere
 */
public class ProjectTreeRepository {

    private static final String TAG = "ProjectTreeRepository";

    public void getProjectTreeItem(final GetWebDataListener listener) {
        ApiWrapper.getService().getProjectTreeItems().enqueue(new MyCallback<BaseResponse<List<ProjectType>>>() {
            @Override
            public void getSuccessful(BaseResponse<List<ProjectType>> baseResponse) {
                listener.getDataSuccess(baseResponse.getData());
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });

    }

    public void getProjectItemList(int pageNumber, int cid, final GetWebDataListener listener) {
        ApiWrapper.getService().getProjectItemList(pageNumber, cid).enqueue(new MyCallback<BaseResponse<ArticleData>>() {
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
