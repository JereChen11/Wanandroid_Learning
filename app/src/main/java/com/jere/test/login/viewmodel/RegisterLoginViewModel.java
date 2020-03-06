package com.jere.test.login.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.jere.test.login.RegisterLoginRepository;

/**
 * @author jere
 */
public class RegisterLoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLoginLd;
    private MutableLiveData<Boolean> isRegisterLd;

    public RegisterLoginViewModel() {
        this.isLoginLd = new MutableLiveData<>();
        this.isRegisterLd = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getIsLoginLd() {
        return isLoginLd;
    }

    public void login(String userName, String password) {
        RegisterLoginRepository.getInstance().login(userName, password, new RegisterLoginRepository.LoginListener() {
            @Override
            public void isLogin(boolean isLogin) {
                setIsLoginLd(isLogin);
            }
        });
    }

    public void setIsLoginLd(Boolean isLogin) {
        this.isLoginLd.postValue(isLogin);
    }

    public MutableLiveData<Boolean> getIsRegisterLd() {
        return isRegisterLd;
    }

    public void setIsRegisterLd(Boolean isRegister) {
        this.isRegisterLd.postValue(isRegister);
    }
}
