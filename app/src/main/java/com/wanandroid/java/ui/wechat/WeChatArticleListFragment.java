package com.wanandroid.java.ui.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wanandroid.java.data.bean.Article;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.databinding.FragmentWeChatArticleListBinding;
import com.wanandroid.java.ui.adapter.ArticleListViewAdapter;
import com.wanandroid.java.ui.base.BaseVmFragment;
import com.wanandroid.java.ui.login.RegisterLoginActivity;
import com.wanandroid.java.ui.web.ArticleDetailWebViewActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author jere
 */
public class WeChatArticleListFragment extends BaseVmFragment<WeChatArticleViewModel, FragmentWeChatArticleListBinding> {
    public static final String BLOGGER_ID_KEY = "BLOGGER_ID";

    private ArticleListViewAdapter articleListViewAdapter;
    private final ArrayList<Article> weChatArticles = new ArrayList<>();
    private int pageNumber = 1;
    private boolean isLoadAllArticleData = false;
    private int bloggerId;

    public WeChatArticleListFragment() {
        // Required empty public constructor
    }

    public static WeChatArticleListFragment newInstance(int bloggerId) {
        WeChatArticleListFragment fragment = new WeChatArticleListFragment();
        Bundle args = new Bundle();
        args.putInt(BLOGGER_ID_KEY, bloggerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bloggerId = getArguments().getInt(BLOGGER_ID_KEY);
        }
    }

    @Override
    protected void initView() {
        viewModel.setWeChatArticleListLd(pageNumber, bloggerId);
        viewModel.getWeChatArticleDataLd().observe(getViewLifecycleOwner(), weChatArticleDataObserver);

        articleListViewAdapter = new ArticleListViewAdapter(weChatArticles,
                new ArticleListViewAdapter.AdapterItemClickListener() {
                    @Override
                    public void onPositionClicked(View v, int position) {
                        String link = weChatArticles.get(position).getLink();
                        Intent intent = new Intent(getActivity(), ArticleDetailWebViewActivity.class);
                        intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClicked(View v, int position) {

                    }

                    @Override
                    public void unLoginBeforeCollect() {
                        startActivity(new Intent(getActivity(), RegisterLoginActivity.class));
                    }
                });

        dataBinding.weChatArticleListRcy.setAdapter(articleListViewAdapter);
        dataBinding.weChatArticleListRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)
                        && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadAllArticleData) {
                    pageNumber++;
                    viewModel.setWeChatArticleListLd(pageNumber, bloggerId);
                }
            }
        });
    }

    private final Observer<ArticleData> weChatArticleDataObserver = new Observer<ArticleData>() {
        @Override
        public void onChanged(ArticleData articleData) {
            if (articleData != null) {
                isLoadAllArticleData = articleData.isOver();
                articleListViewAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
                weChatArticles.addAll(articleData.getArticles());
                articleListViewAdapter.setData(weChatArticles);
            }
        }
    };
}