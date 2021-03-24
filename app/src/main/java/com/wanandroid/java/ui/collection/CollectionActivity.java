package com.wanandroid.java.ui.collection;

import android.content.Intent;
import android.view.View;

import com.wanandroid.java.data.bean.Article;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.databinding.ActivityCollectionBinding;
import com.wanandroid.java.ui.adapter.ArticleListViewAdapter;
import com.wanandroid.java.ui.base.BaseVmActivity;
import com.wanandroid.java.ui.login.RegisterLoginActivity;
import com.wanandroid.java.ui.web.ArticleDetailWebViewActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class CollectionActivity extends BaseVmActivity<CollectionViewModel, ActivityCollectionBinding> {
    private final ArrayList<Article> collectionArticleListData = new ArrayList<>();
    private ArticleListViewAdapter articleListViewAdapter;
    private int pageNumber = 0;
    private boolean isLoadAllArticleData = false;

    @Override
    protected void initView() {
        viewModel.getCollectionArticleDataLd().observe(this, articleListDataObserver);
        viewModel.getCollectionArticleList(pageNumber);

        dataBinding.collectionFolderRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)
                        && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadAllArticleData) {
                    pageNumber++;
                    viewModel.getCollectionArticleList(pageNumber);
                }
            }
        });

        articleListViewAdapter = new ArticleListViewAdapter(collectionArticleListData,
                new ArticleListViewAdapter.AdapterItemClickListener() {
                    @Override
                    public void onPositionClicked(View v, int position) {
                        String link = collectionArticleListData.get(position).getLink();
                        Intent intent = new Intent(CollectionActivity.this, ArticleDetailWebViewActivity.class);
                        intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClicked(View v, int position) {

                    }

                    @Override
                    public void unLoginBeforeCollect() {
                        startActivity(new Intent(CollectionActivity.this, RegisterLoginActivity.class));
                    }
                });
        dataBinding.collectionFolderRcy.setAdapter(articleListViewAdapter);
    }

    private final Observer<ArticleData> articleListDataObserver = new Observer<ArticleData>() {
        @Override
        public void onChanged(ArticleData articleData) {
            if (articleData != null && articleData.getArticles() != null) {
                collectionArticleListData.addAll(articleData.getArticles());
                for (Article article : collectionArticleListData) {
                    article.setCollect(true);
                }
                isLoadAllArticleData = articleData.isOver();
                articleListViewAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
                articleListViewAdapter.setData(collectionArticleListData);
            }
        }
    };

}
