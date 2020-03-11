package com.jere.test.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jere.test.R;
import com.jere.test.account.moreinfo.MoreInfoActivity;
import com.jere.test.databinding.ActivityPersonalInfomationBinding;

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
        mBinding.personalInfoEmailItem.setOnClickListener(this);
        mBinding.personalInfoQrCodeItem.setOnClickListener(this);
        mBinding.personalInfoMoreItem.setOnClickListener(this);
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
            case R.id.personalInfoEmailItem:
                Intent setEmailIntent = new Intent(this, SetEmailActivity.class);
                startActivity(setEmailIntent);
                break;
            case R.id.personalInfoQrCodeItem:
                Intent qrCodeIntent = new Intent(this, MyQrCodeActivity.class);
                startActivity(qrCodeIntent);
                break;
            case R.id.personalInfoMoreItem:
                Intent moreInfoIntent = new Intent(this, MoreInfoActivity.class);
                startActivity(moreInfoIntent);
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
