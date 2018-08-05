package com.example.mozilla.duckduckgo;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import static com.example.mozilla.duckduckgo.Constants.SEARCH_TERM;

public class ResultsActivity extends AppCompatActivity {
    String mTerm;
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWebView = new WebView(this);
        setContentView(mWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        mTerm = intent.getStringExtra(SEARCH_TERM);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = getString(R.string.duckduckgo_url);
        url += mTerm;
        mWebView.loadUrl(url);
    }

    // this is here to prevent webview from reloading on screen rotation
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
