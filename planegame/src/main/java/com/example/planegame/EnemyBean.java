package com.example.planegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 张样 on 2016/10/19.
 */
public class EnemyBean {
    private Bitmap bitmap;
    private float startX,startY;

    public EnemyBean(Bitmap bitmap,float startX, float startY) {
        this.startX = startX;
        this.startY = startY;
        this.bitmap = bitmap;
    }

    public float getStartY() {
        return startY;
    }
    public float getStartX(){
        return startX;
    }
    public void move(Canvas canvas, Paint paint){
        startY += 10;
        canvas.drawBitmap(bitmap,startX,startY,paint);
    }
}
