package com.wanandroid.java.ui.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.wanandroid.java.data.bean.ProjectType;
import com.wanandroid.java.databinding.FragmentProjectBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;


/**
 * @author jere
 */
public class ProjectTypeFragment extends Fragment {
    private final ArrayList<ProjectType> mProjectTreeItems = new ArrayList<>();
    private FragmentProjectBinding mBinding;

    public ProjectTypeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentProjectBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProjectViewModel viewModel = new ViewModelProvider(this, new ProjectVmFactory()).get(ProjectViewModel.class);
        viewModel.getProjectTypeDataLd().observe(getViewLifecycleOwner(), projectTreeItemsObserver);
        viewModel.setProjectTreeItemsLd();

    }

    private void initTabLayoutAndVp2() {
        new TabLayoutMediator(mBinding.projectTabLayout,
                mBinding.projectVp2,
                (tab, position) -> tab.setText(mProjectTreeItems.get(position).getName())).attach();
    }

    private final Observer<List<ProjectType>> projectTreeItemsObserver =
            projectTypes -> {
                if (projectTypes != null) {
                    mProjectTreeItems.clear();
                    mProjectTreeItems.addAll(projectTypes);

                    mBinding.projectVp2.setAdapter(new ProjectTypeVp2Adapter(this, mProjectTreeItems));
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
