package com.androidxx.yangjw.day26_service_music_demo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.androidxx.yangjw.day26_service_music_demo.service.MusicService;

import java.io.IOException;

/**
 * 音乐播放器的基本功能
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    private ListView mListView;
    private MusicAdapter musicAdapter;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.music_list_lv);
        musicAdapter = new MusicAdapter(this);
        mListView.setAdapter(musicAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                notification();
                Intent intent  = new Intent(MainActivity.this,MusicActivity.class);
                startActivity(intent);

            }
        });
    }

//    private void notification() {
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setContentTitle("音乐播放");
//        builder.setOngoing(true);
//        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_view);
//        //
//        Intent intent = new Intent(this,MusicService.class);
//        intent.putExtra("stop",true);
//        PendingIntent pendingIntent = PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.notification_pause_btn,pendingIntent);
//        builder.setContent(remoteViews);
//        notificationManager.notify(1,builder.build());
//    }

//    public void click(View view) {
//        Log.i(TAG, "click: ");
//        //重置
//        mediaPlayer.reset();
//        //设置音频文件的数据源
//        try {
//            mediaPlayer.setDataSource("/sdcard/Download/xiaoxiaoniao.mp3");
//            //进行播放的前期准备工作
//            mediaPlayer.prepareAsync();//异步准备
//            //监听异步准备完成的事件
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    Log.i(TAG, "onPrepared: ");
//                    //播放
//                    mp.start();
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
