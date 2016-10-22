package com.example.day26_media.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.day26_media.R;

public class SecondActivity extends AppCompatActivity {

    private MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = new Intent(this,MusicService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MyBinder myBinder = (MusicService.MyBinder) service;
            musicService = myBinder.getMusicService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public void play(View view){
        if(musicService != null){
            musicService.start();
        }
    }
    public void pause(View view){
        if(musicService != null){
            musicService.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
