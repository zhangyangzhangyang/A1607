package com.example.day29_custom_view_plane;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 张样 on 2016/10/19.
 */
public class Bullet {

    private Bitmap bitmap;
    private float startX,startY;

    public Bullet(Bitmap bitmap,float startX, float startY) {
        this.startY = startY;
        this.startX = startX;
       this.bitmap = bitmap;
    }
    public float getStartY() {
        return startY;
    }
    public void move(Canvas canvas, Paint paint){
        //子弹向前移动20像素
        startY -= 20;
        canvas.drawBitmap(bitmap,startX,startY,paint);

    }
}
