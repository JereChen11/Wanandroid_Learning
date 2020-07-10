package com.jere.test.article.view.completeproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.completeproject.ProjectTreeItem;
import com.jere.test.article.view.ProjectItemListActivity;
import com.jere.test.article.viewmodel.completeproject.ProjectTreeItemViewModel;
import com.jere.test.home.HomeActivity;
import com.jere.test.util.RecyclerItemClickListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author jere
 */
public class CompleteProjectArticleFragment extends Fragment {
    public static final String PROJECT_ITEM_ID_KEY = "PROJECT_ITEM_ID";
    private ProjectTreeItemAdapter mProjectTreeItemAdapter;
    private ArrayList<ProjectTreeItem.ProjectItem> mProjectItems = new ArrayList<>();
    private RecyclerView mProjectTreeItemRecyclerView;

    public CompleteProjectArticleFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complete_project, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProjectTreeItemViewModel projectTreeItemVm = new ViewModelProvider(this).get(ProjectTreeItemViewModel.class);
        projectTreeItemVm.getProjectTreeItemsLd().observe(getViewLifecycleOwner(), projectItemsObserver);
        projectTreeItemVm.setProjectTreeItemsLd();

        mProjectTreeItemAdapter = new ProjectTreeItemAdapter(mProjectItems);
        mProjectTreeItemRecyclerView = view.findViewById(R.id.projectTreeItemsRcv);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mProjectTreeItemRecyclerView.setLayoutManager(layoutManager);
        mProjectTreeItemRecyclerView.setAdapter(mProjectTreeItemAdapter);

        mProjectTreeItemRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                mProjectTreeItemRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ProjectTreeItem.ProjectItem projectItem = mProjectItems.get(position);

                        Intent intent = new Intent(getActivity(), ProjectItemListActivity.class);
                        intent.putExtra(PROJECT_ITEM_ID_KEY, projectItem.getId());
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        //todo need to handle onLongItemClick event.
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    }
                }));

    }

    private Observer<ArrayList<ProjectTreeItem.ProjectItem>> projectItemsObserver = new Observer<ArrayList<ProjectTreeItem.ProjectItem>>() {

        @Override
        public void onChanged(@Nullable ArrayList<ProjectTreeItem.ProjectItem> projectItems) {
            if (projectItems != null) {
                mProjectItems.addAll(projectItems);
                mProjectTreeItemAdapter.setData(mProjectItems);
            }
        }
    };

}
