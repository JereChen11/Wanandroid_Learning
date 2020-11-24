package com.wanandroid.java.article.view.knowledgesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.wanandroid.java.article.modle.beanfiles.knowledgesystem.KnowledgeSystemCategoryBean;
import com.wanandroid.java.article.viewmodel.knowledgesystem.KnowledgeSystemViewModel;
import com.wanandroid.java.databinding.FragmentKnowledgeSystemBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jere
 */
public class KnowledgeSystemFragment extends Fragment {
    private KnowledgeSystemCategoryBean mKnowledgeSystemCategoryBean;
    private FragmentKnowledgeSystemBinding mBinding;
    public static final String CHILD_ITEM_ID_KEY = "childItemId";
    public static final String CHILD_ITEM_NAME_KEY = "childItemName";

    private Observer<KnowledgeSystemCategoryBean> knowledgeSystemCategoryBeanObserver = new Observer<KnowledgeSystemCategoryBean>() {
        @Override
        public void onChanged(KnowledgeSystemCategoryBean knowledgeSystemCategoryBean) {
            if (knowledgeSystemCategoryBean != null) {
                mKnowledgeSystemCategoryBean = knowledgeSystemCategoryBean;
                KnowledgeSystemListAdapter knowledgeSystemListAdapter =
                        new KnowledgeSystemListAdapter(KnowledgeSystemFragment.this, knowledgeSystemCategoryBean);
                mBinding.expandableListView.setAdapter(knowledgeSystemListAdapter);
            }
        }
    };

    public KnowledgeSystemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentKnowledgeSystemBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        KnowledgeSystemViewModel knowledgeSystemVm = new ViewModelProvider(this).get(KnowledgeSystemViewModel.class);
        knowledgeSystemVm.getKnowledgeSystemCategoryBeanLd().observe(getViewLifecycleOwner(), knowledgeSystemCategoryBeanObserver);
        knowledgeSystemVm.setKnowledgeSystemCategoryBeanLd();

        mBinding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
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
                bundle.putInt(CHILD_ITEM_ID_KEY, childItemId);
                bundle.putString(CHILD_ITEM_NAME_KEY, childItemName);
                intent.putExtras(bundle);
                startActivity(intent);

                return false;
            }
        });
    }
}
