package com.example.day31_webview_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
private String path = "http://192.168.52.168:8080/android1607/user_login.html";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //配置javascript的接口
        mWebView.addJavascriptInterface(new JavaScriptInterface(this),"android1607");
        mWebView.loadUrl(path);
    }
}
