package com.example.planegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 张样 on 2016/10/19.
 */
public class PlaneView extends View {
    private static final String TAG = "android++";
    private Paint mPaint;
    private Bitmap bitmap;
    private float x;
    private float y;
    private List<BulletBean> bulletBeanList;
    private List<EnemyBean> enemyBeanList;
    private Bitmap bulletBitmap;
    private int count;
    private int counter;
    private Bitmap enemyBitmap;
    private Random random;
    private float bulletEndY;
    private float bulleEndtX;
    private float enemyEndX;
    private float enemyEndY;

    public PlaneView(Context context) {
        this(context,null);
    }

    public PlaneView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlaneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.myplane);
        bulletBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
        bulletBeanList =new ArrayList<>();
        enemyBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.enemy2_fly_1);
        enemyBeanList = new ArrayList<>();
        random = new Random();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制图片（画板颜色是白色)
        canvas.drawColor(Color.WHITE);
        /**
         * 参数1：图片Bitmap对象
         * 参数2：图片的左边距
         * 参数3：图片的友边距
         * 参数4：画笔对象
         */
        canvas.drawBitmap(bitmap,x-20,y-40,mPaint);
        //发射子弹
        if (count % 5 ==0){
            BulletBean bulletBean = new BulletBean(bulletBitmap, x, y);
            bulletBeanList.add(bulletBean);
        }
        for (int i = 0; i < bulletBeanList.size(); i++) {
            BulletBean bulletBean = bulletBeanList.get(i);
            bulletBean.move(canvas,mPaint);
            if (bulletBean.getStartY() < -10){
                bulletBeanList.remove(i);
            }
            bulletEndY = bulletBean.getStartY();
            bulleEndtX = bulletBean.getStartX();
            Log.i(TAG, "onDraw:bulletEndY "+bulletEndY);
//            Log.i(TAG, "onDraw:bulleEndtX "+bulleEndtX);
        }
        count++;

        //发射敌机
//        int nextInt = random.nextInt(800);
        if (counter++ % 40 ==0){
//            EnemyBean enemyBean = new EnemyBean(enemyBitmap, nextInt, 0);
            EnemyBean enemyBean = new EnemyBean(enemyBitmap, 100, 0);
            enemyBeanList.add(enemyBean);
        }
        for (int i = 0; i < enemyBeanList.size(); i++) {
            EnemyBean enemyBean = enemyBeanList.get(i);
            enemyBean.move(canvas,mPaint);
            if (enemyBean.getStartY() > 2000){
                enemyBeanList.remove(i);
            }
            enemyEndX = enemyBean.getStartX();
            enemyEndY = enemyBean.getStartY();
//            Log.i(TAG, "onDraw:enemyEndX "+enemyEndX);
//            Log.i(TAG, "onDraw:enemyEndY "+enemyEndY);
//            Log.i(TAG, "onDraw:bulletEndY "+bulletEndY);
            if (bulletEndY == 0 ){
                Log.i(TAG, "onDraw: ++++++++++++++++++++");
//                enemyBeanList.remove(i);
            }
//            if (enemyEndY ==bulletEndY && enemyEndX< bulleEndtX && bulleEndtX< (enemyEndX + 30)){
//            enemyBeanList.remove(i);
//            }
        }



        //刷新界面
        invalidate();
    }
}
