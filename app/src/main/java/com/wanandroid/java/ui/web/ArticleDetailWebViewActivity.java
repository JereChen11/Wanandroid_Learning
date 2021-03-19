package com.wanandroid.java.ui.web;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

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
        Log.e(TAG, "onCreate: ");

        Bundle bundle = getIntent().getExtras();
        String link = bundle.getString(ARTICLE_DETAIL_WEB_LINK_KEY);

        WebView webView = findViewById(R.id.article_detail_web_view);
        webView.loadUrl(link);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


}
