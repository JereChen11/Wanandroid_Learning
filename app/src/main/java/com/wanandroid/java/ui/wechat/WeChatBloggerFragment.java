package com.wanandroid.java.ui.wechat;

import com.google.android.material.tabs.TabLayoutMediator;
import com.wanandroid.java.data.bean.WeChatBlogger;
import com.wanandroid.java.databinding.FragmentWechatBloggerBinding;
import com.wanandroid.java.ui.base.BaseVmFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.adapter.FragmentStateAdapter;


/**
 * @author jere
 */
public class WeChatBloggerFragment extends BaseVmFragment<WeChatArticleViewModel, FragmentWechatBloggerBinding> {
    private final ArrayList<WeChatBlogger> mWeChatBloggerList = new ArrayList<>();

    public WeChatBloggerFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initView() {
        viewModel.getWeChatBloggerListLd().observe(getViewLifecycleOwner(), weChatBloggerListObserver);
        viewModel.setWeChatBloggerListLd();
    }

    private final Observer<List<WeChatBlogger>> weChatBloggerListObserver = new Observer<List<WeChatBlogger>>() {
        @Override
        public void onChanged(@Nullable List<WeChatBlogger> weChatBloggerList) {
            if (weChatBloggerList != null) {
                mWeChatBloggerList.clear();
                mWeChatBloggerList.addAll(weChatBloggerList);

                dataBinding.weChatVp2.setAdapter(new WeChatVp2Adapter(WeChatBloggerFragment.this, mWeChatBloggerList));
                initTabLayoutAndVp2();

            }
        }
    };

    private void initTabLayoutAndVp2() {
        new TabLayoutMediator(dataBinding.weChatBloggerTabLayout,
                dataBinding.weChatVp2,
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
