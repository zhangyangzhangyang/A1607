package com.example.fox_pipaw.game.listdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fox_pipaw.CustomListView;
import com.example.fox_pipaw.R;

public class TodayDetailsActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTitleTxt;
    private TextView mTypeTxt;
    private TextView mSizeTxt;
    private TextView mPeoplenoTxt;
    private CustomListView mCustomListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_details);
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.activity_today_details_game_image);
        mTitleTxt = (TextView) findViewById(R.id.activity_today_details_gamename_txt);
        mTypeTxt = (TextView) findViewById(R.id.activity_today_details_gametype_txt);
        mSizeTxt = (TextView) findViewById(R.id.activity_today_details_gamesize_txt);
        mPeoplenoTxt = (TextView) findViewById(R.id.activity_today_details_gamepeopleno_txt);
        mCustomListView = (CustomListView) findViewById(R.id.activity_today_details_listview);
    }
}
