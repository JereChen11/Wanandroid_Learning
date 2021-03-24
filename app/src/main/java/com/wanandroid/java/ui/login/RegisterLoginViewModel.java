package com.wanandroid.java.ui.login;

import com.wanandroid.java.data.bean.local.LoginRegisterResult;
import com.wanandroid.java.data.repository.RegisterLoginRepository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author jere
 */
public class RegisterLoginViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isLoginPatternLd;
    private final MutableLiveData<LoginRegisterResult> loginResultLd;
    private final MutableLiveData<LoginRegisterResult> registerResultLd;

    public RegisterLoginViewModel() {
        this.isLoginPatternLd = new MutableLiveData<>();
        this.loginResultLd = new MutableLiveData<>();
        this.registerResultLd = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getIsLoginPatternLd() {
        return isLoginPatternLd;
    }

    public void setIsLoginPatternLd(Boolean isLoginPattern) {
        this.isLoginPatternLd.postValue(isLoginPattern);
    }

    public MutableLiveData<LoginRegisterResult> getLoginResultLd() {
        return loginResultLd;
    }

    private void setLoginResultLd(LoginRegisterResult result) {
        this.loginResultLd.postValue(result);
    }

    public void login(String userName, String password) {
        RegisterLoginRepository.getInstance().login(userName, password,
                new RegisterLoginRepository.LoginRegisterListener() {
                    @Override
                    public void onResult(LoginRegisterResult result) {
                        setLoginResultLd(result);
                    }
                });
    }

    public MutableLiveData<LoginRegisterResult> getRegisterResultLd() {
        return registerResultLd;
    }

    private void setRegisterResultLd(LoginRegisterResult result) {
        this.registerResultLd.postValue(result);
    }

    public void register(String userName, String password, String rePassword) {
        RegisterLoginRepository.getInstance().register(userName,
                password,
                rePassword,
                new RegisterLoginRepository.LoginRegisterListener() {
                    @Override
                    public void onResult(LoginRegisterResult result) {
                        setRegisterResultLd(result);
                    }
                });
    }
}
