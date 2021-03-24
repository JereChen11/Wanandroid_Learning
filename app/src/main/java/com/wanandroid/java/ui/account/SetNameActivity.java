package com.wanandroid.java.ui.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.wanandroid.java.R;
import com.wanandroid.java.databinding.ActivitySetNameBinding;
import com.wanandroid.java.util.SpSettings;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author jere
 */
public class SetNameActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivitySetNameBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySetNameBinding.inflate(getLayoutInflater());

        mBinding.cancelBtn.setOnClickListener(this);
        mBinding.finishBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelBtn:
                finish();
                break;
            case R.id.finishBtn:
                if (!TextUtils.isEmpty(mBinding.setNameEt.getText())) {
                    Toast.makeText(SetNameActivity.this,
                            "set name: " + mBinding.setNameEt.getText(),
                            Toast.LENGTH_SHORT)
                            .show();
                    SpSettings.getInstance().setUserName(mBinding.setNameEt.getText().toString());
                    finish();
                } else {
                    Toast.makeText(SetNameActivity.this,
                            getString(R.string.pls_input_right_username_cn),
                            Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                break;
        }
    }
}
