package com.wanandroid.java.ui.aboutme;

import android.os.Bundle;
import android.webkit.WebView;

import com.wanandroid.java.R;

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
    }
}