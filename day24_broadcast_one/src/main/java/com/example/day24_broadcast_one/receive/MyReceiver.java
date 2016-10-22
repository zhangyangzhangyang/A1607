package com.example.day24_broadcast_one.receive;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.day24_broadcast_on.R;
import com.example.day24_broadcast_on.Tool;

/**
 * Created by 张样 on 2016/10/11.
 */
public class MyReceiver extends BroadcastReceiver {

    private NotificationManager notificationManaer;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("+++++++++++++++++", "onReceive: ");
        notification(context);
        Tool.isStop = true;
    }

    private void notification(Context context) {
        notificationManaer = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("音乐");
        builder.setContentText("音乐播放暂停");
        Notification notification = builder.build();
        notificationManaer.notify(1001,notification);
    }


}
