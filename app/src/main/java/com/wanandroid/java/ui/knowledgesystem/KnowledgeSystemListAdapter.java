package com.wanandroid.java.ui.knowledgesystem;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanandroid.java.R;
import com.wanandroid.java.data.bean.KnowledgeSystemCategoryBean;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author jere
 */
public class KnowledgeSystemListAdapter extends BaseExpandableListAdapter {
    private WeakReference<KnowledgeSystemFragment> weakReference;
    private ArrayList<KnowledgeSystemCategoryBean.DataBean> mGroupDataList;

    KnowledgeSystemListAdapter(KnowledgeSystemFragment fragment,
                               KnowledgeSystemCategoryBean knowledgeSystemCategoryBean) {
        this.weakReference = new WeakReference<>(fragment);
        this.mGroupDataList = knowledgeSystemCategoryBean.getData();
    }

    @Override
    public int getGroupCount() {
        return mGroupDataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroupDataList.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupDataList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupDataList.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(weakReference.get().getContext())
                    .inflate(R.layout.knowledge_system_group_item_view, parent, false);
        }

        TextView groupTitleTv = convertView.findViewById(R.id.groupTitleTv);
        ImageView indicateArrow = convertView.findViewById(R.id.indicateArrow);

        String groupTitleString = mGroupDataList.get(groupPosition).getName();
        if (!TextUtils.isEmpty(groupTitleString)) {
            groupTitleTv.setText(groupTitleString);
        }
        if (isExpanded) {
            indicateArrow.setImageResource(R.drawable.vector_drawable_arrow_upon);
        } else {
            indicateArrow.setImageResource(R.drawable.vector_drawable_arrow_down);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(weakReference.get().getContext())
                    .inflate(R.layout.knowledge_system_child_item_view, parent, false);
        }
        TextView childTitleTv = convertView.findViewById(R.id.childTitleTv);
        String childTitleString = mGroupDataList.get(groupPosition).getChildren().get(childPosition).getName();
        if (!TextUtils.isEmpty(childTitleString)) {
            childTitleTv.setText(childTitleString);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
