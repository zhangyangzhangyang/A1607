package com.androidxx.yangjw.day24_broadcast_custom_demo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by yangjw on 2016/10/11.
 * 一个广播接收器
 */
public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "androidxx";

    private NotificationManager notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: " + intent.getStringExtra("name"));
        notification(context);
    }

    private void notification(Context context) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("广播发送的通知");
        builder.setContentText("广播结合通知完成的一个案例");
        Notification notification = builder.build();
        notificationManager.notify(1011,notification);
    }
}
