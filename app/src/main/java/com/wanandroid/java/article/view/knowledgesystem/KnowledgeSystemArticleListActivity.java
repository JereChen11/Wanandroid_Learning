package com.wanandroid.java.article.view.knowledgesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wanandroid.java.R;
import com.wanandroid.java.article.modle.beanfiles.homearticle.ArticleListBean;
import com.wanandroid.java.article.view.ArticleDetailWebViewActivity;
import com.wanandroid.java.article.view.ArticleListViewAdapter;
import com.wanandroid.java.article.viewmodel.knowledgesystem.KnowledgeSystemViewModel;
import com.wanandroid.java.databinding.ActivityKnowledgeSystemArticleListBinding;
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
public class KnowledgeSystemArticleListActivity extends AppCompatActivity {
    private ArrayList<ArticleListBean.DataBean.DatasBean> mKnowledgeSystemArticleList = new ArrayList<>();
    private ActivityKnowledgeSystemArticleListBinding mBinding;
    private ArticleListViewAdapter articleListViewAdapter;
    private int pageNumber = 0;
    private boolean isLoadAllArticleData = false;

    private Observer<ArticleListBean> observer = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                mKnowledgeSystemArticleList.addAll(articleListBean.getData().getDatas());
                articleListViewAdapter.setData(mKnowledgeSystemArticleList);
                isLoadAllArticleData = articleListBean.getData().isOver();
                articleListViewAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_knowledge_system_article_list);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        int childItemId = bundle.getInt(KnowledgeSystemFragment.CHILD_ITEM_ID_KEY);
        String childItemName = bundle.getString(KnowledgeSystemFragment.CHILD_ITEM_NAME_KEY);

        mBinding.knowledgeSystemTitleBar.setTitle(childItemName);

        KnowledgeSystemViewModel knowledgeSystemVm = new ViewModelProvider(this).get(KnowledgeSystemViewModel.class);
        knowledgeSystemVm.getKnowledgeSystemArticleListBeanLd().observe(this, observer);
        knowledgeSystemVm.setKnowledgeSystemArticleListBeanLd(pageNumber, childItemId);

        articleListViewAdapter = new ArticleListViewAdapter(mKnowledgeSystemArticleList,
                new ArticleListViewAdapter.AdapterItemClickListener() {
                    @Override
                    public void onPositionClicked(View v, int position) {
                        String link = mKnowledgeSystemArticleList.get(position).getLink();
                        Intent intent = new Intent(KnowledgeSystemArticleListActivity.this, ArticleDetailWebViewActivity.class);
                        intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClicked(View v, int position) {

                    }

                    @Override
                    public void unLoginBeforeCollect() {
                        startActivity(new Intent(KnowledgeSystemArticleListActivity.this, RegisterLoginActivity.class));
                    }
                });
        mBinding.knowledgeSystemArticleRecyclerView.setAdapter(articleListViewAdapter);
        mBinding.knowledgeSystemArticleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)
                        && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadAllArticleData) {
                    pageNumber++;
                    knowledgeSystemVm.setKnowledgeSystemArticleListBeanLd(pageNumber, childItemId);
                }
            }
        });

    }

}
