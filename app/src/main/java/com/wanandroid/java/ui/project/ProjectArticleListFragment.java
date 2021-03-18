package com.wanandroid.java.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanandroid.java.data.bean.ProjectItemList;
import com.wanandroid.java.databinding.FragmentProjectArticleListBinding;
import com.wanandroid.java.ui.ArticleDetailWebViewActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jere
 */
public class ProjectArticleListFragment extends Fragment {

    public static final String PROJECT_ID_KEY = "PROJECT_ID";

    private FragmentProjectArticleListBinding binding;
    private ProjectViewModel viewModel;
    private ProjectItemListAdapter mProjectItemListAdapter;
    private final ArrayList<ProjectItemList.DataBean.DatasBean> mProjectItems = new ArrayList<>();
    private int pageNumber = 0;
    private boolean isLoadAllArticleData = false;
    private int projectId = 0;

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

        if (getArguments() != null) {
            viewModel.setProjectItemListLd(pageNumber, getArguments().getInt(PROJECT_ID_KEY));
        }

        viewModel.getProjectItemListLd().observe(getViewLifecycleOwner(), projectItemsObserver);

        mProjectItemListAdapter = new ProjectItemListAdapter(getContext(), mProjectItems,
                new ProjectItemListAdapter.AdapterItemClickListener() {
                    @Override
                    public void onPositionClicked(View v, int position) {
                        String link = mProjectItems.get(position).getLink();
                        Intent intent = new Intent(getActivity(), ArticleDetailWebViewActivity.class);
                        intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                        startActivity(intent);
                    }
                });
        binding.projectArticleListRcy.setAdapter(mProjectItemListAdapter);

    }

    private final Observer<ProjectItemList> projectItemsObserver = new Observer<ProjectItemList>() {
        @Override
        public void onChanged(ProjectItemList projectItemsData) {
            if (projectItemsData != null) {
                isLoadAllArticleData = projectItemsData.getData().isOver();
                mProjectItemListAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
                mProjectItems.addAll(projectItemsData.getData().getDatas());
                ArrayList<ProjectItemList.DataBean.DatasBean> newProjectItemList = new ArrayList<>(mProjectItems);
                mProjectItemListAdapter.setData(newProjectItemList);
            }
        }
    };
}
