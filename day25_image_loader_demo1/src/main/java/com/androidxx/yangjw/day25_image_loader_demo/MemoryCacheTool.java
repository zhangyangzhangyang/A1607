package com.androidxx.yangjw.day25_image_loader_demo;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.BaseAdapter;

/**
 * Created by yangjw on 2016/10/12.
 */
public class MemoryCacheTool {

    /**
     * 参数：最大的缓存空间
     */
    private static LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(4 * 1024 * 1024) {
        /**
         * 计算每一个缓存进来的图片的大小
         * @param key
         * @param value
         * @return
         */
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
    public static void save(String key,Bitmap bitmap) {
        lruCache.put(key,bitmap);
    }

    /**
     * 取出缓存的数据
     * @param key
     * @return
     */
    public static Bitmap read(String key) {
        Bitmap bitmap = lruCache.get(key);
        return bitmap;
    }

}
