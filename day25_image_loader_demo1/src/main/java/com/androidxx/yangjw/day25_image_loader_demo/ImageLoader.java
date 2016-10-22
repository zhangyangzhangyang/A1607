package com.androidxx.yangjw.day25_image_loader_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.jakewharton.disklrucache.DiskLruCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangjw on 2016/10/12.
 */
public class ImageLoader {

    private static ExecutorService executorService;

    private static void init() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(4);
        }
    }

    public static void load(Context context , String path, ImageView imageView) {
        init();
        DiskCacheTool.init(context);//初始化磁盘缓存的对象
        //1、从内存中取数据
        Bitmap bitmap = MemoryCacheTool.read(path);
        if (bitmap == null) {
            imageView.setImageResource(R.drawable.icon_closewhite);
            executorService.execute(new ImageThreadd(path,imageView));
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }
}
