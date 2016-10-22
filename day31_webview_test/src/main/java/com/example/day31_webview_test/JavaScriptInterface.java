package com.example.day31_webview_test;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by 张样 on 2016/10/21.
 */
public class JavaScriptInterface {
    private Context context;

    public JavaScriptInterface(Context context) {
        this.context = context;
    }

    //javascript可以调用的方法
    @JavascriptInterface
    public void login(String name , String password){
        Toast.makeText(context,"用户名"+name+"密码"+password,Toast.LENGTH_SHORT).show();

    }
}
