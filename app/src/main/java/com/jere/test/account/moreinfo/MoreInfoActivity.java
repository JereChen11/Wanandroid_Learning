package com.jere.test.account.moreinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jere.test.R;
import com.jere.test.customcomponent.PersonalInfoItemCustomView;

/**
 * @author jere
 */
public class MoreInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        PersonalInfoItemCustomView gender = findViewById(R.id.more_info_gender_item);
        PersonalInfoItemCustomView district = findViewById(R.id.more_info_district_item);
        PersonalInfoItemCustomView personalSignature = findViewById(R.id.more_info_personal_signature);
        gender.setOnClickListener(this);
        district.setOnClickListener(this);
        personalSignature.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_info_gender_item:
                //todo set gender
                Intent setGenderIntent = new Intent(this, SetGenderActivity.class);
                startActivity(setGenderIntent);
                break;
            case R.id.more_info_district_item:
                //todo set district
                break;
            case R.id.more_info_personal_signature:
                //todo set personal signature
                break;
            default:
                break;
        }
    }
}
