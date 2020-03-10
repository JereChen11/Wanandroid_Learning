package com.jere.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                Log.e(TAG, "onClick: ");

            }
        });



    }
}
