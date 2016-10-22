package com.example.day29_custom_view_danmu;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 张样 on 2016/10/19.
 */
public class DanBean {

    private String text;
    private float x,y;

    public DanBean(String text, float x, float y) {
        this.text = text;
        this.y = y;
        this.x = x;
    }
    public void move(Canvas canvas, Paint paint){
        x -= 20;
        canvas.drawText(text,x,y+30,paint);
    }
}
