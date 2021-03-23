package com.wanandroid.java.ui.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.wanandroid.java.data.bean.SystemCategory;
import com.wanandroid.java.databinding.FragmentSystemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jere
 */
public class SystemFragment extends Fragment {
    private final ArrayList<SystemCategory> systemCategoryList = new ArrayList<>();
    private FragmentSystemBinding mBinding;
    public static final String CHILD_ITEM_ID_KEY = "childItemId";
    public static final String CHILD_ITEM_NAME_KEY = "childItemName";

    private final Observer<List<SystemCategory>> systemCategoryBeanObserver = new Observer<List<SystemCategory>>() {
        @Override
        public void onChanged(List<SystemCategory> systemCategories) {
            if (systemCategories != null) {
                systemCategoryList.addAll(systemCategories);
                SystemListAdapter systemListAdapter =
                        new SystemListAdapter(SystemFragment.this, systemCategoryList);
                mBinding.expandableListView.setAdapter(systemListAdapter);
            }
        }
    };

    public SystemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentSystemBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SystemViewModel knowledgeSystemVm = new ViewModelProvider(this).get(SystemViewModel.class);
        knowledgeSystemVm.getSystemCategoryBeanLd().observe(getViewLifecycleOwner(), systemCategoryBeanObserver);
        knowledgeSystemVm.setSystemCategoryBeanLd();

        mBinding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                SystemCategory.ChildrenBean childData = systemCategoryList.get(groupPosition)
                        .getChildren()
                        .get(childPosition);

                int childItemId = childData.getId();
                String childItemName = childData.getName();

                Intent intent = new Intent(getActivity(), SystemArticleListActivity.class);
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
