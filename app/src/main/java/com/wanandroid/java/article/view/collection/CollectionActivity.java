package com.wanandroid.java.article.view.collection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wanandroid.java.R;
import com.wanandroid.java.article.modle.beanfiles.homearticle.ArticleListBean;
import com.wanandroid.java.article.view.ArticleDetailWebViewActivity;
import com.wanandroid.java.article.view.ArticleListViewAdapter;
import com.wanandroid.java.article.viewmodel.collection.CollectionViewModel;
import com.wanandroid.java.databinding.ActivityCollectionBinding;
import com.wanandroid.java.login.view.RegisterLoginActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class CollectionActivity extends AppCompatActivity {
    private ActivityCollectionBinding mBinding;
    private ArrayList<ArticleListBean.DataBean.DatasBean> mCollectionArticleListData = new ArrayList<>();
    private ArticleListViewAdapter articleListViewAdapter;
    private int pageNumber = 0;
    private boolean isLoadAllArticleData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_collection);

        CollectionViewModel collectionVm = new ViewModelProvider(this).get(CollectionViewModel.class);
        collectionVm.getCollectionArticleListBeanLd().observe(this, articleListBeanObserver);
        collectionVm.getCollectionArticleList(pageNumber);

        mBinding.collectionFolderRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)
                        && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadAllArticleData) {
                    pageNumber++;
                    collectionVm.getCollectionArticleList(pageNumber);
                }
            }
        });

        articleListViewAdapter = new ArticleListViewAdapter(mCollectionArticleListData,
                new ArticleListViewAdapter.AdapterItemClickListener() {
                    @Override
                    public void onPositionClicked(View v, int position) {
                        String link = mCollectionArticleListData.get(position).getLink();
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
        mBinding.collectionFolderRcy.setAdapter(articleListViewAdapter);
    }

    private Observer<ArticleListBean> articleListBeanObserver = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                mCollectionArticleListData.addAll(articleListBean.getData().getDatas());
                for (ArticleListBean.DataBean.DatasBean data : mCollectionArticleListData) {
                    data.setCollect(true);
                }
                isLoadAllArticleData = articleListBean.getData().isOver();
                articleListViewAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
                articleListViewAdapter.setData(mCollectionArticleListData);
            }
        }
    };

}
