package com.example.day24_broadcast_dongtai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 张样 on 2016/10/11.
 */
public class MyReceive extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("+++++++", "onReceive: ");

    }
}
