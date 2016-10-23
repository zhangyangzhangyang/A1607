package com.example.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.androidxx.yangjw.commonlibrary.AsyncTaskTool;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.main_id_txt);//
        AsyncTaskTool.load("http://big.pipaw.com/apinew/newgift/Hot")
                .post("token=cnNCa5mMjTvXD%2Bw1YEZagvo%2BpKuh5M2aAl%2FOs29Raz95MnMqCjom4qSWYC%2BSFrelkEVyzVO9u9Jm1RWc7P3YWvJwn2H7qR4I&app_version=133524429196107")
               .execute(new AsyncTaskTool.IMyCallback() {
                    @Override
                    public void success(String result) {
                        textView.setText(result);
                Log.i("android++", "success: "+result);
                    }
                });

    }
}
