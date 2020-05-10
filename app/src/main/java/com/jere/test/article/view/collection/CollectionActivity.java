package com.jere.test.article.view.collection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.homearticle.ArticleListBean;
import com.jere.test.article.view.ArticleDetailWebViewActivity;
import com.jere.test.article.view.ArticleListViewAdapter;
import com.jere.test.article.viewmodel.collection.CollectionViewModel;
import com.jere.test.databinding.ActivityCollectionBinding;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jere
 */
public class CollectionActivity extends AppCompatActivity {
    private ActivityCollectionBinding mBinding;
    private ArrayList<ArticleListBean.DataBean.DatasBean> mCollectionArticleListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_collection);

        CollectionViewModel collectionVm = new ViewModelProvider(this).get(CollectionViewModel.class);
        collectionVm.getCollectionArticleListBeanLd().observe(this, articleListBeanObserver);
        collectionVm.getCollectionArticleList();

    }

    private Observer<ArticleListBean> articleListBeanObserver = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                mCollectionArticleListData = articleListBean.getData().getDatas();
                for (ArticleListBean.DataBean.DatasBean data : mCollectionArticleListData) {
                    data.setCollect(true);
                }
                ArticleListViewAdapter adapter = new ArticleListViewAdapter(mCollectionArticleListData,
                        new ArticleListViewAdapter.AdapterItemClickListener() {
                            @Override
                            public void onPositionClicked(View v, int position) {
                                String link = mCollectionArticleListData.get(position).getLink();
                                Intent intent = new Intent(CollectionActivity.this, ArticleDetailWebViewActivity.class);
                                intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongClicked(View v, int position) {

                            }
                        });
                mBinding.collectionFolderRcy.setAdapter(adapter);
            }
        }
    };

}
