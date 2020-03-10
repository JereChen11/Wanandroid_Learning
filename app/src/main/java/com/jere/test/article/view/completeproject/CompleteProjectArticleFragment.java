package com.jere.test.article.view.completeproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.ProjectTreeItem;
import com.jere.test.article.view.ProjectItemListActivity;
import com.jere.test.article.viewmodel.ProjectTreeItemViewModel;
import com.jere.test.home.view.HomeActivity;
import com.jere.test.util.RecyclerItemClickListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author jere
 */
public class CompleteProjectArticleFragment extends Fragment {

    public static final String PROJECT_ITEM_ID_KEY = "PROJECT_ITEM_ID";

    private OnFragmentInteractionListener mListener;


    private ProjectTreeItemAdapter mProjectTreeItemAdapter;
    private ArrayList<ProjectTreeItem.ProjectItem> mProjectItems;
    private RecyclerView mProjectTreeItemRecyclerView;
    private RecyclerView mWeChatBloggerListRecyclerView;


    public CompleteProjectArticleFragment() {
        // Required empty public constructor
    }

    public static CompleteProjectArticleFragment newInstance() {
        return new CompleteProjectArticleFragment();
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

        ProjectTreeItemViewModel projectTreeItemVm = ViewModelProviders.of(this, new ViewModelFactory())
                .get(ProjectTreeItemViewModel.class);
        projectTreeItemVm.getProjectTreeItemsLd().observe(getViewLifecycleOwner(), projectItemsObserver);
        projectTreeItemVm.setProjectTreeItemsLd();

        mProjectTreeItemAdapter = new ProjectTreeItemAdapter(mProjectItems);
        mProjectTreeItemRecyclerView = view.findViewById(R.id.project_tree_items_recycler_view);
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
                mProjectItems = projectItems;
                mProjectTreeItemAdapter = new ProjectTreeItemAdapter(mProjectItems);
                mProjectTreeItemRecyclerView.setAdapter(mProjectTreeItemAdapter);
                mProjectTreeItemAdapter.notifyDataSetChanged();
            }
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    class ViewModelFactory implements ViewModelProvider.Factory {

        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ProjectTreeItemViewModel.class)) {
                return (T) new ProjectTreeItemViewModel();
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
