package com.jere.test.article.view.knowledgesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.homearticle.ArticleListBean;
import com.jere.test.article.view.ArticleDetailWebViewActivity;
import com.jere.test.article.view.ArticleListViewAdapter;
import com.jere.test.article.viewmodel.knowledgesystem.KnowledgeSystemViewModel;
import com.jere.test.databinding.ActivityKnowledgeSystemArticleListBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jere
 */
public class KnowledgeSystemArticleListActivity extends AppCompatActivity {
    private ArrayList<ArticleListBean.DataBean.DatasBean> mKnowledgeSystemArticleList;
    private ActivityKnowledgeSystemArticleListBinding mBinding;

    private Observer<ArticleListBean> observer = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                mKnowledgeSystemArticleList = articleListBean.getData().getDatas();

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
                mBinding.knowledgeSystemArticleRecyclerView.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_knowledge_system_article_list);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        int childItemId = bundle.getInt(KnowledgeSystemFragment.CHILD_ITEM_ID_KEY);
        String childItemName = bundle.getString(KnowledgeSystemFragment.CHILD_ITEM_NAME_KEY);

        mBinding.knowledgeSystemTitleBar.setTitle(childItemName);

        KnowledgeSystemViewModel knowledgeSystemVm = new ViewModelProvider(this).get(KnowledgeSystemViewModel.class);
        knowledgeSystemVm.getKnowledgeSystemArticleListBeanLd().observe(this, observer);
        knowledgeSystemVm.setKnowledgeSystemArticleListBeanLd(childItemId);

    }

}
