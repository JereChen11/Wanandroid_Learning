package com.jere.test.article.view.completeproject;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.completeproject.ProjectTreeItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class ProjectTreeItemAdapter extends RecyclerView.Adapter<ProjectTreeItemAdapter.MyViewHolder> {
    private ArrayList<ProjectTreeItem.ProjectItem> projectItems;

    ProjectTreeItemAdapter(ArrayList<ProjectTreeItem.ProjectItem> arrayList) {
        this.projectItems = arrayList;
    }

    public void setData(ArrayList<ProjectTreeItem.ProjectItem> arrayList) {
        this.projectItems = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycley_list_item_view_project_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (projectItems.size() > 0) {
            ProjectTreeItem.ProjectItem projectItem = projectItems.get(i);
            if (!TextUtils.isEmpty(projectItem.getName())) {
                myViewHolder.projectItemNameTv.setText(projectItem.getName());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (projectItems != null) {
            return projectItems.size();
        }
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView projectItemNameTv;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            projectItemNameTv = itemView.findViewById(R.id.project_item_name_tv);
        }
    }
}