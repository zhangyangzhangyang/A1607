package com.example.day25_image_loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 张样 on 2016/10/12.
 */
public class ImageLoader {
    private static ExecutorService executorService;
    private static  void init() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(4);
        }
    }
    public static void load(Context context , String path , ImageView imageView){
        init();
        DiskCacheTool.init(context);
        //1从内存中取数据
        Bitmap bitmap = MemoryCacheTool.read(path);
        if(bitmap == null){
            imageView.setImageResource(R.mipmap.ic_launcher);
            executorService.execute(new ImageThread(path,imageView));
        }else{
            imageView.setImageBitmap(bitmap);
        }

    }
}
