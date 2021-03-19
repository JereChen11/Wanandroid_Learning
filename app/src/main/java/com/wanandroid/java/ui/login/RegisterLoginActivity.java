package com.wanandroid.java.ui.login;

import android.text.TextUtils;
import android.widget.Toast;

import com.wanandroid.java.R;
import com.wanandroid.java.data.bean.local.LoginRegisterResult;
import com.wanandroid.java.databinding.ActivityRegisterLoginBinding;
import com.wanandroid.java.ui.base.BaseVmActivity;
import com.wanandroid.java.util.Settings;

import androidx.lifecycle.Observer;

/**
 * @author jere
 */
public class RegisterLoginActivity extends BaseVmActivity<RegisterLoginViewModel, ActivityRegisterLoginBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_login;
    }

    @Override
    protected Class<RegisterLoginViewModel> getViewModelClass() {
        return RegisterLoginViewModel.class;
    }

    @Override
    public void initView() {
        initViewModel();
        switchRegisterOrLoginPattern();
        registerOrLogin();
    }

    private void initViewModel() {
        viewModel.getLoginResultLd().observe(this, loginResultObserver);
        viewModel.getRegisterResultLd().observe(this, registerResultObserver);
        viewModel.getIsLoginPatternLd().observe(this, isLoginPatternObserver);
        viewModel.setIsLoginPatternLd(true);
    }

    private void switchRegisterOrLoginPattern() {
        dataBinding.switchToRegister.setOnClickListener(v -> {
            if (viewModel.getIsLoginPatternLd().getValue() == null) {
                viewModel.setIsLoginPatternLd(true);
            }
            viewModel.setIsLoginPatternLd(!viewModel.getIsLoginPatternLd().getValue());
        });
    }

    private void registerOrLogin() {
        dataBinding.confirmBtn.setOnClickListener(v -> {
            String usernameString = dataBinding.userNameEt.getText().toString();
            String passwordString = dataBinding.passwordEt.getText().toString();
            String rePasswordString = dataBinding.repasswordEt.getText().toString();
            if (TextUtils.isEmpty(usernameString) || TextUtils.isEmpty(passwordString)) {
                Toast.makeText(RegisterLoginActivity.this,
                        "Pls input right content!",
                        Toast.LENGTH_SHORT).show();
            } else {
                if (viewModel.getIsLoginPatternLd().getValue() != null
                        && viewModel.getIsLoginPatternLd().getValue()) {
                    viewModel.login(usernameString, passwordString);
                } else {
                    viewModel.register(usernameString, passwordString, rePasswordString);
                }
            }
        });
    }

    private final Observer<Boolean> isLoginPatternObserver = aBoolean -> {
        if (aBoolean != null && aBoolean) {
            dataBinding.setIsLoginPattern(true);
            dataBinding.setTitleText(getResources().getString(R.string.login_cn));
            dataBinding.setSwitchPatternText(getResources().getString(R.string.register_cn));
        } else {
            dataBinding.setIsLoginPattern(false);
            dataBinding.setTitleText(getResources().getString(R.string.register_cn));
            dataBinding.setSwitchPatternText(getResources().getString(R.string.login_cn));
        }
    };

    private final Observer<LoginRegisterResult> loginResultObserver = result -> {
        if (result.isSuccess()) {
            Toast.makeText(RegisterLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            finish();
            Settings.getInstance().setIsLogin(true);
            Settings.getInstance().setUserName(dataBinding.userNameEt.getText().toString());
        } else {
            Toast.makeText(RegisterLoginActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
        }
    };

    private final Observer<LoginRegisterResult> registerResultObserver = result -> {
        if (result.isSuccess()) {
            showToast("Register Successful");
            Settings.getInstance().setIsLogin(true);
            Settings.getInstance().setUserName(dataBinding.userNameEt.getText().toString());
            finish();
        } else {
            showToast(result.getMsg());
        }
    };

}
