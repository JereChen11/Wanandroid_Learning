package com.jere.test.article.view.knowledgesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.homearticle.ArticleListBean;
import com.jere.test.article.view.ArticleDetailWebViewActivity;
import com.jere.test.article.view.ArticleListViewAdapter;
import com.jere.test.article.viewmodel.knowledgesystem.KnowledgeSystemViewModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author jere
 */
public class KnowledgeSystemArticleListActivity extends AppCompatActivity {
    private RecyclerView mKnowledgeSystemArticleRcy;
    private ArrayList<ArticleListBean.DataBean.DatasBean> mKnowledgeSystemArticleList;

    private Observer<ArticleListBean> observer = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                mKnowledgeSystemArticleList = articleListBean.getData().getDatas();
//                ArticleListViewAdapter adapter = new ArticleListViewAdapter(mKnowledgeSystemArticleList);
//                mKnowledgeSystemArticleRcy.setAdapter(adapter);

                ArticleListViewAdapter adapter = new ArticleListViewAdapter(mKnowledgeSystemArticleList,
                        new ArticleListViewAdapter.AdapterItemClickListener() {
                            @Override
                            public void onPositionClicked(View v, int position) {
                                String link = mKnowledgeSystemArticleList.get(position).getLink();
                                Intent intent = new Intent(KnowledgeSystemArticleListActivity.this, ArticleDetailWebViewActivity.class);
                                intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongClicked(View v, int position) {

                            }
                        });
                mKnowledgeSystemArticleRcy.setAdapter(adapter);
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

        KnowledgeSystemViewModel knowledgeSystemVm = new ViewModelProvider(this).get(KnowledgeSystemViewModel.class);
        knowledgeSystemVm.getKnowledgeSystemArticleListBeanLd().observe(this, observer);
        knowledgeSystemVm.setKnowledgeSystemArticleListBeanLd(childItemId);

        mKnowledgeSystemArticleRcy = findViewById(R.id.knowledgeSystemArticleRecyclerView);
//        mKnowledgeSystemArticleRcy.addOnItemTouchListener(new RecyclerItemClickListener(this,
//                mKnowledgeSystemArticleRcy,
//                new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        ArticleListBean.DataBean.DatasBean data = mKnowledgeSystemArticleList.get(position);
//                        String link = data.getLink();
//                        Intent intent = new Intent(KnowledgeSystemArticleListActivity.this, ArticleDetailWebViewActivity.class);
//                        intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void onLongItemClick(View view, int position) {
//
//                    }
//                }));
    }

}
