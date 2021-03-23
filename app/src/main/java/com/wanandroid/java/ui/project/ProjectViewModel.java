package com.wanandroid.java.ui.project;

import android.util.Log;

import com.wanandroid.java.data.api.GetWebDataListener;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.data.bean.ProjectType;
import com.wanandroid.java.data.repository.ProjectTreeRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class ProjectViewModel extends ViewModel {
    private static final String TAG = "ProjectViewModel";
    private final MutableLiveData<List<ProjectType>> projectTypeDataLd;
    private final MutableLiveData<ArticleData> projectArticleDataLd;
    private final ProjectTreeRepository repository;

    public ProjectViewModel(ProjectTreeRepository repository) {
        this.repository = repository;
        this.projectTypeDataLd = new MutableLiveData<>();
        this.projectArticleDataLd = new MutableLiveData<>();
    }

    public MutableLiveData<List<ProjectType>> getProjectTypeDataLd() {
        return projectTypeDataLd;
    }

    /**
     * 需要将此数据存储到数据库，不能每次进去都是重新从web服务器上获取。思考：此处时候可以伸展到Android缓存机制？
     */
    public void setProjectTreeItemsLd() {
        repository.getProjectTreeItem(new GetWebDataListener() {
            @Override
            public void getDataSuccess(Object object) {
                projectTypeDataLd.setValue((List<ProjectType>) object);
            }

            @Override
            public void getDataFailed(String failedMsg) {
                Log.e(TAG, "getDataFailed: " + failedMsg);
            }
        });
    }

    public MutableLiveData<ArticleData> getProjectArticleDataLd() {
        return projectArticleDataLd;
    }

    public void setProjectItemListLd(int pageNumber, final int projectItemId) {
        repository.getProjectItemList(pageNumber,
                projectItemId,
                new GetWebDataListener() {
                    @Override
                    public void getDataSuccess(Object object) {
                        projectArticleDataLd.postValue((ArticleData) object);
                    }

                    @Override
                    public void getDataFailed(String failedMsg) {
                        Log.e(TAG, "getDataFailed: " + failedMsg);
                    }
                });
    }


}
