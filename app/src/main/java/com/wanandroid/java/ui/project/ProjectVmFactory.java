package com.wanandroid.java.ui.project;

import com.wanandroid.java.data.repository.ProjectTreeRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jere
 */
public class ProjectVmFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProjectViewModel(new ProjectTreeRepository());
    }
}
