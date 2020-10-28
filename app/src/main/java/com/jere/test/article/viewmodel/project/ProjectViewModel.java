package com.jere.test.article.viewmodel.project;

import android.util.Log;

import com.jere.test.article.modle.ProjectTreeRepository;
import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.beanfiles.completeproject.ProjectItemList;
import com.jere.test.article.modle.beanfiles.completeproject.ProjectTreeItem;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProjectViewModel extends ViewModel {
    private static final String TAG = "ProjectViewModel";
    private MutableLiveData<ArrayList<ProjectTreeItem.ProjectItem>> projectTreeItemsLd;
    private MutableLiveData<ProjectItemList> projectItemListLd;

    public ProjectViewModel() {
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
        ProjectTreeRepository projectTreeRepository = ProjectTreeRepository.newInstance();
        projectTreeRepository.getProjectTreeItem(new GetWebDataListener() {
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
        ProjectTreeRepository.newInstance().getProjectItemList(pageNumber,
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
