package com.example.day25_image_loader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


/**
 * Created by 张样 on 2016/10/12.
 */
public class MemoryCacheTool {
    private static LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(4 * 1024 *1024){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };
    /**
     * 缓存图片到内容中
     * @param key
     * @param bitmap
     */
    public static void save(String key,Bitmap bitmap){
        lruCache.put(key,bitmap);
    }
    /**
     * 取出缓存的数据
     * @param key
     * @return
     */
    public static Bitmap read(String key){
        Bitmap bitmap = lruCache.get(key);
        return bitmap;
    }

}
