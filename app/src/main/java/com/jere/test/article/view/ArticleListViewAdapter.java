package com.jere.test.article.view;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.CollectRepository;
import com.jere.test.article.modle.beanfiles.homearticle.ArticleListBean;
import com.jere.test.util.customcomponent.CustomCollectView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class ArticleListViewAdapter extends RecyclerView.Adapter<ArticleListViewAdapter.MyViewHolder> {
    private ArrayList<ArticleListBean.DataBean.DatasBean> homeArticleListData;

    public ArticleListViewAdapter(ArrayList<ArticleListBean.DataBean.DatasBean> homeArticleListData) {
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ArticleListBean.DataBean.DatasBean data = homeArticleListData.get(position);
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

        holder.collectView.setIsCollect(data.isCollect());
        holder.collectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int articleId;
                if (data.getOriginId() != 0) {
                    articleId = data.getOriginId();
                } else {
                    articleId = data.getId();
                }
                if (holder.collectView.isCollect()) {
                    CollectRepository.newInstance().unCollectArticle(articleId, new CollectRepository.CollectOrUnCollectListener() {
                        @Override
                        public void isSuccessful(boolean isSuccess) {
                            if (isSuccess) {
                                holder.collectView.setIsCollect(false);
                            }
                        }
                    });
                } else {
                    CollectRepository.newInstance().collectArticle(articleId, new CollectRepository.CollectOrUnCollectListener() {
                        @Override
                        public void isSuccessful(boolean isSuccess) {
                            holder.collectView.setIsCollect(true);
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeArticleListData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView authorTv;
        private TextView sharedDateTv;
        private CustomCollectView collectView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.articleListItemTitleTv);
            authorTv = itemView.findViewById(R.id.articleListItemAuthorTv);
            sharedDateTv = itemView.findViewById(R.id.articleListItemSharedDateTv);
            collectView = itemView.findViewById(R.id.collectIconView);
            getAdapterPosition();
        }
    }
}

