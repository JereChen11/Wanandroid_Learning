package com.wanandroid.java.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wanandroid.java.R;
import com.wanandroid.java.data.bean.Article;
import com.wanandroid.java.data.repository.CollectRepository;
import com.wanandroid.java.util.Settings;
import com.wanandroid.java.ui.customview.CustomCollectView;
import com.wanandroid.java.ui.customview.PullUpRefreshView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class ArticleListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ARTICLE_TYPE = 0;
    private static final int BOTTOM_PROMPT_TYPE = 1;
    private ArrayList<Article> articleListData;
    private boolean isLoadAllArticleData = false;

    public interface AdapterItemClickListener {
        void onPositionClicked(View v, int position);

        void onLongClicked(View v, int position);

        void unLoginBeforeCollect();
    }

    public AdapterItemClickListener itemClickListener;



    public ArticleListViewAdapter(ArrayList<Article> articleListData,
                                  AdapterItemClickListener listener) {
        this.articleListData = articleListData;
        this.itemClickListener = listener;
    }

    public void setData(ArrayList<Article> articleListData) {
        this.articleListData = articleListData;
        notifyDataSetChanged();
    }

    public void setIsLoadAllArticleData(boolean isLoadAll) {
        this.isLoadAllArticleData = isLoadAll;
//        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == articleListData.size()) {
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
        return new ArticleViewHolder(layoutInflater.inflate(R.layout.recycler_item_view_article_list_item,
                parent,
                false),
                itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ARTICLE_TYPE) {
            final ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
            final Article data = articleListData.get(position);
            articleViewHolder.titleTv.setText(data.getTitle());
            String author;
            if (!TextUtils.isEmpty(data.getShareUser())) {
                author = data.getShareUser();
            } else if (!TextUtils.isEmpty(data.getAuthor())) {
                author = data.getAuthor();
            } else {
                author = "UnKnow";
            }
            articleViewHolder.authorTv.setText(author);
            articleViewHolder.sharedDateTv.setText(data.getNiceShareDate());

            articleViewHolder.collectView.setIsCollect(data.isCollect());
            //这里处理了点击事件，外面点击事件监听中监听不到 点击 collectView 的事件
            articleViewHolder.collectView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int articleId;
                    if (data.getOriginId() != 0) {
                        articleId = data.getOriginId();
                    } else {
                        articleId = data.getId();
                    }
                    v.findViewById(R.id.collectIconView);
                    if (articleViewHolder.collectView.isCollect()) {
                        CollectRepository.newInstance().unCollectArticle(articleId, new CollectRepository.CollectOrUnCollectListener() {
                            @Override
                            public void isSuccessful(boolean isSuccess) {
                                if (isSuccess) {
                                    articleViewHolder.collectView.setIsCollect(false);
                                }
                            }
                        });
                    } else {
                        if (Settings.getInstance().getIsLogin()) {
                            CollectRepository.newInstance().collectArticle(articleId, new CollectRepository.CollectOrUnCollectListener() {
                                @Override
                                public void isSuccessful(boolean isSuccess) {
                                    articleViewHolder.collectView.setIsCollect(true);
                                }
                            });
                        } else {
                            itemClickListener.unLoginBeforeCollect();
                        }
                    }
                }
            });
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
        return articleListData.size() + 1;
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ConstraintLayout containerCl;
        private TextView titleTv;
        private TextView authorTv;
        private TextView sharedDateTv;
        private CustomCollectView collectView;
        private AdapterItemClickListener itemClickListener;

        ArticleViewHolder(View itemView, AdapterItemClickListener itemClickListener) {
            this(itemView);
            this.itemClickListener = itemClickListener;
        }

        ArticleViewHolder(@NonNull View itemView) {
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

    static class BottomPromptViewHolder extends RecyclerView.ViewHolder {
        private PullUpRefreshView pullUpRefreshView;

        public BottomPromptViewHolder(@NonNull View itemView) {
            super(itemView);
            pullUpRefreshView = itemView.findViewById(R.id.articleBottomPullUpRefreshView);
        }
    }
}
