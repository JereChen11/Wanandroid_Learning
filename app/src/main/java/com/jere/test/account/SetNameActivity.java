package com.jere.test.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.jere.test.R;
import com.jere.test.databinding.ActivitySetNameBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

/**
 * @author jere
 */
public class SetNameActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivitySetNameBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_set_name);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_set_name);

        mBinding.cancelTv.setOnClickListener(this);
        mBinding.finishBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelTv:
                finish();
                break;
            case R.id.finishBtn:
                //todo save the updating about set name
                if (!TextUtils.isEmpty(mBinding.setNameEt.getText())) {
                    Toast.makeText(SetNameActivity.this,
                            "set name: " + mBinding.setNameEt.getText(),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
