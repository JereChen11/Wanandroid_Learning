package com.jere.test.article.view.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.jere.test.article.modle.beanfiles.homearticle.ArticleListBean;
import com.jere.test.article.modle.beanfiles.wechat.WeChatArticleBloggerList;
import com.jere.test.article.view.ArticleDetailWebViewActivity;
import com.jere.test.article.view.ArticleListViewAdapter;
import com.jere.test.article.viewmodel.wechat.WeChatBlogArticleViewModel;
import com.jere.test.databinding.FragmentWechatBlogArticleBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


/**
 * @author jere
 */
public class WeChatBlogArticleFragment extends Fragment {
    private ArrayList<WeChatArticleBloggerList.DataBean> mWeChatBloggerList = new ArrayList<>();
    private WeChatBlogArticleViewModel mWeChatBlogArticleVm;
    private ArrayList<ArticleListBean.DataBean.DatasBean> mWeChatArticleListData = new ArrayList<>();
    private FragmentWechatBlogArticleBinding mBinding;
    private int pageNumber = 0;
    private int currentWeChatBloggerId = 0;

    private Observer<WeChatArticleBloggerList> weChatArticleBloggerListObserver = new Observer<WeChatArticleBloggerList>() {
        @Override
        public void onChanged(@Nullable WeChatArticleBloggerList weChatArticleBloggerList) {
            if (weChatArticleBloggerList != null) {
                mWeChatBloggerList = weChatArticleBloggerList.getData();
                for (WeChatArticleBloggerList.DataBean dataBean : mWeChatBloggerList) {
                    mBinding.weChatBloggerTabLayout.addTab(mBinding.weChatBloggerTabLayout.newTab().setText(dataBean.getName()));
                }
            }
        }
    };
    private Observer<ArticleListBean> weChatArticleListObserver = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                mWeChatArticleListData.addAll(articleListBean.getData().getDatas());
                ArticleListViewAdapter adapter = new ArticleListViewAdapter(mWeChatArticleListData,
                        new ArticleListViewAdapter.AdapterItemClickListener() {
                            @Override
                            public void onPositionClicked(View v, int position) {
                                String link = mWeChatArticleListData.get(position).getLink();
                                Intent intent = new Intent(getActivity(), ArticleDetailWebViewActivity.class);
                                intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongClicked(View v, int position) {

                            }
                        });
                mBinding.weChatArticleListRecyclerView.setAdapter(adapter);
            }
        }
    };

    public WeChatBlogArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentWechatBlogArticleBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWeChatBlogArticleVm = new ViewModelProvider(this).get(WeChatBlogArticleViewModel.class);
        mWeChatBlogArticleVm.getWeChatArticleBloggerListLd().observe(getViewLifecycleOwner(), weChatArticleBloggerListObserver);
        mWeChatBlogArticleVm.setWeChatArticleBloggerListLd();
        mWeChatBlogArticleVm.getWeChatArticleListLd().observe(getViewLifecycleOwner(), weChatArticleListObserver);

        mBinding.weChatBloggerTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentWeChatBloggerId = mWeChatBloggerList.get(tab.getPosition()).getId();
                mWeChatArticleListData.clear();
                mWeChatBlogArticleVm.setWeChatArticleListLd(currentWeChatBloggerId, pageNumber);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mBinding.weChatNsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (!v.canScrollVertically(1)) {
                    pageNumber++;
                    mWeChatBlogArticleVm.setWeChatArticleListLd(currentWeChatBloggerId, pageNumber);
                }
            }
        });

    }
}
