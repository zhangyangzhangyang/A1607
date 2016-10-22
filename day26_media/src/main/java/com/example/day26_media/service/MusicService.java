package com.example.day26_media.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.day26_media.R;

import java.io.IOException;

/**
 * Created by 张样 on 2016/10/13.
 */
public class MusicService extends Service {

    private static final String TAG = "android++";
    private MediaPlayer mediaPlayer;
    private NotificationManager notificationManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public class MyBinder extends Binder{
        public MusicService getMusicService(){
            return MusicService.this;
        }
    }

    /**
     * 音乐播放器的播放和暂停
     */
    public void start(){
        mediaPlayer.start();
    }
    public void pause(){
        mediaPlayer.pause();
    }

    /**
     * 创建Service
     * 只会执行一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }
    private  void notifacation(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("音乐播放");
        builder.setOngoing(true);
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_view);

        Intent intent = new Intent(this,MusicService.class);
        intent.putExtra("stop",true);
        PendingIntent pendingIntent = PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notification_pasue_btn,pendingIntent);
        builder.setContent(remoteViews);
        notificationManager.notify(1,builder.build());
     }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notifacation();
        boolean stop = intent.getBooleanExtra("stop",false);
        if(stop){
            pause();
        }else{
            playMusic();
        }
        return START_NOT_STICKY;
    }

    //准备音乐
    private void playMusic(){
        try {
            Log.i(TAG, "playMusic: +++++++++++++++++++++++++++++++++");
            mediaPlayer.setDataSource("/sdcard/Baidu_music/download/moumoon-Spark-Sunshine Girl-128.mp3");
            mediaPlayer.prepare();
            mediaPlayer.start();
            Log.i(TAG, "playMusic: 播放音乐");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
