package com.example.day25_image_loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 张样 on 2016/10/12.
 */
public class DiskCacheTool {
    private static DiskLruCache diskCache;

    public static void init(Context context){
        /**
         * 1.缓存的目录
         * 2.app版本号
         * 3.默认1（一个key对应一个value
         * 4.缓存的分配大小
         */
        File cacheFile = getCacheFile(context);
        Log.i("android++++++++", "init: " + cacheFile.getAbsolutePath());
        try {
            diskCache = DiskLruCache.open(cacheFile,1,1,4*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static File getCacheFile(Context context){
        File cacheDir= null;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            cacheDir = context.getExternalCacheDir();
        }else{
            cacheDir = context.getCacheDir();
        }
        return cacheDir;
    }
    private static String formatKey(String path){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(path.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                String hexString = Integer.toHexString(0xFF & digest[i]);
                if (hexString.length() == 1){
                    builder.append("0").append(hexString);
                }else{
                    builder.append(hexString);
                }
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.valueOf(path.hashCode());
    }
    public static  void save(String key, Bitmap bitmap){
        String formatKey = formatKey(key);
        try {
            DiskLruCache.Editor editor = diskCache.edit(formatKey);
            OutputStream outputStream = editor.newOutputStream(0);
            /**
             * compress：有压缩功能，当图片选择了png，参数2无效
             * 1存储格式
             * 2.压缩比例。100完全不压缩
             * 返回值：true表示写入成功，false表示写入失败.
             */
            boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            if(compress){
                editor.commit();
            }else{
                editor.abort();//撤销
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Bitmap read(String path){
        String formatKey = formatKey(path);
        try {
            DiskLruCache.Snapshot snapshot = diskCache.get(formatKey);
            if(snapshot != null){
                InputStream inputStream = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void flush(){
        if(diskCache != null){
            try {
                diskCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
