package com.jere.test.article;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jere.test.R;
import com.jere.test.article.api.ApiService;
import com.jere.test.article.api.ApiWrapper;
import com.jere.test.article.roomdatabase.AppRoomDatabase;
import com.jere.test.article.roomdatabase.Article;

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

        final AppRoomDatabase appRoomDatabase = AppRoomDatabase.getInstance(this);

        Button insertArticleBtn = findViewById(R.id.insert_article_btn);
        insertArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article article = new Article();
                article.setAuthor("JereChen");
                article.setUrl("https://blog.csdn.net/jerechen/article/details/93543819");
                Log.d(TAG, "onClick: insert article");
                appRoomDatabase.articleDao.insertArticle(article);
            }
        });


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
