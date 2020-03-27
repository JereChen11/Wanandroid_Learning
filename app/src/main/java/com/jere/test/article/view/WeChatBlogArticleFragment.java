package com.jere.test.article.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jere.test.R;
import com.jere.test.article.modle.beanfiles.wechat.WeChatArticleBloggerList;
import com.jere.test.article.modle.beanfiles.wechat.WeChatArticleList;
import com.jere.test.article.viewmodel.wechat.WeChatBlogArticleViewModel;
import com.jere.test.util.RecyclerItemClickListener;

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
    private ViewPager2 mWeChatBloggerListVp2;
    private ArrayList<WeChatArticleBloggerList.DataBean> mWeChatBloggerList = new ArrayList<>();
    private WeChatBlogArticleViewModel mWeChatBlogArticleVm;
    private RecyclerView mWeChatArticleListRecyclerView;
    private ArrayList<WeChatArticleList.DataBean.DatasBean> mWeChatArticleListData = new ArrayList<>();


    private OnFragmentInteractionListener mListener;
    private Observer<WeChatArticleBloggerList> weChatArticleBloggerListObserver = new Observer<WeChatArticleBloggerList>() {
        @Override
        public void onChanged(@Nullable WeChatArticleBloggerList weChatArticleBloggerList) {
            if (weChatArticleBloggerList != null) {
                mWeChatBloggerList = weChatArticleBloggerList.getData();
                WeChatBloggerListVpAdapter adapter = new WeChatBloggerListVpAdapter(mWeChatBloggerList);
                mWeChatBloggerListVp2.setAdapter(adapter);
            }
        }
    };
    private Observer<WeChatArticleList> weChatArticleListObserver = new Observer<WeChatArticleList>() {
        @Override
        public void onChanged(WeChatArticleList weChatArticleList) {
            if (weChatArticleList != null) {
                mWeChatArticleListData = weChatArticleList.getData().getDatas();
                WeChatArticleListAdapter weChatArticleListAdapter = new WeChatArticleListAdapter(mWeChatArticleListData);
                mWeChatArticleListRecyclerView.setAdapter(weChatArticleListAdapter);
            }
        }
    };

    public WeChatBlogArticleFragment() {
        // Required empty public constructor
    }

    public static WeChatBlogArticleFragment newInstance() {
        return new WeChatBlogArticleFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wechat_blog_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWeChatBlogArticleVm = new ViewModelProvider(this).get(WeChatBlogArticleViewModel.class);
        mWeChatBlogArticleVm.getWeChatArticleBloggerListLd().observe(getViewLifecycleOwner(), weChatArticleBloggerListObserver);
        mWeChatBlogArticleVm.setWeChatArticleBloggerListLd();
        mWeChatBlogArticleVm.getWeChatArticleListLd().observe(getViewLifecycleOwner(), weChatArticleListObserver);

        mWeChatBloggerListVp2 = view.findViewById(R.id.weChatBlogArticleVp2);
        mWeChatBloggerListVp2.setLayoutParams(new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        WeChatBloggerListVpAdapter weChatBloggerListVpAdapter = new WeChatBloggerListVpAdapter(mWeChatBloggerList);
        mWeChatBloggerListVp2.setAdapter(weChatBloggerListVpAdapter);
        mWeChatBloggerListVp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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

        mWeChatArticleListRecyclerView = view.findViewById(R.id.weChatArticleListRecyclerView);
        WeChatArticleListAdapter weChatArticleListAdapter = new WeChatArticleListAdapter(mWeChatArticleListData);
        mWeChatArticleListRecyclerView.setAdapter(weChatArticleListAdapter);
        mWeChatArticleListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                mWeChatArticleListRecyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String link = mWeChatArticleListData.get(position).getLink();
                        Intent intent = new Intent(getActivity(), ArticleDetailWebViewActivity.class);
                        intent.putExtra(ArticleDetailWebViewActivity.ARTICLE_DETAIL_WEB_LINK_KEY, link);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class WeChatBloggerListVpAdapter extends RecyclerView.Adapter<WeChatBloggerListVpAdapter.MyViewHolder> {
        private ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList;

        WeChatBloggerListVpAdapter(ArrayList<WeChatArticleBloggerList.DataBean> weChatBloggerList) {
            this.weChatBloggerList = weChatBloggerList;
        }

        @NonNull
        @Override
        public WeChatBloggerListVpAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_page_item_view_wechat_blogger_list, parent, false);
            return new WeChatBloggerListVpAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WeChatBloggerListVpAdapter.MyViewHolder holder, int position) {
            WeChatArticleBloggerList.DataBean data = weChatBloggerList.get(position);
            if (!TextUtils.isEmpty(data.getName())) {
                holder.nameTv.setText(data.getName());
            }
        }

        @Override
        public int getItemCount() {
            return weChatBloggerList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView nameTv;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                nameTv = itemView.findViewById(R.id.weChatBloggerListNameTv);
            }
        }
    }

    class WeChatArticleListAdapter extends RecyclerView.Adapter<WeChatArticleListAdapter.MyViewHolder> {
        private ArrayList<WeChatArticleList.DataBean.DatasBean> dataList;

        WeChatArticleListAdapter(ArrayList<WeChatArticleList.DataBean.DatasBean> list) {
            this.dataList = list;
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
            WeChatArticleList.DataBean.DatasBean data = dataList.get(position);
            holder.titleTv.setText(data.getTitle());
            holder.authorTv.setText(data.getAuthor());
            holder.sharedDateTv.setText(data.getNiceShareDate());

        }

        @Override
        public int getItemCount() {
            return dataList.size();
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
