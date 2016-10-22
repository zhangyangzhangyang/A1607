package com.example.day26_media;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.day26_media.adapter.MusicAdapter;
import com.example.day26_media.service.SecondActivity;

public class MainActivity extends AppCompatActivity {

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
        mListView = (ListView) findViewById(R.id.main_music_lv);
        musicAdapter = new MusicAdapter(this);
        mListView.setAdapter(musicAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

}
