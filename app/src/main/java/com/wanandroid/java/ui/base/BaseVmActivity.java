package com.wanandroid.java.ui.base;

import android.os.Bundle;
import android.widget.Toast;

import com.wanandroid.java.MyApplication;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author jere
 */
public abstract class BaseVmActivity<VM extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity {

    public VM viewModel;
    public B dataBinding;

    protected final String TAG = getClass().getName();

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract Class<VM> getViewModelClass();

    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = new ViewModelProvider(this).get(getViewModelClass());

        initView();
    }

    public void showToast(String textContent) {
        Toast.makeText(MyApplication.getInstance(), textContent, Toast.LENGTH_SHORT).show();
    }

}
