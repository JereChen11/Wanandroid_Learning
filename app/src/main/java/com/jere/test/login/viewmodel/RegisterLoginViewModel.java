package com.jere.test.login.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jere.test.login.RegisterLoginRepository;

/**
 * @author jere
 */
public class RegisterLoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLoginPatternLd;
    private MutableLiveData<Boolean> isLoginSuccessfulLd;
    private MutableLiveData<Boolean> isRegisterSuccessfulLd;

    public RegisterLoginViewModel() {
        this.isLoginPatternLd = new MutableLiveData<>();
        this.isLoginSuccessfulLd = new MutableLiveData<>();
        this.isRegisterSuccessfulLd = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getIsLoginPatternLd() {
        return isLoginPatternLd;
    }

    public void setIsLoginPatternLd(Boolean isLoginPattern) {
        this.isLoginPatternLd.postValue(isLoginPattern);
    }

    public MutableLiveData<Boolean> getIsLoginSuccessfulLd() {
        return isLoginSuccessfulLd;
    }

    public void login(String userName, String password) {
        RegisterLoginRepository.getInstance().login(userName, password, new RegisterLoginRepository.LoginListener() {
            @Override
            public void isLogin(boolean isLogin) {
                setIsLoginSuccessfulLd(isLogin);
            }
        });
    }

    public void setIsLoginSuccessfulLd(Boolean isLogin) {
        this.isLoginSuccessfulLd.postValue(isLogin);
    }

    public MutableLiveData<Boolean> getIsRegisterSuccessfulLd() {
        return isRegisterSuccessfulLd;
    }

    public void setIsRegisterSuccessfulLd(Boolean isRegister) {
        this.isRegisterSuccessfulLd.postValue(isRegister);
    }
}
