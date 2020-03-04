package com.jere.test.article.modle;

import android.util.Log;

import com.google.gson.Gson;
import com.jere.test.article.modle.api.ApiService;
import com.jere.test.article.modle.api.ApiWrapper;
import com.jere.test.article.modle.beanfiles.ProjectItemList;
import com.jere.test.article.modle.beanfiles.ProjectTreeItem;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void getProjectTreeItem(final GetProjectItemsListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getProjectTreeItems().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String responseBody = response.body().string();
                        Gson gson = new Gson();
                        ProjectTreeItem projectTreeItem = gson.fromJson(responseBody, ProjectTreeItem.class);
                        Log.e(TAG, "onResponse: projectItems size = " + projectTreeItem.getData().size());
                        listener.getDataSuccessful(projectTreeItem);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    listener.getDataFailed("response.code() = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.getDataFailed(t.getMessage());
            }
        });
    }

    public void getProjectItemList(int pageNumber, int cid, final GetProjectItemListListener listener) {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getProjectItemList(pageNumber, cid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String responseBody = response.body().string();
                        Gson gson = new Gson();
                        ProjectItemList projectItemList = gson.fromJson(responseBody, ProjectItemList.class);
                        listener.successful(projectItemList);
                    } catch (IOException e) {
                        e.printStackTrace();
                        listener.failed(e.getMessage());
                    }
                } else {
                    listener.failed("response.code() = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.failed(t.getMessage());
            }
        });
    }


    public interface GetProjectItemsListener {
        void getDataSuccessful(ProjectTreeItem projectTreeItem);

        void getDataFailed(String failedMsg);
    }

    public interface GetProjectItemListListener {
        void successful(ProjectItemList projectItemList);

        void failed(String errorMsg);
    }

}
