package com.jere.test.account;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jere.test.R;

/**
 * @author jere
 */
public class SetNameActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText setNameEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name);

        TextView cancelTv = findViewById(R.id.cancel_tv);
        cancelTv.setOnClickListener(this);
        Button finishBtn = findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(this);
        setNameEt = findViewById(R.id.set_name_et);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_tv:
                finish();
                break;
            case R.id.finish_btn:
                //todo save the updating about set name
                if (!TextUtils.isEmpty(setNameEt.getText())) {
                    Toast.makeText(SetNameActivity.this,
                            "set name: " + setNameEt.getText(),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
