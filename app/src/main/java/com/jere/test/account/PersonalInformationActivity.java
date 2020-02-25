package com.jere.test.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jere.test.R;
import com.jere.test.customcomponent.PersonalInfoItemCustomView;

/**
 * @author jere
 */
public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_infomation);

        initClickEvent();
    }

    private void initClickEvent() {
        PersonalInfoItemCustomView nameItem = findViewById(R.id.personal_info_name_item);
        PersonalInfoItemCustomView avatarItem = findViewById(R.id.personal_info_avatar_item);
        PersonalInfoItemCustomView emailItem = findViewById(R.id.personal_info_email_item);
        PersonalInfoItemCustomView myQrCodeItem = findViewById(R.id.personal_info_qrcode_item);
        PersonalInfoItemCustomView moreItem = findViewById(R.id.personal_info_more_item);
        PersonalInfoItemCustomView myAddressItem = findViewById(R.id.personal_info_my_address_item);
        nameItem.setOnClickListener(this);
        avatarItem.setOnClickListener(this);
        emailItem.setOnClickListener(this);
        myQrCodeItem.setOnClickListener(this);
        moreItem.setOnClickListener(this);
        myAddressItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_info_name_item:
                Intent setNameIntent = new Intent(this, SetNameActivity.class);
                startActivity(setNameIntent);
                //todo
                break;
            case R.id.personal_info_avatar_item:
                //todo
                break;
            case R.id.personal_info_email_item:
                //todo
                break;
            case R.id.personal_info_qrcode_item:
                //todo
                break;
            case R.id.personal_info_more_item:
                //todo
                break;
            case R.id.personal_info_my_address_item:
                //todo
                break;
            default:
                //todo
                break;

        }
    }
}
