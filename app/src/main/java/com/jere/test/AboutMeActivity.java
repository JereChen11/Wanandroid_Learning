package com.jere.test;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.jere.test.article.modle.api.AbstractRetrofitCallback;
import com.jere.test.article.modle.api.ApiService;
import com.jere.test.article.modle.api.ApiWrapper;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author jere
 */
public class AboutMeActivity extends AppCompatActivity {
    private static final String TAG = "AboutMeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        WebView webView = findViewById(R.id.about_me_webview);
        webView.loadUrl("https://blog.csdn.net/jerechen");
        Toast.makeText(AboutMeActivity.this, "click content tv", Toast.LENGTH_SHORT).show();

        Button testTouchEventBtn = findViewById(R.id.test_touch_event_btn);
        testTouchEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ConstraintLayout csl = findViewById(R.id.about_me_csl);

        HashMap<String, String> map = new HashMap<>();
        map.put("username", "Jere_Chen");
        map.put("password", "zxc123");
        ApiService apiService = ApiWrapper.getRetrofitInstance().create(ApiService.class);
        apiService.login(map).enqueue(new AbstractRetrofitCallback() {
            @Override
            public void getSuccessful(String responseBody) {
                Log.e(TAG, "getSuccessful: " + responseBody);
            }

            @Override
            public void getFailed(String failedMsg) {
                Log.e(TAG, "getFailed: " + failedMsg);
            }
        });



    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

//    onInterceptTouchEvent


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
