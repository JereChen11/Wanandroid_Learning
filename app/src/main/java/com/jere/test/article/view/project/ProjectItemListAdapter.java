package com.jere.test.article.view.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.completeproject.ProjectItemList;
import com.jere.test.util.customcomponent.PullUpRefreshView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class ProjectItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ARTICLE_TYPE = 0;
    private static final int BOTTOM_PROMPT_TYPE = 1;
    private ArrayList<ProjectItemList.DataBean.DatasBean> dataList;
    private WeakReference<Context> weakReference;
    private boolean isLoadAllArticleData = false;

    public interface AdapterItemClickListener {
        void onPositionClicked(View v, int position);
    }

    public AdapterItemClickListener itemClickListener;

    ProjectItemListAdapter(Context context,
                           ArrayList<ProjectItemList.DataBean.DatasBean> projectItemList,
                           AdapterItemClickListener adapterItemClickListener) {
        weakReference = new WeakReference<>(context);
        this.dataList = projectItemList;
        this.itemClickListener = adapterItemClickListener;
    }

    public void setData(ArrayList<ProjectItemList.DataBean.DatasBean> projectItemList) {
        this.dataList = projectItemList;
        notifyDataSetChanged();
    }

    public void setIsLoadAllArticleData(boolean isLoadAll) {
        this.isLoadAllArticleData = isLoadAll;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == dataList.size()) {
            return BOTTOM_PROMPT_TYPE;
        }
        return ARTICLE_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == BOTTOM_PROMPT_TYPE) {
            return new BottomPromptViewHolder(
                    layoutInflater.inflate(R.layout.recycler_list_item_view_article_bottom_prompt_view,
                            parent,
                            false));
        }
        return new ProjectArticleViewHolder(layoutInflater.inflate(R.layout.recycle_list_item_view_project_item_list,
                parent,
                false),
                itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ARTICLE_TYPE) {
            ProjectArticleViewHolder projectArticleViewHolder = (ProjectArticleViewHolder) holder;
            ProjectItemList.DataBean.DatasBean item = dataList.get(position);
            if (weakReference != null && weakReference.get() != null) {
                Glide.with(weakReference.get())
                        .load(item.getEnvelopePic())
                        .into(projectArticleViewHolder.envelopIv);
                projectArticleViewHolder.titleTv.setText(item.getTitle());
                projectArticleViewHolder.describeContentTv.setText(item.getDesc());
                projectArticleViewHolder.shareDateTv.setText(item.getNiceShareDate());
                projectArticleViewHolder.authorTv.setText(item.getAuthor());
            }
        } else {
            BottomPromptViewHolder bottomPromptViewHolder = (BottomPromptViewHolder) holder;
            if (isLoadAllArticleData) {
                bottomPromptViewHolder.pullUpRefreshView.setProgressBarStatus(View.GONE);
                bottomPromptViewHolder.pullUpRefreshView.setPromptTv("所有文章都已被加载");
            } else {
                bottomPromptViewHolder.pullUpRefreshView.setProgressBarStatus(View.VISIBLE);
                bottomPromptViewHolder.pullUpRefreshView.setPromptTv("加载中");
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + 1;
    }

    static class ProjectArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView envelopIv;
        private TextView titleTv;
        private TextView describeContentTv;
        private TextView shareDateTv;
        private TextView authorTv;
        private AdapterItemClickListener adapterItemClickListener;

        ProjectArticleViewHolder(@NonNull View itemView, AdapterItemClickListener adapterItemClickListener) {
            super(itemView);
            envelopIv = itemView.findViewById(R.id.projectItemListEnvelopIv);
            titleTv = itemView.findViewById(R.id.projectItemListTitleTv);
            describeContentTv = itemView.findViewById(R.id.projectItemListDescribeContentTv);
            shareDateTv = itemView.findViewById(R.id.projectItemListShareDateTv);
            authorTv = itemView.findViewById(R.id.projectItemListAuthorTv);

            this.adapterItemClickListener = adapterItemClickListener;
        }

        @Override
        public void onClick(View v) {
            adapterItemClickListener.onPositionClicked(v, getAdapterPosition());
        }
    }

    static class BottomPromptViewHolder extends RecyclerView.ViewHolder {
        private PullUpRefreshView pullUpRefreshView;

        public BottomPromptViewHolder(@NonNull View itemView) {
            super(itemView);
            pullUpRefreshView = itemView.findViewById(R.id.articleBottomPullUpRefreshView);
        }
    }

}
