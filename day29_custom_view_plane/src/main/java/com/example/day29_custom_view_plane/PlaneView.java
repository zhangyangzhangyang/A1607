package com.example.day29_custom_view_plane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张样 on 2016/10/19.
 */
public class PlaneView extends View {
    private Paint mPaint;
    private Bitmap bitmap;
    private float x;
    private float y;
    private Bitmap bulletBitmap;
    private List<Bullet> bulletList;
    private long counter;

    public PlaneView(Context context) {
        this(context,null);
    }

    public PlaneView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlaneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.myplane);
        bulletBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        bulletList = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                break;
        }
        return true;
    }

    //绘制图片
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        //绘制图片
        /**
         * 1,图片的bitmap对象
         * 2,图片的左边距
         * 3,图片的右边距
         * 4,画笔对象
         */
        canvas.drawBitmap(bitmap,x-33,y-40,mPaint);
        //发射子弹
        if(counter % 10 == 0){
            Bullet bullet = new Bullet(bulletBitmap,x ,y - 40);
            bulletList.add(bullet);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            bullet.move(canvas,mPaint);
            if(bullet.getStartY() < -10){
                bulletList.remove(i);
            }
        }
        counter++;
        //刷新界面
        invalidate();
    }
}
