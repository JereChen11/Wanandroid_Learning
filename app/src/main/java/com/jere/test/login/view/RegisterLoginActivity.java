package com.jere.test.login.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.jere.test.R;
import com.jere.test.databinding.ActivityRegisterLoginBinding;
import com.jere.test.login.viewmodel.RegisterLoginViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

/**
 * @author jere
 */
public class RegisterLoginActivity extends AppCompatActivity {
    private ActivityRegisterLoginBinding mBinding;
    private RegisterLoginViewModel mRegisterLoginVm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        initDataBinding();
        initViewModel();
        switchRegisterOrLoginPattern();
        registerOrLogin();
    }

    private void initDataBinding() {
        mBinding = DataBindingUtil.setContentView(RegisterLoginActivity.this, R.layout.activity_register_login);
    }

    private void initViewModel() {
        mRegisterLoginVm = ViewModelProviders.of(this, new RegisterLoginViewModelFactory())
                .get(RegisterLoginViewModel.class);
        mRegisterLoginVm.getIsLoginSuccessfulLd().observe(this, isLoginObserver);
        mRegisterLoginVm.getIsRegisterSuccessfulLd().observe(this, isRegisterObserver);
        mRegisterLoginVm.getIsLoginPatternLd().observe(this, isLoginPatternObserver);
        mRegisterLoginVm.setIsLoginPatternLd(true);
    }

    private void switchRegisterOrLoginPattern() {
        mBinding.switchToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRegisterLoginVm.getIsLoginPatternLd().getValue() == null) {
                    mRegisterLoginVm.setIsLoginPatternLd(true);
                }
                if (mRegisterLoginVm.getIsLoginPatternLd().getValue()) {
                    mRegisterLoginVm.setIsLoginPatternLd(false);
                } else {
                    mRegisterLoginVm.setIsLoginPatternLd(true);
                }
            }
        });
    }

    private void registerOrLogin() {
        mBinding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameString = mBinding.userNameEt.getText().toString();
                String passwordString = mBinding.passwordEt.getText().toString();
                String rePasswordString = mBinding.repasswordEt.getText().toString();
                if (TextUtils.isEmpty(usernameString) || TextUtils.isEmpty(passwordString)) {
                    Toast.makeText(RegisterLoginActivity.this,
                            "Pls input right content!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (mRegisterLoginVm.getIsLoginPatternLd().getValue()) {
                        mRegisterLoginVm.login(usernameString, passwordString);
                    } else {
                        mRegisterLoginVm.register(usernameString, passwordString, rePasswordString);
                    }
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
                finish();
            } else {
                Toast.makeText(RegisterLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Observer<Boolean> isRegisterObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            if (aBoolean != null && aBoolean) {
                Toast.makeText(RegisterLoginActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(RegisterLoginActivity.this, "用户名已被注册，注意密码必须大于6位", Toast.LENGTH_SHORT).show();
            }
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
