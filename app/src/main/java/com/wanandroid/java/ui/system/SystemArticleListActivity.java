package com.wanandroid.java.ui.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wanandroid.java.R;
import com.wanandroid.java.data.bean.ArticleListBean;
import com.wanandroid.java.databinding.ActivitySystemArticleListBinding;
import com.wanandroid.java.ui.web.ArticleDetailWebViewActivity;
import com.wanandroid.java.ui.adapter.ArticleListViewAdapter;
import com.wanandroid.java.ui.base.BaseVmActivity;
import com.wanandroid.java.ui.login.RegisterLoginActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class SystemArticleListActivity extends BaseVmActivity<SystemViewModel, ActivitySystemArticleListBinding> {

    private final ArrayList<ArticleListBean.DataBean.DatasBean> knowledgeSystemArticles = new ArrayList<>();
//    private ActivitySystemArticleListBinding mBinding;
    private ArticleListViewAdapter articleListViewAdapter;
    private int pageNumber = 0;
    private boolean isLoadAllArticleData = false;

    private final Observer<ArticleListBean> systemArticleDataObserver = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                knowledgeSystemArticles.addAll(articleListBean.getData().getDatas());
                articleListViewAdapter.setData(knowledgeSystemArticles);
                isLoadAllArticleData = articleListBean.getData().isOver();
                articleListViewAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_article_list;
    }

    @Override
    protected Class<SystemViewModel> getViewModelClass() {
        return SystemViewModel.class;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        int childItemId = bundle.getInt(SystemFragment.CHILD_ITEM_ID_KEY);
        String childItemName = bundle.getString(SystemFragment.CHILD_ITEM_NAME_KEY);

        dataBinding.systemTitleBar.setTitle(childItemName);

//        SystemViewModel knowledgeSystemVm = new ViewModelProvider(this).get(SystemViewModel.class);
        viewModel.getSystemArticleListBeanLd().observe(this, systemArticleDataObserver);
        viewModel.setSystemArticleListBeanLd(pageNumber, childItemId);

        articleListViewAdapter = new ArticleListViewAdapter(knowledgeSystemArticles,
                new ArticleListViewAdapter.AdapterItemClickListener() {
                    @Override
                    public void onPositionClicked(View v, int position) {
                        String link = knowledgeSystemArticles.get(position).getLink();
                        Intent intent = new Intent(SystemArticleListActivity.this, ArticleDetailWebViewActivity.class);
                        intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClicked(View v, int position) {

                    }

                    @Override
                    public void unLoginBeforeCollect() {
                        startActivity(new Intent(SystemArticleListActivity.this, RegisterLoginActivity.class));
                    }
                });
        dataBinding.systemArticleRecyclerView.setAdapter(articleListViewAdapter);
        dataBinding.systemArticleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)
                        && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadAllArticleData) {
                    pageNumber++;
                    viewModel.setSystemArticleListBeanLd(pageNumber, childItemId);
                }
            }
        });
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_system_article_list);
//
//
//
//    }

}
