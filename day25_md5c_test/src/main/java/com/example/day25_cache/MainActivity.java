package com.example.day25_cache;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.main_text_view);
        init();
    }

    private void init() {
        String passward = "123456";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(passward.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i <digest.length; i++) {
                builder.append(digest[i]);
            }
            //将密文转换成16进制数字
            StringBuilder oxBuilder = new StringBuilder();
            for (int j = 0; j < digest.length; j++) {
                String hexString = Integer.toHexString(0xff & digest[j]);
                oxBuilder.append(hexString);
            }
            mTextView.setText(builder.toString()+"+++++++++++++++"+oxBuilder.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
