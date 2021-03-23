package com.wanandroid.java.ui.wechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.wanandroid.java.data.bean.WeChatBlogger;
import com.wanandroid.java.databinding.FragmentWechatBloggerBinding;

import java.util.ArrayList;
import java.util.List;

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
    private final ArrayList<WeChatBlogger> mWeChatBloggerList = new ArrayList<>();
    private WeChatArticleViewModel viewModel;
    private FragmentWechatBloggerBinding mBinding;

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
        viewModel.getWeChatBloggerListLd().observe(getViewLifecycleOwner(), weChatBloggerListObserver);
        viewModel.setWeChatBloggerListLd();

    }

    private final Observer<List<WeChatBlogger>> weChatBloggerListObserver = new Observer<List<WeChatBlogger>>() {
        @Override
        public void onChanged(@Nullable List<WeChatBlogger> weChatBloggerList) {
            if (weChatBloggerList != null) {
                mWeChatBloggerList.clear();
                mWeChatBloggerList.addAll(weChatBloggerList);

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

    static class WeChatVp2Adapter extends FragmentStateAdapter {
        private final ArrayList<WeChatBlogger> weChatBloggerList;

        public WeChatVp2Adapter(@NonNull Fragment fragment, ArrayList<WeChatBlogger> weChatBloggerList) {
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
