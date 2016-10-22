package com.example.day24_broadcast_on;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private NotificationManager notificationManaer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new MusicThead()).start();

    }
    class MusicThead implements Runnable{

        @Override
        public void run() {
            while(true){
                if(Tool.isStop){
                    try {
                        Thread.sleep(30000);
                        Tool.isStop = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    notification();
                }
                Log.i("android+++++", "run: 正在播放音乐");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    private void notification() {
        notificationManaer = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("音乐");
        builder.setContentText("音乐正在播放");
        Notification notification = builder.build();
        notificationManaer.notify(1001,notification);
    }
}
