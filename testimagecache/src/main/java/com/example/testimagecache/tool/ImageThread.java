package com.example.testimagecache.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 张样 on 2016/10/29.
 */
public class ImageThread implements Runnable{
    /**
     * 规定图片宽高的最大值
     */
    public static final int MAX_WIDTH = 100;
    public static final int MAX_HEIGHT = 100;
    /**
     * 图片地址
     */
    private String imagePath;
    private ImageView mImageView;

    public ImageThread(ImageView mImageView, String imagePath) {
        this.mImageView = mImageView;
        this.imagePath = imagePath;
//        this.mImageView.setTag(imagePath);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Override
    public void run() {
        //2.----从磁盘中取数据（从磁盘中取出Bitmap对象）
       //
        //
        //3.----如果磁盘中没有图片，则开启网络加载 图片
        InputStream inputString = null;
        try {
            URL url = new URL(imagePath);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.connect();
            //请求成功
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (httpUrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                 inputString = httpUrlConnection.getInputStream();
                int len = 0;
                byte [] buffer = new byte[1024];
                while ((len = inputString.read(buffer)) != -1){
                    baos.write(buffer,0,len);
                }
                baos.flush();
            }
            //将图片流转换成Bitmap对象
            byte[] bytes = baos.toByteArray();
            //二次采样
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//仅仅只获取图片的大小
            BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            //计算压缩比
            int widthRadio = outWidth / MAX_WIDTH;
            int heightRadio = outHeight / MAX_HEIGHT;
            int radio = widthRadio > heightRadio ? widthRadio :heightRadio;
            //加载图片的内容，并且对图片按照比率进行压缩
            options.inJustDecodeBounds =false;
            //配置图片的压缩比
            options.inSampleSize = radio;
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            //存入磁盘
            //
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
