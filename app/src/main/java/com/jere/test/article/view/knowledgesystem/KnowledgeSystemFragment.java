package com.jere.test.article.view.knowledgesystem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.knowledgesystem.KnowledgeSystemCategoryBean;
import com.jere.test.article.viewmodel.knowledgesystem.KnowledgeSystemViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author jere
 */
public class KnowledgeSystemFragment extends Fragment {

    private ExpandableListView mExpandableListView;
    private KnowledgeSystemCategoryBean mKnowledgeSystemCategoryBean;

    private OnFragmentInteractionListener mListener;
    private Observer<KnowledgeSystemCategoryBean> knowledgeSystemCategoryBeanObserver = new Observer<KnowledgeSystemCategoryBean>() {
        @Override
        public void onChanged(KnowledgeSystemCategoryBean knowledgeSystemCategoryBean) {
            if (knowledgeSystemCategoryBean != null) {
                mKnowledgeSystemCategoryBean = knowledgeSystemCategoryBean;
                KnowledgeSystemListAdapter knowledgeSystemListAdapter =
                        new KnowledgeSystemListAdapter(KnowledgeSystemFragment.this, knowledgeSystemCategoryBean);
                mExpandableListView.setAdapter(knowledgeSystemListAdapter);
            }
        }
    };

    public KnowledgeSystemFragment() {
        // Required empty public constructor
    }

    public static KnowledgeSystemFragment newInstance() {
        return new KnowledgeSystemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_knowledge_system, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        KnowledgeSystemViewModel knowledgeSystemVm = ViewModelProviders
                .of(this, new ViewModelFactory()).get(KnowledgeSystemViewModel.class);
        knowledgeSystemVm.getKnowledgeSystemCategoryBeanLd().observe(getViewLifecycleOwner(), knowledgeSystemCategoryBeanObserver);
        knowledgeSystemVm.setKnowledgeSystemCategoryBeanLd();

        mExpandableListView = view.findViewById(R.id.expandableListView);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                KnowledgeSystemCategoryBean.DataBean.ChildrenBean childData = mKnowledgeSystemCategoryBean.getData()
                        .get(groupPosition)
                        .getChildren()
                        .get(childPosition);

                int childItemId = childData.getId();
                String childItemName = childData.getName();

                Intent intent = new Intent(getActivity(), KnowledgeSystemArticleListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("childItemId", childItemId);
                bundle.putString("childItemName", childItemName);
                intent.putExtras(bundle);
                startActivity(intent);


                return false;
            }
        });
    }

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

    class ViewModelFactory implements ViewModelProvider.Factory {

        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(KnowledgeSystemViewModel.class)) {
                return (T) new KnowledgeSystemViewModel();
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
