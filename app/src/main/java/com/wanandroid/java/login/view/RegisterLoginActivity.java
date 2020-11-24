package com.wanandroid.java.login.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.wanandroid.java.R;
import com.wanandroid.java.databinding.ActivityRegisterLoginBinding;
import com.wanandroid.java.login.viewmodel.RegisterLoginViewModel;
import com.wanandroid.java.util.Settings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
        mRegisterLoginVm = new ViewModelProvider(this).get(RegisterLoginViewModel.class);
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
                    if (mRegisterLoginVm.getIsLoginPatternLd().getValue() != null
                            && mRegisterLoginVm.getIsLoginPatternLd().getValue()) {
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
                mBinding.setTitleText(getResources().getString(R.string.login_cn));
                mBinding.setSwitchPatternText(getResources().getString(R.string.register_cn));
            } else {
                mBinding.setIsLoginPattern(false);
                mBinding.setTitleText(getResources().getString(R.string.register_cn));
                mBinding.setSwitchPatternText(getResources().getString(R.string.login_cn));
            }
        }
    };

    private Observer<Boolean> isLoginObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            if (aBoolean != null && aBoolean) {
                Toast.makeText(RegisterLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                finish();
                Settings.getInstance().setIsLogin(true);
                Settings.getInstance().setUserName(mBinding.userNameEt.getText().toString());
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
                Settings.getInstance().setIsLogin(true);
                Settings.getInstance().setUserName(mBinding.userNameEt.getText().toString());
                finish();
            } else {
                Toast.makeText(RegisterLoginActivity.this, "用户名已被注册，注意密码必须大于6位", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
