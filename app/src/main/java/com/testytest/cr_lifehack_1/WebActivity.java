package com.testytest.cr_lifehack_1;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    WebView wvTwoDemo;
    ProgressBar tdProgressBar;
    TextView tvTwoDemo;
    private String myUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        myUrl = getIntent().getStringExtra("url");

        wvTwoDemo = (WebView) findViewById(R.id.web_view);
        tdProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        tvTwoDemo = (TextView)findViewById(R.id.text_pb);

        WebSettings webSettings = wvTwoDemo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvTwoDemo.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                tdProgressBar.setVisibility(View.GONE);
                tvTwoDemo.setVisibility(View.GONE);
            }
        });
        tdProgressBar.setVisibility(View.VISIBLE);
        tvTwoDemo.setVisibility(View.VISIBLE);
        wvTwoDemo.loadUrl(myUrl);
    }
}

