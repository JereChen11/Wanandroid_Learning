package com.wanandroid.java.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wanandroid.java.MyApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.TypeCastException;

/**
 * @author jere
 */
public abstract class BaseVmFragment<VM extends ViewModel, B extends ViewDataBinding> extends Fragment {

    public VM viewModel;
    public B dataBinding;

    protected final String TAG = getClass().getName();

    protected abstract void initView();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(getViewModelClass());
        try {
            Method method = getDataBindingClass().getDeclaredMethod("inflate",
                    LayoutInflater.class,
                    ViewGroup.class,
                    Boolean.TYPE);
            dataBinding = (B) method.invoke(null, inflater, container, false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private Class<VM> getViewModelClass() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (type == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<VM>");
        } else {
            return (Class<VM>) type;
        }
    }

    private Class<B> getDataBindingClass() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        if (type == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<B>");
        } else {
            return (Class<B>) type;
        }
    }

    public void showToast(String textContent) {
        Toast.makeText(MyApplication.getInstance(), textContent, Toast.LENGTH_SHORT).show();
    }

}
