package com.example.planegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 张样 on 2016/10/19.
 */
public class BulletBean {
    private Bitmap bitmap;
    private float startX,startY;

    public BulletBean(Bitmap bitmap, float startX, float startY) {
        this.bitmap = bitmap;
        this.startX = startX;
        this.startY = startY;
    }
    public float getStartY(){
        return  startY;
    }
    public float getStartX(){
        return startX;
    }
    public void move(Canvas canvas, Paint paint){
        //子弹向前移动20个像素
        startY -= 40;
        canvas.drawBitmap(bitmap,startX,startY,paint);
    }
}
