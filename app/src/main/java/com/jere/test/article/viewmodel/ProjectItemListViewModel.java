package com.jere.test.article.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.jere.test.article.modle.ProjectTreeRepository;
import com.jere.test.article.modle.beanfiles.ProjectItemList;

/**
 * @author jere
 */
public class ProjectItemListViewModel extends ViewModel {
    private static final String TAG = "ProjectItemListViewModel";
    private MutableLiveData<ProjectItemList> projectItemListLd;

    public ProjectItemListViewModel() {
        this.projectItemListLd = new MutableLiveData<>();
    }

    public MutableLiveData<ProjectItemList> getProjectItemListLd() {
        return projectItemListLd;
    }

    public void setProjectItemListLd(int pageNumber, int projectItemId) {
        ProjectTreeRepository.newInstance().getProjectItemList(pageNumber, projectItemId, new ProjectTreeRepository.GetProjectItemListListener() {
            @Override
            public void successful(ProjectItemList projectItemList) {
                projectItemListLd.postValue(projectItemList);
            }

            @Override
            public void failed(String errorMsg) {
                Log.e(TAG, "failed: " + errorMsg);
            }
        });
    }
}
