package com.jere.test.article.viewmodel.completeproject;

import android.util.Log;

import com.jere.test.article.modle.ProjectTreeRepository;
import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.beanfiles.completeproject.ProjectItemList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class ProjectItemListViewModel extends ViewModel {
    private static final String TAG = "ProjectItemListVm";
    private MutableLiveData<ProjectItemList> projectItemListLd;

    public ProjectItemListViewModel() {
        this.projectItemListLd = new MutableLiveData<>();
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
