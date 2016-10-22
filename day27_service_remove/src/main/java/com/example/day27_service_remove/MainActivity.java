package com.example.day27_service_remove;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
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
    
     ServiceConnection serviceConnection = new ServiceConnection() {


         public static final String TAG = "android++";

         @Override
         public void onServiceConnected(ComponentName name, IBinder service) {
             messenger = new Messenger(service);
             Log.i(TAG, "onServiceConnected: ");
         }

         @Override
         public void onServiceDisconnected(ComponentName name) {

         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }
    public  void click(View view){
        Message message = new Message();
        message.what = 300;
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
