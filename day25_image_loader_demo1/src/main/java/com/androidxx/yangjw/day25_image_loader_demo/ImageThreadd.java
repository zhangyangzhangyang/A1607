package com.androidxx.yangjw.day25_image_loader_demo;

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
 * Created by yangjw on 2016/10/12.
 */
public class ImageThreadd implements Runnable {
    /**
     * 规定的图片的最大值，宽高像素最大只能是100个像素
     */
    public static final int MAX_WIDTH = 100;
    public static final int MAX_HEIGHT = 100;
    /**
     * 图片地址
     */
    private String imagePath;

    private ImageView mImageView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //防止因为ListView的Item复用导致的图片出现跳动BUG
            if (mImageView.getTag().equals(imagePath)) {
                Bitmap bitmap = (Bitmap) msg.obj;
                //将图片缓存到内存中
                MemoryCacheTool.save(imagePath,bitmap);
                mImageView.setImageBitmap(bitmap);
            }
        }
    };

    public ImageThreadd(String imagePath, ImageView mImageView) {
        this.imagePath = imagePath;
        this.mImageView = mImageView;
        mImageView.setTag(imagePath);
    }

    @Override
    public void run() {
        //2、从磁盘中取
        //从磁盘中取出Bitmap对象
        Bitmap cacheBitmap = DiskCacheTool.read(imagePath);
        //如果磁盘中有缓存的图片，则直接将图片取出，并且返回主线程
        if (cacheBitmap != null) {
            Message obtainMessage = mHandler.obtainMessage();
            obtainMessage.obj = cacheBitmap;
            mHandler.sendMessage(obtainMessage);
            return ;
        }
        //3、从网络取
        //如果磁盘没有图片，则开启网络加载图片
        InputStream inputStream = null;
        try {
            URL url = new URL(imagePath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //请求成功
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while((len = inputStream.read(buffer)) != -1) {
                    baos.write(buffer,0,len);
                }
                baos.flush();
            }

            //将图片流转换成Bitmap对象
            byte[] bytes = baos.toByteArray();
            //二次采样
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//接下来仅仅获取图片的大小属性
            BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);//获取图片的大小，不加载图片的内容
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            //计算压缩比
            int widthRatio = outWidth/MAX_WIDTH ;
            int heightRatio = outHeight/MAX_HEIGHT;
            int ratio = widthRatio > heightRatio ? widthRatio : heightRatio;
            //加载图片的内容，并且对图片按照比率进行压缩
            options.inJustDecodeBounds = false;
            //配置图片的压缩比
            options.inSampleSize = ratio;
            //转化图片
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            //存入磁盘
            DiskCacheTool.save(imagePath,bitmap);
            //将Bitmap对象传递到主线程
            Message message = mHandler.obtainMessage();
            message.obj = bitmap;
            mHandler.sendMessage(message);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
