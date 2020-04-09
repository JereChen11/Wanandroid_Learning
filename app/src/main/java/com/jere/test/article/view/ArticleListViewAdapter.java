package com.jere.test.article.view;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.homearticle.HomeArticleListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleListViewAdapter extends RecyclerView.Adapter<ArticleListViewAdapter.MyViewHolder> {
    private List<HomeArticleListBean.DataBean.DatasBean> homeArticleListData;

    ArticleListViewAdapter(List<HomeArticleListBean.DataBean.DatasBean> homeArticleListData) {
        this.homeArticleListData = homeArticleListData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_view_article_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeArticleListBean.DataBean.DatasBean data = homeArticleListData.get(position);
        holder.titleTv.setText(data.getTitle());
        String author;
        if (!TextUtils.isEmpty(data.getAuthor())) {
            author = data.getAuthor();
        } else if (TextUtils.isEmpty(data.getShareUser())) {
            author = data.getShareUser();
        } else {
            author = "Robot";
        }
        holder.authorTv.setText(author);
        holder.sharedDateTv.setText(data.getNiceShareDate());
    }

    @Override
    public int getItemCount() {
        return homeArticleListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView authorTv;
        private TextView sharedDateTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.articleListItemTitleTv);
            authorTv = itemView.findViewById(R.id.articleListItemAuthorTv);
            sharedDateTv = itemView.findViewById(R.id.articleListItemSharedDateTv);
        }
    }
}

