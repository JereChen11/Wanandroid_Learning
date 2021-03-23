package com.wanandroid.java.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanandroid.java.data.bean.Article;
import com.wanandroid.java.data.bean.ArticleData;
import com.wanandroid.java.databinding.FragmentProjectArticleListBinding;
import com.wanandroid.java.ui.web.ArticleDetailWebViewActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class ProjectArticleListFragment extends Fragment {

    public static final String PROJECT_ID_KEY = "PROJECT_ID";

    private FragmentProjectArticleListBinding binding;
    private ProjectViewModel viewModel;
    private ProjectItemListAdapter projectItemListAdapter;
    private final ArrayList<Article> articleList = new ArrayList<>();
    private int projectItemId;
    private int pageNumber = 1;
    private boolean isLoadAllArticleData = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            projectItemId = getArguments().getInt(PROJECT_ID_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProjectArticleListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new ProjectVmFactory()).get(ProjectViewModel.class);
        viewModel.setProjectItemListLd(pageNumber, projectItemId);
        viewModel.getProjectArticleDataLd().observe(getViewLifecycleOwner(), projectItemsObserver);

        projectItemListAdapter = new ProjectItemListAdapter(getContext(), articleList,
                (v, position) -> {
                    String link = articleList.get(position).getLink();
                    Intent intent = new Intent(getActivity(), ArticleDetailWebViewActivity.class);
                    intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                    startActivity(intent);
                });
        binding.projectArticleListRcy.setAdapter(projectItemListAdapter);
        binding.projectArticleListRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)
                        && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadAllArticleData) {
                    pageNumber++;
                    viewModel.setProjectItemListLd(pageNumber, projectItemId);
                }
            }
        });

    }

    private final Observer<ArticleData> projectItemsObserver = new Observer<ArticleData>() {
        @Override
        public void onChanged(ArticleData articleData) {
            if (articleData != null) {
                isLoadAllArticleData = articleData.isOver();
                projectItemListAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
                articleList.addAll(articleData.getArticles());
                projectItemListAdapter.setData(articleList);
            }
        }
    };
}
