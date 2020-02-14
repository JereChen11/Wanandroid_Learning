package com.jere.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * @author jere
 */
public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        WebView webView = findViewById(R.id.about_me_webview);
        webView.loadUrl("https://blog.csdn.net/jerechen");
        Toast.makeText(AboutMeActivity.this, "click content tv", Toast.LENGTH_SHORT).show();

    }
}
