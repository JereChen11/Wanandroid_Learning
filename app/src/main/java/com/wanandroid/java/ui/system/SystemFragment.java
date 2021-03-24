package com.wanandroid.java.ui.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.wanandroid.java.data.bean.SystemCategory;
import com.wanandroid.java.databinding.FragmentSystemBinding;
import com.wanandroid.java.ui.base.BaseVmFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;

/**
 * @author jere
 */
public class SystemFragment extends BaseVmFragment<SystemViewModel, FragmentSystemBinding> {
    private final ArrayList<SystemCategory> systemCategoryList = new ArrayList<>();
    public static final String CHILD_ITEM_ID_KEY = "childItemId";
    public static final String CHILD_ITEM_NAME_KEY = "childItemName";

    private final Observer<List<SystemCategory>> systemCategoryBeanObserver = new Observer<List<SystemCategory>>() {
        @Override
        public void onChanged(List<SystemCategory> systemCategories) {
            if (systemCategories != null) {
                systemCategoryList.addAll(systemCategories);
                SystemListAdapter systemListAdapter =
                        new SystemListAdapter(SystemFragment.this, systemCategoryList);
                dataBinding.expandableListView.setAdapter(systemListAdapter);
            }
        }
    };

    public SystemFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initView() {
        viewModel.getSystemCategoryBeanLd().observe(getViewLifecycleOwner(), systemCategoryBeanObserver);
        viewModel.setSystemCategoryBeanLd();

        dataBinding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
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
