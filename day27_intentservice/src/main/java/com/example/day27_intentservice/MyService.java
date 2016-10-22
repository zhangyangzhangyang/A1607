package com.example.day27_intentservice;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by 张样 on 2016/10/14.
 */
public class MyService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name 子线程的名称
     *             Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }
    public MyService() {
        this("a1");
    }
    /**
     * 运行在子线程中
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        //可以在这执行耗时操作

    }
}
