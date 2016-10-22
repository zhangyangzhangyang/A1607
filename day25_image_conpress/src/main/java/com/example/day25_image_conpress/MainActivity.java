package com.example.day25_image_conpress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片二次采样
 *目的：解决OOM(out of memory)
 */
public class MainActivity extends AppCompatActivity {
    private static final String PATH ="http://i3.72g.com/upload/201510/201510261436311061.png";
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            mImageView.setImageBitmap(bitmap);
        }
    };
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.main_show_image);
    }
    public void click(View view){
        new Thread(new ImageRunnable()).start();
    }
    class ImageRunnable implements Runnable{
        @Override
        public void run() {
            InputStream is = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                URL url = new URL(PATH);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();
                byteArrayOutputStream = new ByteArrayOutputStream();
                if(urlConnection.getResponseCode() == 200){
                    is = urlConnection.getInputStream();
                    int len = 0;
                    byte [] buffer = new byte[1024];
                    while((len = is.read(buffer)) !=-1){
                        byteArrayOutputStream.write(buffer,0,len);
                    }
                    byteArrayOutputStream.flush();
                    byte[] imageBytes = byteArrayOutputStream.toByteArray();
                    //将流转化成图片对象
                    BitmapFactory.Options options  = new BitmapFactory.Options();
                    //表示仅仅获取图片的大小
                    options.inJustDecodeBounds = true;
                    //出现oom主要是图片转换
                    //仅仅解析图片的大小
                   BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length,options);
                    //获取图片的大小.高度和宽度
                    int outHeight = options.outHeight;
                    int outWidth = options.outWidth;
                    //计算压缩比
                   int ratio = outWidth /100;
                    //ratio表示压缩比，表示将图片压缩到原图的radio分之一
                    if(ratio > 1){
                        options.inSampleSize = ratio;
                    }else {
                        options.inSampleSize = 1;
                    }
                    //接下来获取图片的真实数据
                    options.inJustDecodeBounds = false;
                    //获取压缩后的图片
                    Bitmap bitmap =   BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length,options);
                    Message message = mHandler.obtainMessage();
                    message.obj = bitmap;
                    mHandler.sendMessage(message);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
