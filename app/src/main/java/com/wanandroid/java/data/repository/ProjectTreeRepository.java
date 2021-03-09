package com.wanandroid.java.data.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.wanandroid.java.data.api.AbstractRetrofitCallback;
import com.wanandroid.java.data.api.ApiService;
import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ProjectItemList;
import com.wanandroid.java.data.bean.ProjectTreeItem;

/**
 * @author jere
 */
public class ProjectTreeRepository {

    private static final String TAG = "ProjectTreeRepository";
    private volatile static ProjectTreeRepository instance;

    ProjectTreeRepository() {

    }

    public static ProjectTreeRepository newInstance() {
        if (instance == null) {
            synchronized (ProjectTreeRepository.class) {
                if (instance == null) {
                    instance = new ProjectTreeRepository();
                }
            }
        }
        return instance;
    }

    public void getProjectTreeItem(final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getProjectTreeItems().enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                ProjectTreeItem projectTreeItem = gson.fromJson(responseBody, ProjectTreeItem.class);
                Log.e(TAG, "onResponse: projectItems size = " + projectTreeItem.getData().size());
                listener.getDataSuccess(projectTreeItem);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

    public void getProjectItemList(int pageNumber, int cid, final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getProjectItemList(pageNumber, cid).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                ProjectItemList projectItemList = gson.fromJson(responseBody, ProjectItemList.class);
                listener.getDataSuccess(projectItemList);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

}
