package com.wanandroid.java.ui.project;

import android.util.Log;

import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ProjectItemList;
import com.wanandroid.java.data.bean.ProjectTreeItem;
import com.wanandroid.java.data.repository.ProjectTreeRepository;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class ProjectViewModel extends ViewModel {
    private static final String TAG = "ProjectViewModel";
    private final MutableLiveData<ArrayList<ProjectTreeItem.ProjectItem>> projectTreeItemsLd;
    private final MutableLiveData<ProjectItemList> projectItemListLd;
    private final ProjectTreeRepository repository;

    public ProjectViewModel(ProjectTreeRepository repository) {
        this.repository = repository;
        this.projectTreeItemsLd = new MutableLiveData<>();
        this.projectItemListLd = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<ProjectTreeItem.ProjectItem>> getProjectTreeItemsLd() {
        return projectTreeItemsLd;
    }

    /**
     * 需要将此数据存储到数据库，不能每次进去都是重新从web服务器上获取。思考：此处时候可以伸展到Android缓存机制？
     */
    public void setProjectTreeItemsLd() {
        repository.getProjectTreeItem(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                ProjectTreeItem projectTreeItem = (ProjectTreeItem) object;
                projectTreeItemsLd.setValue(projectTreeItem.getData());
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<ProjectItemList> getProjectItemListLd() {
        return projectItemListLd;
    }

    public void setProjectItemListLd(int pageNumber, final int projectItemId) {
        repository.getProjectItemList(pageNumber,
                projectItemId,
                new GetWebDataListener() {
                    @Override
                    public void getDataSuccess(Object object) {
                        ProjectItemList projectItemList = (ProjectItemList) object;
                        projectItemListLd.postValue(projectItemList);
                    }

                    @Override
                    public void getDataFailed(String failedMsg) {
                        Log.e(TAG, "getDataFailed: " + failedMsg);
                    }
                });
    }


}
