package com.wanandroid.java.ui.web;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wanandroid.java.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author jere
 */
public class ArticleDetailWebViewActivity extends AppCompatActivity {
    private static final String TAG = "ArticleDetailWebViewAct";
    public static final String ARTICLE_DETAIL_WEB_LINK_KEY = "ARTICLE_DETAIL_WEB_LINK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail_web_view);

        Bundle bundle = getIntent().getExtras();
        String link = bundle.getString(ARTICLE_DETAIL_WEB_LINK_KEY);

        WebView webView = findViewById(R.id.article_detail_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


}
