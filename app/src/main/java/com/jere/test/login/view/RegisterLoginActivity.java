package com.jere.test.login.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.jere.test.R;
import com.jere.test.databinding.ActivityRegisterLoginBinding;
import com.jere.test.login.viewmodel.RegisterLoginViewModel;

/**
 * @author jere
 */
public class RegisterLoginActivity extends AppCompatActivity {
    private ActivityRegisterLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_login);
        mBinding.setIsLoginPattern(true);
        mBinding.setSwitchPatternText(getResources().getString(R.string.switch_to_register));
        mBinding.setTitleText(getResources().getString(R.string.login));

        final RegisterLoginViewModel registerLoginVm = ViewModelProviders
                .of(this, new RegisterLoginViewModelFactory())
                .get(RegisterLoginViewModel.class);
        registerLoginVm.getIsLoginSuccessfulLd().observe(this, isLoginObserver);
        registerLoginVm.getIsRegisterSuccessfulLd().observe(this, isRegisterObserver);
        registerLoginVm.getIsLoginPatternLd().observe(this, isLoginPatternObserver);
        registerLoginVm.setIsLoginPatternLd(true);


        mBinding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameString = mBinding.userNameEt.getText().toString();
                String passwordString = mBinding.passwordEt.getText().toString();
                if (!TextUtils.isEmpty(usernameString) && !TextUtils.isEmpty(passwordString)) {
                    registerLoginVm.login(usernameString, passwordString);
                }
                finish();
            }
        });
        mBinding.switchToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerLoginVm.getIsLoginPatternLd().getValue() == null) {
                    registerLoginVm.setIsLoginPatternLd(true);
                }
                if (registerLoginVm.getIsLoginPatternLd().getValue()) {
                    registerLoginVm.setIsLoginPatternLd(false);
                } else {
                    registerLoginVm.setIsLoginPatternLd(true);
                }
            }
        });
    }

    private Observer<Boolean> isLoginPatternObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            if (aBoolean != null && aBoolean) {
                mBinding.setIsLoginPattern(true);
                mBinding.setTitleText(getResources().getString(R.string.login));
                mBinding.setSwitchPatternText(getResources().getString(R.string.switch_to_register));
            } else {
                mBinding.setIsLoginPattern(false);
                mBinding.setTitleText(getResources().getString(R.string.register));
                mBinding.setSwitchPatternText(getResources().getString(R.string.switch_to_login));
            }
        }
    };

    private Observer<Boolean> isLoginObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            if (aBoolean != null && aBoolean) {
                Toast.makeText(RegisterLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            } else {
                //todo not login
                Toast.makeText(RegisterLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Observer<Boolean> isRegisterObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {

        }
    };


    class RegisterLoginViewModelFactory implements ViewModelProvider.Factory {

        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(RegisterLoginViewModel.class)) {
                return (T) new RegisterLoginViewModel();
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }

    @Override
    public void onBackPressed() {
    }
}
