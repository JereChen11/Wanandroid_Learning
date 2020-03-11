package com.jere.test.account;

import android.os.Bundle;

import com.jere.test.R;
import com.jere.test.databinding.ActivitySetEmailBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class SetEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_set_email);
        ActivitySetEmailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_set_email);
    }
}
