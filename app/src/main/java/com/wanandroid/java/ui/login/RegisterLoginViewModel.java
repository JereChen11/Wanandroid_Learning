package com.wanandroid.java.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wanandroid.java.ui.login.RegisterLoginRepository;

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

    private void setIsLoginSuccessfulLd(Boolean isLogin) {
        this.isLoginSuccessfulLd.postValue(isLogin);
    }

    public void login(String userName, String password) {
        RegisterLoginRepository.getInstance().login(userName, password, new RegisterLoginRepository.LoginListener() {
            @Override
            public void login(boolean isLogin) {
                setIsLoginSuccessfulLd(isLogin);
            }
        });
    }

    public MutableLiveData<Boolean> getIsRegisterSuccessfulLd() {
        return isRegisterSuccessfulLd;
    }

    private void setIsRegisterSuccessfulLd(Boolean isRegister) {
        this.isRegisterSuccessfulLd.postValue(isRegister);
    }

    public void register(String userName, String password, String rePassword) {
        RegisterLoginRepository.getInstance().register(userName,
                password,
                rePassword,
                new RegisterLoginRepository.RegisterListener() {
                    @Override
                    public void register(boolean isRegisterSuccessful) {
                        setIsRegisterSuccessfulLd(isRegisterSuccessful);
                    }
                });
    }
}
