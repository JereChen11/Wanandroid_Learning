package com.jere.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @author jere
 */
public class BaseBarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_bottom_bar);

        Button homePageBtn = findViewById(R.id.btn_home_page);
        Button page1Btn = findViewById(R.id.btn_page_1);
        Button page2Btn = findViewById(R.id.btn_page_2);
        Button page3Btn = findViewById(R.id.btn_page_3);
        homePageBtn.setOnClickListener(this);
        page1Btn.setOnClickListener(this);
        page2Btn.setOnClickListener(this);
        page3Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home_page:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.btn_page_1:
                Intent page1Intent = new Intent(this, Page1Activity.class);
                startActivity(page1Intent);
                break;
            case R.id.btn_page_2:
                Intent page2Intent = new Intent(this, Page2Activity.class);
                startActivity(page2Intent);
                break;
            case R.id.btn_page_3:
                Intent page3Intent = new Intent(this, Page3Activity.class);
                startActivity(page3Intent);
                break;
            default:
                break;
        }
    }
}
