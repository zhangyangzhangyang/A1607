package com.example.day27_message_one;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Messenger messenger;
    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i("android", "handleMessage: ++++");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("android.demo.SEND");
        intent.setPackage("com.example.day27_message_two");
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    public void click(View view ){
        if(messenger != null){
            Message message = new Message();
            message.what = 200;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
