package com.example.liwushuo;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by yangjw on 2016/10/25.
 */
public class MyApplication extends Application {

    // 在application的onCreate中初始化
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
