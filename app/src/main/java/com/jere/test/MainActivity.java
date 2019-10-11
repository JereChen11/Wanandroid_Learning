package com.jere.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @author jere
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getPrefBtn = findViewById(R.id.get_pref_btn);
        Button getSharedPrefBtn = findViewById(R.id.get_shared_pref_btn);
        Button getDefaultSharedPrefBtn = findViewById(R.id.get_default_shared_pref_btn);
        getPrefBtn.setOnClickListener(this);
        getSharedPrefBtn.setOnClickListener(this);
        getDefaultSharedPrefBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_pref_btn:
                //getPreferences() 方法
                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("JereTest", "first test");
                editor.apply();
                break;
            case R.id.get_shared_pref_btn:
                //getSharedPreferences() 方法
                SharedPreferences sp1 = getSharedPreferences("testGetSharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sp1.edit();
                editor1.putString("getSharedPref", "test getSharedPreferences!");
                editor1.apply();
                break;
            case R.id.get_default_shared_pref_btn:
                //getDefaultSharedPreferences() 方法
                SharedPreferences sp2 = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor2 = sp2.edit();
                editor2.putString("getDefaultSp", "jere test getDefaultSharedPreferences!");
                editor2.apply();
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
