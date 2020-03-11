package com.jere.test.account.moreinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jere.test.R;
import com.jere.test.databinding.ActivityMoreInfoBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class MoreInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        ActivityMoreInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_more_info);

        binding.moreInfoGenderItem.setOnClickListener(this);
        binding.moreInfoDistrictItem.setOnClickListener(this);
        binding.moreInfoPersonalSignature.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.moreInfoGenderItem:
                //todo set gender
                Intent setGenderIntent = new Intent(this, SetGenderActivity.class);
                startActivity(setGenderIntent);
                break;
            case R.id.moreInfoDistrictItem:
                //todo set district
                break;
            case R.id.moreInfoPersonalSignature:
                //todo set personal signature
                break;
            default:
                break;
        }
    }
}
