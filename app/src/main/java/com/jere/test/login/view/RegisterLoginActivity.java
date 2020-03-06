package com.jere.test.login.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jere.test.R;
import com.jere.test.login.viewmodel.RegisterLoginViewModel;

/**
 * @author jere
 */
public class RegisterLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);

        final RegisterLoginViewModel registerLoginVm = ViewModelProviders
                .of(this, new RegisterLoginViewModelFactory())
                .get(RegisterLoginViewModel.class);
        registerLoginVm.getIsLoginLd().observe(this, isLoginObserver);
        registerLoginVm.getIsRegisterLd().observe(this, isRegisterObserver);

        final EditText userNameEt = findViewById(R.id.user_name_et);
        final EditText passwordEt = findViewById(R.id.password_et);
        Button loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(userNameEt.getText().toString())
                        && !TextUtils.isEmpty(passwordEt.getText().toString())) {
                    registerLoginVm.login(userNameEt.getText().toString(), passwordEt.getText().toString());
                }
            }
        });
    }

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
            if (aBoolean != null && aBoolean) {
                //todo is register, auto login
            } else {
                //todo register account failed.
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

}
