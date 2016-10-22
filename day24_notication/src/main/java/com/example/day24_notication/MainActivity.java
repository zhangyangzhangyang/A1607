package com.example.day24_notication;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    public void click(View view){
      NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("通知标题");
        builder.setContentText("这是一个基本的内容");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("AAAAAAAAAAAAAA");
        Notification build = builder.build();
        notificationManager.notify(1,build);

    }
}
