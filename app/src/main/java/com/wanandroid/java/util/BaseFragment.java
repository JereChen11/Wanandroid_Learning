package com.wanandroid.java.util;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanandroid.java.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * @author jere
 */
public abstract class BaseFragment extends Fragment {
    private View mContentView;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceId(), container, false);
        mContext = getContext();
        init();
        setUpView();
        setUpData();
        return mContentView;
    }

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID * * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceId();

    /**
     * 一些View的相关操作
     */
    protected abstract void setUpView();

    /**
     * 一些Data的相关操作
     */
    protected abstract void setUpData();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据 * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    protected void init() {
    }

    public View getContentView() {
        return mContentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void refreshCurrentFragment() {
        Fragment fragment = null;
//        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("Your_Fragment_TAG");
        fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.frame);
        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();
    }
}
