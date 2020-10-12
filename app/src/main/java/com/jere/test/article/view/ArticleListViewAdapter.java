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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class ArticleListViewAdapter extends RecyclerView.Adapter<ArticleListViewAdapter.MyViewHolder> {


    public interface AdapterItemClickListener {
        void onPositionClicked(View v, int position);

        void onLongClicked(View v, int position);
    }

    public AdapterItemClickListener itemClickListener;


    private ArrayList<ArticleListBean.DataBean.DatasBean> homeArticleListData;

    public ArticleListViewAdapter(ArrayList<ArticleListBean.DataBean.DatasBean> homeArticleListData,
                                  AdapterItemClickListener listener) {
        this.homeArticleListData = homeArticleListData;
        this.itemClickListener = listener;
    }

    public void setData(ArrayList<ArticleListBean.DataBean.DatasBean> homeArticleListData) {
        this.homeArticleListData = homeArticleListData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_view_article_list_item, parent, false);
        return new MyViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final ArticleListBean.DataBean.DatasBean data = homeArticleListData.get(position);
        holder.titleTv.setText(data.getTitle());
        String author;
        if (!TextUtils.isEmpty(data.getShareUser())) {
            author = data.getShareUser();
        } else if (TextUtils.isEmpty(data.getAuthor())) {
            author = data.getAuthor();
        } else {
            author = "UnKnow";
        }
        holder.authorTv.setText(author);
        holder.sharedDateTv.setText(data.getNiceShareDate());

        holder.collectView.setIsCollect(data.isCollect());
        //这里处理了点击事件，外面点击事件监听中监听不到 点击 collectView 的事件
        holder.collectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int articleId;
                if (data.getOriginId() != 0) {
                    articleId = data.getOriginId();
                } else {
                    articleId = data.getId();
                }
                v.findViewById(R.id.collectIconView);
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

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ConstraintLayout containerCl;
        private TextView titleTv;
        private TextView authorTv;
        private TextView sharedDateTv;
        private CustomCollectView collectView;
        private AdapterItemClickListener itemClickListener;

        MyViewHolder(View itemView, AdapterItemClickListener itemClickListener) {
            this(itemView);
            this.itemClickListener = itemClickListener;
        }

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            containerCl = itemView.findViewById(R.id.rcyItemArticleListContainerCl);
            titleTv = itemView.findViewById(R.id.articleListItemTitleTv);
            authorTv = itemView.findViewById(R.id.articleListItemAuthorTv);
            sharedDateTv = itemView.findViewById(R.id.articleListItemSharedDateTv);
            collectView = itemView.findViewById(R.id.collectIconView);

            containerCl.setOnClickListener(this);
            titleTv.setOnClickListener(this);
            authorTv.setOnClickListener(this);
            sharedDateTv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onPositionClicked(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onLongClicked(v, getAdapterPosition());
            return true;
        }
    }
}
