package com.androidxx.yangjw.day25_image_loader_demo;

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
 * Created by yangjw on 2016/10/12.
 * DiskLruCache中的key的命名只能是小写的a-z0-9
 */
public class DiskCacheTool {

    private static final String TAG = "androidxx";
    private static DiskLruCache diskCache;

    /**
     * 初始化DiskLruCache对象
     * @param context
     */
    public static void init(Context context) {
        if (diskCache == null) {
            /**
             * 参数1：缓存的目录
             * 参数2：app的版本号
             * 参数3：默认值为1（表示一个key对应一个value）
             * 参数4：缓存的大小
             */
            File cacheFile = getCacheFile(context);
            Log.i(TAG, "init: " + cacheFile.getAbsolutePath());
            try {
                diskCache = DiskLruCache.open(cacheFile, 1, 1, 4 * 1024 * 1024);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取图片缓存的目录
     * @param context
     * @return
     */
    private static File getCacheFile(Context context) {
        File cacheDir = null;
        //判断外置存储是否挂载
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            cacheDir = context.getExternalCacheDir();
        } else {
            //如果没有外部存储，则取内部存储的路径
            cacheDir = context.getCacheDir();
        }

        return cacheDir;
    }


    private static String formatKey(String path) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(path.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder builder = new StringBuilder();
            for (int i = 0,len = digest.length; i < len; i++) {
                String hexString = Integer.toHexString(0xFF & digest[i]);
                if (hexString.length() == 1) {
                    builder.append("0").append(hexString);
                } else {
                    builder.append(hexString);
                }
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.valueOf(path.hashCode());

    }

    public static void save(String key, Bitmap bitmap) {
        String formatKey = formatKey(key);
        try {
            DiskLruCache.Editor editor = diskCache.edit(formatKey);
            OutputStream outputStream = editor.newOutputStream(0);
            //将Bitmap对象写入输出流中
            /**
             * compress方法有压缩的功能，但是当图片选择PNG格式，则参数是无效的
             * 参数1：存储的图片的格式
             * 参数2：压缩比率
             *返回值：true表示写入成功，false表示写入失败
             */
            boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            if (compress) {
                //如果成功，则提交
                editor.commit();//只有执行了commit之后，图片才会被写入磁盘
            } else {
                //如果失败，则撤销
                editor.abort(); //执行abort就表示图片会从内存中移除和回收
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap read(String path) {
        String formatKey = formatKey(path);
        try {
            DiskLruCache.Snapshot snapshot = diskCache.get(formatKey);
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 刷新日志文件：建议在APP退出的时候执行
     */
    public static void flush() {
        if (diskCache != null) {
            try {
                diskCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
