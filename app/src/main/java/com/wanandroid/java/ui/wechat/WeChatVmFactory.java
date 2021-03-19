package com.wanandroid.java.ui.wechat;

import com.wanandroid.java.data.repository.WeChatArticleRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jere
 */
public class WeChatVmFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WeChatArticleViewModel(new WeChatArticleRepository());
    }
}
