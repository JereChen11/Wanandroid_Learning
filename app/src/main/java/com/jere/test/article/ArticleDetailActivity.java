package com.jere.test.article;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jere.test.R;
import com.jere.test.article.roomdatabase.Article;
import com.jere.test.databinding.ActivityArticleDetailBinding;

/**
 * @author jere
 */
public class ArticleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        ActivityArticleDetailBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_article_detail);
        Article article = new Article();
        article.setAuthor("Jere");
        article.setUrl("http://www.csdn.com/jerechen/blog/test/url");
        binding.setArticle(article);

        binding.dataBindingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ArticleDetailActivity.this, "click dataBindingBtn", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
