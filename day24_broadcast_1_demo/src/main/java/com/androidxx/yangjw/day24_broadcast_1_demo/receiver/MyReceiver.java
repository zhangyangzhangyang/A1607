package com.androidxx.yangjw.day24_broadcast_1_demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.androidxx.yangjw.day24_broadcast_1_demo.Tool;

/**
 * Created by yangjw on 2016/10/11.
 * 一个广播接收器
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "androidxx";

    /**
     * 接收数据
     * @param context 上下文对象
     * @param intent 用来传递信息
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: --" + intent.getData());
        Tool.isStop = true;
    }
}
