package com.example.day31_webview_helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private String PATH = "http://cn.bing.com/academic/search?intlF=0&q=%e6%92%92%e6%97%a6&FORM=HDRSC4";

    /**
     * webview的基本使用
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //用来显示html的
        mWebView = (WebView) findViewById(R.id.web_view);
        //允许加载和执行javascrpt的代码
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //加载页面，显示页面
        mWebView.loadUrl(PATH);
        WebViewClient webViewClient = new WebViewClient(){
            /**
             *
             * @param view 表示当前的webview对象
             * @param url 需要打开的链接
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
              //使用当前的webview打开链接
               view.loadUrl(url);
                return true;
            }
        };
        mWebView.setWebViewClient(webViewClient);
    }
}
