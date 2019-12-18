package com.jere.test.article;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jere.test.R;
import com.jere.test.article.api.ApiService;
import com.jere.test.article.api.ApiWrapper;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author JereChen
 */
public class ArticleActivity extends AppCompatActivity {
    private static final String TAG = "ArticleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

//        Button goToDetailBtn = findViewById(R.id.goto_detail_btn);
//        goToDetailBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(ArticleActivity.this, ArticleDetailActivity.class);
////                startActivity(intent);
//                getHomeArticleList();
//            }
//        });
        getHomeArticleList();

    }

    private void getHomeArticleList() {
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.getHomeArticleList().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String responseBody = response.body().string();
                        Log.d(TAG, "onResponse: " + responseBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}
