package com.jere.test.article.view.wechat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.homearticle.ArticleListBean;
import com.jere.test.article.modle.beanfiles.wechat.WeChatArticleBloggerList;
import com.jere.test.article.view.ArticleDetailWebViewActivity;
import com.jere.test.article.view.ArticleListViewAdapter;
import com.jere.test.article.viewmodel.wechat.WeChatBlogArticleViewModel;
import com.jere.test.databinding.FragmentWechatBlogArticleBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


/**
 * @author jere
 */
public class WeChatBlogArticleFragment extends Fragment {
    private ArrayList<WeChatArticleBloggerList.DataBean> mWeChatBloggerList = new ArrayList<>();
    private WeChatBlogArticleViewModel mWeChatBlogArticleVm;
    private ArrayList<ArticleListBean.DataBean.DatasBean> mWeChatArticleListData = new ArrayList<>();
    private FragmentWechatBlogArticleBinding mBinding;

    private Observer<WeChatArticleBloggerList> weChatArticleBloggerListObserver = new Observer<WeChatArticleBloggerList>() {
        @Override
        public void onChanged(@Nullable WeChatArticleBloggerList weChatArticleBloggerList) {
            if (weChatArticleBloggerList != null) {
                mWeChatBloggerList = weChatArticleBloggerList.getData();
                WeChatBloggerListVpAdapter adapter = new WeChatBloggerListVpAdapter(mWeChatBloggerList);
                mBinding.weChatBlogArticleVp2.setAdapter(adapter);
            }
        }
    };
    private Observer<ArticleListBean> weChatArticleListObserver = new Observer<ArticleListBean>() {
        @Override
        public void onChanged(ArticleListBean articleListBean) {
            if (articleListBean != null) {
                mWeChatArticleListData = articleListBean.getData().getDatas();

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

        mBinding.weChatBlogArticleVp2.setLayoutParams(new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        WeChatBloggerListVpAdapter weChatBloggerListVpAdapter = new WeChatBloggerListVpAdapter(mWeChatBloggerList);
        mBinding.weChatBlogArticleVp2.setAdapter(weChatBloggerListVpAdapter);
        mBinding.weChatBlogArticleVp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                WeChatArticleBloggerList.DataBean data = mWeChatBloggerList.get(position);
                mWeChatBlogArticleVm.setWeChatArticleListLd(data.getId(), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    static class WeChatBloggerListVpAdapter extends RecyclerView.Adapter<WeChatBloggerListVpAdapter.MyViewHolder> {
        private ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList;

        WeChatBloggerListVpAdapter(ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList) {
            this.weChatBloggerList = weChatBloggerList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_page_item_view_wechat_blogger_list, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            WeChatArticleBloggerList.DataBean data = weChatBloggerList.get(position);
            if (!TextUtils.isEmpty(data.getName())) {
                holder.nameTv.setText(data.getName());
            }
        }

        @Override
        public int getItemCount() {
            return weChatBloggerList.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView nameTv;

            MyViewHolder(@NonNull View itemView) {
                super(itemView);
                nameTv = itemView.findViewById(R.id.weChatBloggerListNameTv);
            }
        }
    }
}
