package com.example.day25_image_loader;

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
 * Created by 张样 on 2016/10/12.
 */
public class ImageThread implements Runnable{
    private String PATH;
    public static final int MAX_WIDTH = 100;
    public static final int MAX_HEIGHT = 100;
    private ImageView mImageView;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (mImageView.getTag().equals(PATH)){
                Bitmap bitmap = (Bitmap) msg.obj;
                //将图片缓存到内存中
                MemoryCacheTool.save(PATH,bitmap);
                mImageView.setImageBitmap(bitmap);
            }

        }
    };
    public ImageThread(String imagePath, ImageView mImageView) {
        this.PATH = imagePath;
        this.mImageView = mImageView;
        mImageView.setTag(imagePath);
    }

    @Override
    public void run() {
        //2、从磁盘中取
        //从磁盘中取出Bitmap对象
        Bitmap cacheBitmap = DiskCacheTool.read(PATH);
        //如果磁盘中有缓存的图片，则直接将图片取出，并且返回主线程
        if (cacheBitmap != null){
            Message obtainMessage = mHandler.obtainMessage();
            obtainMessage.obj = cacheBitmap;
            mHandler.sendMessage(obtainMessage);
            return;
        }
        //3、从网络取
        //如果磁盘没有图片，则开启网络加载图片
        InputStream inputStream = null;
        try {
            URL url = new URL(PATH);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while((len = inputStream.read(buffer)) != -1){
                    baos.write(buffer,0,len);
                }
                baos.flush();
            }
            byte[] bytes = baos.toByteArray();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            int widthRatio = outWidth/ MAX_WIDTH;
            int heightRatio = outHeight/MAX_HEIGHT;
            int ratio = widthRatio > heightRatio ? widthRatio : heightRatio;
            options.inJustDecodeBounds = false;
            options.inSampleSize = ratio;
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            Message message = mHandler.obtainMessage();
            message.obj = bitmap;
            mHandler.sendMessage(message);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
