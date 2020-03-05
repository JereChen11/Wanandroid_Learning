package com.jere.test.article.modle;

import android.util.Log;

import com.google.gson.Gson;
import com.jere.test.article.modle.api.AbstractRetrofitCallback;
import com.jere.test.article.modle.api.ApiService;
import com.jere.test.article.modle.api.ApiWrapper;
import com.jere.test.article.modle.beanfiles.ProjectItemList;
import com.jere.test.article.modle.beanfiles.ProjectTreeItem;
import com.jere.test.article.modle.beanfiles.wechatofficialaccount.WeChatArticleList;
import com.jere.test.article.modle.beanfiles.wechatofficialaccount.WeChatOfficialAccountBloggerList;

/**
 * @author jere
 */
public class ProjectTreeRepository {

    private static final String TAG = "ProjectTreeRepository";
    private static ProjectTreeRepository instance;

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

    public void getWeChatOfficialAccountBloggerList(final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getWeChatOfficialAccountBloggerList().enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                WeChatOfficialAccountBloggerList bloggerList = gson.fromJson(responseBody, WeChatOfficialAccountBloggerList.class);
                listener.getDataSuccess(bloggerList);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }


    public void getWeChatArticleList(int authorId, int pageNumber, final GetWebDataListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getWeChatArticleList(authorId, pageNumber).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Gson gson = new Gson();
                WeChatArticleList weChatArticleList = gson.fromJson(responseBody, WeChatArticleList.class);
                listener.getDataSuccess(weChatArticleList);
            }

            @Override
            public void getFailed(String failedMsg) {
                listener.getDataFailed(failedMsg);
            }
        });
    }

    public interface GetWebDataListener {
        /**
         * get data from CMS successful
         * @param object 反序列化得到的对象 gson.fromJson()
         */
        void getDataSuccess(Object object);

        /**
         * get data from CMS failed
         * @param failedMsg String type error message
         */
        void getDataFailed(String failedMsg);
    }

}
