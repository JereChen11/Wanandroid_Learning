package com.wanandroid.java.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wanandroid.java.R;
import com.wanandroid.java.databinding.ActivityPersonalInfomationBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityPersonalInfomationBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_infomation);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_personal_infomation);

        initClickEvent();
    }

    private void initClickEvent() {
        mBinding.personalInfoNameItem.setOnClickListener(this);
        mBinding.personalInfoAvatarItem.setOnClickListener(this);
        mBinding.personalInfoMyAddressItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personalInfoNameItem:
                Intent setNameIntent = new Intent(this, SetNameActivity.class);
                startActivity(setNameIntent);
                //todo
                break;
            case R.id.personalInfoAvatarItem:
                //todo
                break;
            case R.id.personalInfoMyAddressItem:
                //todo
                break;
            default:
                //todo
                break;

        }
    }
}
