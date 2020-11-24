package com.wanandroid.java.article.view.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wanandroid.java.R;
import com.wanandroid.java.article.modle.beanfiles.homearticle.ArticleListBean;
import com.wanandroid.java.article.modle.beanfiles.wechat.WeChatArticleBloggerList;
import com.wanandroid.java.article.view.ArticleDetailWebViewActivity;
import com.wanandroid.java.article.view.ArticleListViewAdapter;
import com.wanandroid.java.article.viewmodel.wechat.WeChatBlogArticleViewModel;
import com.wanandroid.java.databinding.FragmentWechatBlogArticleBinding;
import com.wanandroid.java.login.view.RegisterLoginActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


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
    private boolean isLoadAllArticleData = false;
    private WeChatVp2Adapter weChatVp2Adapter;
    private ArticleListViewAdapter articleListViewAdapter;

    private Observer<WeChatArticleBloggerList> weChatArticleBloggerListObserver = new Observer<WeChatArticleBloggerList>() {
        @Override
        public void onChanged(@Nullable WeChatArticleBloggerList weChatArticleBloggerList) {
            if (weChatArticleBloggerList != null) {
                mWeChatBloggerList.clear();
                mWeChatBloggerList.addAll(weChatArticleBloggerList.getData());
                weChatVp2Adapter.setBloggerListData(mWeChatBloggerList);
            }
        }
    };

    private Observer<ArticleListBean> weChatArticleListObserver = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                isLoadAllArticleData = articleListBean.getData().isOver();
                articleListViewAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
                mWeChatArticleListData.addAll(articleListBean.getData().getDatas());
                ArrayList<ArticleListBean.DataBean.DatasBean> newArticleList = new ArrayList<>(mWeChatArticleListData);
                articleListViewAdapter.setData(newArticleList);
            }
        }
    };

    public WeChatBlogArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
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

        articleListViewAdapter = new ArticleListViewAdapter(mWeChatArticleListData,
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

                    @Override
                    public void unLoginBeforeCollect() {
                        startActivity(new Intent(getActivity(), RegisterLoginActivity.class));
                    }
                });

        weChatVp2Adapter = new WeChatVp2Adapter(articleListViewAdapter,
                mWeChatBloggerList,
                new WeChatVp2Adapter.ScrollListener() {
                    @Override
                    public void isScrollBottom(boolean getBottom) {
                        if (getBottom && !isLoadAllArticleData) {
                            pageNumber++;
                            mWeChatBlogArticleVm.setWeChatArticleListLd(currentWeChatBloggerId, pageNumber);
                        }
                    }
                });

        mBinding.weChatVp2.setAdapter(weChatVp2Adapter);
        new TabLayoutMediator(mBinding.weChatBloggerTabLayout,
                mBinding.weChatVp2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(mWeChatBloggerList.get(position).getName());
                        mBinding.weChatVp2.setCurrentItem(tab.getPosition(), true);
                    }
                }).attach();


        mBinding.weChatBloggerTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentWeChatBloggerId = mWeChatBloggerList.get(tab.getPosition()).getId();
                pageNumber = 0;
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


    }

    static class WeChatVp2Adapter extends RecyclerView.Adapter<WeChatVp2Adapter.MyViewHolder> {
        private ArticleListViewAdapter articleListViewAdapter;
        private ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList;
        private ScrollListener scrollListener;

        interface ScrollListener {
            /**
             * detect the RecyclerView is scroll to bottom.
             *
             * @param getBottom
             */
            void isScrollBottom(boolean getBottom);
        }

        public WeChatVp2Adapter(ArticleListViewAdapter articleListViewAdapter,
                                ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList,
                                ScrollListener scrollListener) {
            this.articleListViewAdapter = articleListViewAdapter;
            this.weChatBloggerList = weChatBloggerList;
            this.scrollListener = scrollListener;
        }

        public void setBloggerListData(ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList) {
            this.weChatBloggerList = weChatBloggerList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager2_wechat_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.weChatArticleRcy.setNestedScrollingEnabled(false);
            holder.weChatArticleRcy.setAdapter(articleListViewAdapter);

            holder.weChatArticleRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        scrollListener.isScrollBottom(true);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return weChatBloggerList.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder {
            private RecyclerView weChatArticleRcy;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                weChatArticleRcy = itemView.findViewById(R.id.weChatArticleListRcy);
            }
        }

    }

}
