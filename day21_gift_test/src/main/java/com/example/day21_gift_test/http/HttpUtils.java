package com.example.day21_gift_test.http;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 张样 on 2016/10/8.
 */
public class HttpUtils {

    private static ExecutorService executorService;

    private static void request(String path, boolean isPost, String param,ICallBack callBack){
        if(executorService == null){
            executorService = Executors.newFixedThreadPool(4);
        }
        executorService.execute(new NetworkRunnable(path,isPost,param,callBack));
    }

    static class NetworkRunnable implements  Runnable{
        public NetworkRunnable( String path, boolean isPost, String param, ICallBack callBack) {
            this.path = path;
            this.isPost = isPost;
            this.param = param;
            this.callBack = callBack;
        }

        private String path;
        private boolean isPost;
        private String param;
        private ICallBack callBack;

        private Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result = msg.obj.toString();
                callBack.success(result);
            }
        };
        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if(isPost){
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.getOutputStream().write(param.getBytes());
                    httpURLConnection.getOutputStream().flush();
                }
                httpURLConnection.connect();
                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream is = httpURLConnection.getInputStream();
                    int len = 0;
                    byte[] buffer = new byte[1024];
                    StringBuilder stringBuilder = new StringBuilder();
                    while((len = is.read(buffer)) != -1){
                        stringBuilder.append(new String(buffer,0,len));
                    }
                    Message message = mHandler.obtainMessage();
                    message.obj = stringBuilder;
                    mHandler.sendMessage(message);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public interface ICallBack{
        void success(String result);
    }

}
