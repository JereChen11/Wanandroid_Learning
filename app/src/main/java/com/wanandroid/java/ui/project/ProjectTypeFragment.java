package com.wanandroid.java.ui.project;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayoutMediator;
import com.wanandroid.java.data.bean.ProjectType;
import com.wanandroid.java.databinding.FragmentProjectBinding;
import com.wanandroid.java.ui.base.BaseVmFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.adapter.FragmentStateAdapter;


/**
 * @author jere
 */
public class ProjectTypeFragment extends BaseVmFragment<ProjectViewModel, FragmentProjectBinding> {
    private final ArrayList<ProjectType> mProjectTreeItems = new ArrayList<>();

    public ProjectTypeFragment() {
    }

    @Override
    protected void initView() {
        viewModel.getProjectTypeDataLd().observe(getViewLifecycleOwner(), projectTreeItemsObserver);
        viewModel.setProjectTreeItemsLd();
    }

    private void initTabLayoutAndVp2() {
        new TabLayoutMediator(dataBinding.projectTabLayout,
                dataBinding.projectVp2,
                (tab, position) -> tab.setText(mProjectTreeItems.get(position).getName())).attach();
    }

    private final Observer<List<ProjectType>> projectTreeItemsObserver =
            projectTypes -> {
                if (projectTypes != null) {
                    mProjectTreeItems.clear();
                    mProjectTreeItems.addAll(projectTypes);

                    dataBinding.projectVp2.setAdapter(new ProjectTypeVp2Adapter(this, mProjectTreeItems));
                    initTabLayoutAndVp2();
                }
            };

    static class ProjectTypeVp2Adapter extends FragmentStateAdapter {
        private final ArrayList<ProjectType> projectTypes;

        public ProjectTypeVp2Adapter(@NonNull Fragment fragment, ArrayList<ProjectType> projectTypes) {
            super(fragment);
            this.projectTypes = projectTypes;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = new ProjectArticleListFragment();
            Bundle args = new Bundle();
            args.putInt(ProjectArticleListFragment.PROJECT_ID_KEY, projectTypes.get(position).getId());
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return projectTypes.size();
        }
    }


}
