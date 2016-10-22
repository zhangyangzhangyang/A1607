package com.example.hot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gift_bag_spirit.R;

public class HotDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_details);
        Intent intent = getIntent();
        String id = intent.getStringExtra("hotId");
        Log.i("张阳阳啊", "onCreate: +++++++"+id);
    }
}
