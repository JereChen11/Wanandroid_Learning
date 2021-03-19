package com.wanandroid.java.ui.wechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.wanandroid.java.data.bean.WeChatArticleBloggerList;
import com.wanandroid.java.databinding.FragmentWechatBloggerBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;


/**
 * @author jere
 */
public class WeChatBloggerFragment extends Fragment {
    private final ArrayList<WeChatArticleBloggerList.DataBean> mWeChatBloggerList = new ArrayList<>();
    private WeChatArticleViewModel viewModel;
    private FragmentWechatBloggerBinding mBinding;
    private int pageNumber = 0;
    private int currentWeChatBloggerId = 0;
    private boolean isLoadAllArticleData = false;
    private WeChatVp2Adapter weChatVp2Adapter;



//    private Observer<ArticleListBean> weChatArticleListObserver = new Observer<ArticleListBean>() {
//        @Override
//        public void onChanged(ArticleListBean articleListBean) {
//            if (articleListBean != null) {
//                isLoadAllArticleData = articleListBean.getData().isOver();
//                articleListViewAdapter.setIsLoadAllArticleData(isLoadAllArticleData);
//                mWeChatArticleListData.addAll(articleListBean.getData().getDatas());
//                ArrayList<ArticleListBean.DataBean.DatasBean> newArticleList = new ArrayList<>(mWeChatArticleListData);
//                articleListViewAdapter.setData(newArticleList);
//            }
//        }
//    };

    public WeChatBloggerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentWechatBloggerBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this, new WeChatVmFactory()).get(WeChatArticleViewModel.class);
        viewModel.getWeChatArticleBloggerListLd().observe(getViewLifecycleOwner(), weChatArticleBloggerListObserver);
        viewModel.setWeChatArticleBloggerListLd();

    }

    private final Observer<WeChatArticleBloggerList> weChatArticleBloggerListObserver = new Observer<WeChatArticleBloggerList>() {
        @Override
        public void onChanged(@Nullable WeChatArticleBloggerList weChatArticleBloggerList) {
            if (weChatArticleBloggerList != null) {
                mWeChatBloggerList.clear();
                mWeChatBloggerList.addAll(weChatArticleBloggerList.getData());

                mBinding.weChatVp2.setAdapter(new WeChatVp2Adapter(WeChatBloggerFragment.this, mWeChatBloggerList));
                initTabLayoutAndVp2();

            }
        }
    };

    private void initTabLayoutAndVp2() {
        new TabLayoutMediator(mBinding.weChatBloggerTabLayout,
                mBinding.weChatVp2,
                (tab, position) -> tab.setText(mWeChatBloggerList.get(position).getName())).attach();
    }

//    static class WeChatVp2Adapter extends RecyclerView.Adapter<WeChatVp2Adapter.MyViewHolder> {
//        private ArticleListViewAdapter articleListViewAdapter;
//        private ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList;
//        private ScrollListener scrollListener;
//
//        interface ScrollListener {
//            /**
//             * detect the RecyclerView is scroll to bottom.
//             *
//             * @param getBottom
//             */
//            void isScrollBottom(boolean getBottom);
//        }
//
//        public WeChatVp2Adapter(ArticleListViewAdapter articleListViewAdapter,
//                                ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList,
//                                ScrollListener scrollListener) {
//            this.articleListViewAdapter = articleListViewAdapter;
//            this.weChatBloggerList = weChatBloggerList;
//            this.scrollListener = scrollListener;
//        }
//
//        public void setBloggerListData(ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList) {
//            this.weChatBloggerList = weChatBloggerList;
//            notifyDataSetChanged();
//        }
//
//        @NonNull
//        @Override
//        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager2_wechat_item, parent, false);
//            return new MyViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//            holder.weChatArticleRcy.setNestedScrollingEnabled(false);
//            holder.weChatArticleRcy.setAdapter(articleListViewAdapter);
//
//            holder.weChatArticleRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                    super.onScrollStateChanged(recyclerView, newState);
//                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                        scrollListener.isScrollBottom(true);
//                    }
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return weChatBloggerList.size();
//        }
//
//        static class MyViewHolder extends RecyclerView.ViewHolder {
//            private RecyclerView weChatArticleRcy;
//
//            public MyViewHolder(@NonNull View itemView) {
//                super(itemView);
//                weChatArticleRcy = itemView.findViewById(R.id.weChatArticleListRcy);
//            }
//        }
//
//    }

    static class WeChatVp2Adapter extends FragmentStateAdapter {
        private final ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList;

        public WeChatVp2Adapter(@NonNull Fragment fragment, ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList) {
            super(fragment);
            this.weChatBloggerList = weChatBloggerList;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return WeChatArticleListFragment.newInstance(weChatBloggerList.get(position).getId());
        }

        @Override
        public int getItemCount() {
            return weChatBloggerList.size();
        }
    }

}
