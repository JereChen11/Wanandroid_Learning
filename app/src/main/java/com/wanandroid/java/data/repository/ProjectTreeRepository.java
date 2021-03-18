package com.wanandroid.java.data.repository;

import com.wanandroid.java.data.api.ApiWrapper;
import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.api.MyCallback;
import com.wanandroid.java.data.bean.ProjectItemList;
import com.wanandroid.java.data.bean.ProjectTreeItem;

/**
 * @author jere
 */
public class ProjectTreeRepository {

    private static final String TAG = "ProjectTreeRepository";

    public void getProjectTreeItem(final GetWebDataListener listener) {
        ApiWrapper.getService().getProjectTreeItems().enqueue(new MyCallback<ProjectTreeItem>() {
            @Override
            public void getSuccessful(ProjectTreeItem data) {
                listener.getDataSuccess(data);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

    public void getProjectItemList(int pageNumber, int cid, final GetWebDataListener listener) {
        ApiWrapper.getService().getProjectItemList(pageNumber, cid).enqueue(new MyCallback<ProjectItemList>() {
            @Override
            public void getSuccessful(ProjectItemList data) {
                listener.getDataSuccess(data);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

}
