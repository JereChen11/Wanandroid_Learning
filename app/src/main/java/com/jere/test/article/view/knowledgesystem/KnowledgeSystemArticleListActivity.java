package com.jere.test.article.view.knowledgesystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.knowledgesystem.KnowledgeSystemArticleListBean;
import com.jere.test.article.view.ArticleDetailWebViewActivity;
import com.jere.test.article.viewmodel.KnowledgeSystemViewModel;
import com.jere.test.util.RecyclerItemClickListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class KnowledgeSystemArticleListActivity extends AppCompatActivity {
    private RecyclerView mKnowledgeSystemArticleRecyclerView;
    private ArrayList<KnowledgeSystemArticleListBean.DataBean.DatasBean> mKnowledgeSystemArticleList;

    private Observer<KnowledgeSystemArticleListBean> observer = new Observer<KnowledgeSystemArticleListBean>() {
        @Override
        public void onChanged(KnowledgeSystemArticleListBean knowledgeSystemArticleListBean) {
            if (knowledgeSystemArticleListBean != null) {
                mKnowledgeSystemArticleList = knowledgeSystemArticleListBean.getData().getDatas();
                MyAdapter adapter = new MyAdapter(mKnowledgeSystemArticleList);
                mKnowledgeSystemArticleRecyclerView.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_system_article_list);

        Bundle bundle = getIntent().getExtras();
        int childItemId = bundle.getInt("childItemId");
        String childItemName = bundle.getString("childItemName");

        TextView titleTv = findViewById(R.id.knowledgeSystemArticleListTitleTv);
        titleTv.setText(childItemName);

        KnowledgeSystemViewModel knowledgeSystemVm = ViewModelProviders
                .of(this, new ViewModelFactory()).get(KnowledgeSystemViewModel.class);
        knowledgeSystemVm.getKnowledgeSystemArticleListBeanLd().observe(this, observer);
        knowledgeSystemVm.setKnowledgeSystemArticleListBeanLd(childItemId);

        mKnowledgeSystemArticleRecyclerView = findViewById(R.id.knowledgeSystemArticleRecyclerView);
        mKnowledgeSystemArticleRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                mKnowledgeSystemArticleRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        KnowledgeSystemArticleListBean.DataBean.DatasBean data = mKnowledgeSystemArticleList.get(position);
                        String link = data.getLink();
                        Intent intent = new Intent(KnowledgeSystemArticleListActivity.this, ArticleDetailWebViewActivity.class);
                        intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));
    }

    class ViewModelFactory implements ViewModelProvider.Factory {

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(KnowledgeSystemViewModel.class)) {
                return (T) new KnowledgeSystemViewModel();
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ArrayList<KnowledgeSystemArticleListBean.DataBean.DatasBean> mDataList;

        MyAdapter(ArrayList<KnowledgeSystemArticleListBean.DataBean.DatasBean> dataList) {
            this.mDataList = dataList;
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
            KnowledgeSystemArticleListBean.DataBean.DatasBean data = mDataList.get(position);
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
            return mDataList.size();
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
}
