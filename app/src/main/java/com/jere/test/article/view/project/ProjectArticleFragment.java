package com.jere.test.article.view.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.completeproject.ProjectItemList;
import com.jere.test.article.modle.beanfiles.completeproject.ProjectTreeItem;
import com.jere.test.article.view.ArticleDetailWebViewActivity;
import com.jere.test.article.viewmodel.project.ProjectViewModel;
import com.jere.test.databinding.FragmentProjectBinding;

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
public class ProjectArticleFragment extends Fragment {
    private ArrayList<ProjectTreeItem.ProjectItem> mProjectTreeItems = new ArrayList<>();
    private ArrayList<ProjectItemList.DataBean.DatasBean> mProjectItems = new ArrayList<>();
    private FragmentProjectBinding mBinding;
    private ProjectViewModel mProjectVm;
    private ProjectItemListAdapter mProjectItemListAdapter;
    private int pageNumber = 0;
    private int currentProjectCategoryId = 0;
    private boolean isLoadAllArticleData = false;
    private ProjectVp2Adapter mProjectVp2Adapter;

    public ProjectArticleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentProjectBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProjectVm = new ViewModelProvider(this).get(ProjectViewModel.class);
        mProjectVm.getProjectTreeItemsLd().observe(getViewLifecycleOwner(), projectTreeItemsObserver);
        mProjectVm.setProjectTreeItemsLd();
        mProjectVm.getProjectItemListLd().observe(getViewLifecycleOwner(), projectItemsObserver);

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

        mProjectVp2Adapter = new ProjectVp2Adapter(mProjectItemListAdapter,
                mProjectTreeItems,
                new ProjectVp2Adapter.ScrollListener() {
                    @Override
                    public void isScrollBottom(boolean getBottom) {
                        if (getBottom && !isLoadAllArticleData) {
                            pageNumber++;
                            mProjectVm.setProjectItemListLd(pageNumber, currentProjectCategoryId);
                        }
                    }
                });

        mBinding.projectVp2.setAdapter(mProjectVp2Adapter);
        new TabLayoutMediator(mBinding.projectTabLayout,
                mBinding.projectVp2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(mProjectTreeItems.get(position).getName());
                        mBinding.projectVp2.setCurrentItem(tab.getPosition(), true);
                    }
                }).attach();

        mBinding.projectTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentProjectCategoryId = mProjectTreeItems.get(tab.getPosition()).getId();
                pageNumber = 0;
                mProjectItems.clear();
                mProjectVm.setProjectItemListLd(pageNumber, currentProjectCategoryId);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private Observer<ArrayList<ProjectTreeItem.ProjectItem>> projectTreeItemsObserver =
            new Observer<ArrayList<ProjectTreeItem.ProjectItem>>() {

        @Override
        public void onChanged(@Nullable ArrayList<ProjectTreeItem.ProjectItem> projectTreeItems) {
            if (projectTreeItems != null) {
                mProjectTreeItems.clear();
                mProjectTreeItems.addAll(projectTreeItems);
                mProjectVp2Adapter.setProjectTreeListData(projectTreeItems);
            }
        }
    };

    private Observer<ProjectItemList> projectItemsObserver = new Observer<ProjectItemList>() {
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

    static class ProjectVp2Adapter extends RecyclerView.Adapter<ProjectVp2Adapter.MyViewHolder> {
        private ProjectItemListAdapter projectItemListAdapter;
        private ArrayList<ProjectTreeItem.ProjectItem> projectTreeList;
        private ScrollListener scrollListener;

        interface ScrollListener {
            /**
             * detect the RecyclerView is scroll to bottom.
             *
             * @param getBottom
             */
            void isScrollBottom(boolean getBottom);
        }

        public ProjectVp2Adapter(ProjectItemListAdapter projectItemListAdapter,
                                ArrayList<ProjectTreeItem.ProjectItem> projectTreeList,
                                 ProjectVp2Adapter.ScrollListener scrollListener) {
            this.projectItemListAdapter = projectItemListAdapter;
            this.projectTreeList = projectTreeList;
            this.scrollListener = scrollListener;
        }

        public void setProjectTreeListData(ArrayList<ProjectTreeItem.ProjectItem> projectTreeList) {
            this.projectTreeList = projectTreeList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager2_project_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.projectArticleRcy.setNestedScrollingEnabled(false);
            holder.projectArticleRcy.setAdapter(projectItemListAdapter);

            holder.projectArticleRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        scrollListener.isScrollBottom(true);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return projectTreeList.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView projectArticleRcy;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                projectArticleRcy = itemView.findViewById(R.id.projectArticleListRcy);
            }
        }

    }

}
