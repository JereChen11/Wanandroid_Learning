package com.jere.test.article.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.ProjectTreeItem;
import com.jere.test.article.modle.beanfiles.wechatofficialaccount.WeChatArticleBloggerList;
import com.jere.test.article.viewmodel.ProjectTreeItemViewModel;
import com.jere.test.article.viewmodel.WeChatArticleBloggerListViewModel;
import com.jere.test.home.HomeActivity;
import com.jere.test.util.RecyclerItemClickListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArticleListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArticleListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * @author jere
 */
public class ArticleListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String PROJECT_ITEM_ID_KEY = "PROJECT_ITEM_ID";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private ProjectTreeItemAdapter mProjectTreeItemAdapter;
    private ArrayList<ProjectTreeItem.ProjectItem> mProjectItems;
    private RecyclerView mProjectTreeItemRecyclerView;
    private RecyclerView mWeChatBloggerListRecyclerView;


    public ArticleListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleListFragment newInstance(String param1, String param2) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProjectTreeItemViewModel projectTreeItemVm = ViewModelProviders.of(this, new ViewModelFactory())
                .get(ProjectTreeItemViewModel.class);
        projectTreeItemVm.getProjectTreeItemsLd().observe(this, projectItemsObserver);
        projectTreeItemVm.setProjectTreeItemsLd();

        mProjectTreeItemAdapter = new ProjectTreeItemAdapter(mProjectItems);
        mProjectTreeItemRecyclerView = view.findViewById(R.id.project_tree_items_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mProjectTreeItemRecyclerView.setLayoutManager(mLayoutManager);
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


        WeChatArticleBloggerListViewModel weChatArticleBloggerListVm = ViewModelProviders
                .of(this, new WeChatArticleBloggerListViewModelFactory())
                .get(WeChatArticleBloggerListViewModel.class);
        weChatArticleBloggerListVm.getWeChatArticleBloggerListLd().observe(this, weChatArticleBloggerListObserver);
        weChatArticleBloggerListVm.setWeChatArticleBloggerListLd();

        mWeChatBloggerListRecyclerView = view.findViewById(R.id.wechat_official_account_blogger_list);
        RecyclerView.LayoutManager weChatBloggerRecycleViewManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mWeChatBloggerListRecyclerView.setLayoutManager(weChatBloggerRecycleViewManager);
        WeChatArticleBloggerList weChatArticleBloggerList = weChatArticleBloggerListVm.getWeChatArticleBloggerListLd().getValue();
        if (weChatArticleBloggerList != null) {
            WeChatArticleBloggerListAdapter weChatArticleBloggerListAdapter =
                    new WeChatArticleBloggerListAdapter(weChatArticleBloggerList);
            mWeChatBloggerListRecyclerView.setAdapter(weChatArticleBloggerListAdapter);
        }

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

    private Observer<WeChatArticleBloggerList> weChatArticleBloggerListObserver = new Observer<WeChatArticleBloggerList>() {
        @Override
        public void onChanged(@Nullable WeChatArticleBloggerList weChatArticleBloggerList) {
            if (weChatArticleBloggerList != null) {
                WeChatArticleBloggerListAdapter weChatArticleBloggerListAdapter =
                        new WeChatArticleBloggerListAdapter(weChatArticleBloggerList);
                mWeChatBloggerListRecyclerView.setAdapter(weChatArticleBloggerListAdapter);
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

    class WeChatArticleBloggerListViewModelFactory implements ViewModelProvider.Factory {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(WeChatArticleBloggerListViewModel.class)) {
                return (T) new WeChatArticleBloggerListViewModel();
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
