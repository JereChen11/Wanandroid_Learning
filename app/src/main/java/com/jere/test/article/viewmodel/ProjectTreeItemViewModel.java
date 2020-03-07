package com.jere.test.article.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;

import com.jere.test.article.modle.ProjectTreeRepository;
import com.jere.test.article.modle.api.GetWebDataListener;
import com.jere.test.article.modle.beanfiles.ProjectTreeItem;

import java.util.ArrayList;

/**
 * @author jere
 */
public class ProjectTreeItemViewModel extends ViewModel {
    private static final String TAG = "ProjectTreeItemViewModel";
    private MutableLiveData<ArrayList<ProjectTreeItem.ProjectItem>> projectTreeItemsLd;


    public ProjectTreeItemViewModel() {
        projectTreeItemsLd = new MutableLiveData<>();
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

    public MutableLiveData<ArrayList<ProjectTreeItem.ProjectItem>> getProjectTreeItemsLd() {
        return projectTreeItemsLd;
    }
}
