package com.example.day28_custom_view_clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by 张样 on 2016/10/18.
 */
public class ClockView extends View{
    private Paint mPaint;
    private int hour = 4;
    private int minutes = 16;
    private int seconds = 30;
    private int width;
    private int height,center;

    public ClockView(Context context) {
        this(context,null);
    }


    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

        mHandller.sendEmptyMessage(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode){
            case MeasureSpec.AT_MOST:
                width = 200;
                break;
            case MeasureSpec.EXACTLY:
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode){
            case MeasureSpec.AT_MOST:
                height = 200;
                break;
            case MeasureSpec.EXACTLY:
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
        }
        int size = width > height ? height : width;
        /******/
        center = size /2;
        setMeasuredDimension(size,size);
    }

    /**
     * 绘制时钟表盘
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //绘制表盘
        mPaint.setStyle(Paint.Style.STROKE);
        //是指线条的宽度
        mPaint.setStrokeWidth(5);
        //消除锯齿
        mPaint.setAntiAlias(true);
        /**
         *1.圆心x
         * 2.圆心y
         *3.半径
         * 4.Paint
         */
//        canvas.drawCircle(200,200,150,mPaint);

        for (int i = 0; i < 12; i++) {
            //还原画布（提取画布中保存的内容）
            canvas.restore();
            //绘制一条直线作为12点钟方向的刻度
        /*
        *1 ,2起始点 x，y
        * 3，4结束点x,y
         */
//            canvas.drawLine(200,55,200,65,mPaint);
            if(i == 0 || i == 3 || i == 6 || i == 9){
                mPaint.setStrokeWidth(2);
                mPaint.setColor(Color.RED);
            }else{
                mPaint.setStrokeWidth(1);
                mPaint.setColor(Color.BLACK);
            }
            canvas.drawCircle(center,center/4,center/40,mPaint);
            //旋转的角度(指定旋转的圆心)
//            canvas.rotate(i * 30);
//            canvas.rotate(30,200,200);
            canvas.rotate(30,center,center);
            //保存画布中原有的内容
            canvas.save();

        }
        //绘制时针
        canvas.save();
        mPaint.setStrokeWidth(10);
        int hourDegree = hour * 30 + minutes / 2;
        canvas.rotate(hourDegree,center,center);
        canvas.drawLine(center,center,center,center*3/4,mPaint);
        canvas.restore();

        //绘制分针
        canvas.save();
        mPaint.setStrokeWidth(5);
        canvas.rotate(minutes * 6,center,center);
        canvas.drawLine(center,center,center,center*3/5,mPaint);
        canvas.restore();

        //绘制秒针
        canvas.save();
        mPaint.setStrokeWidth(1);
        canvas.rotate(seconds * 6,center,center);
        canvas.drawLine(center,center,center,center*2/5,mPaint);
        canvas.restore();
    }
    private Handler mHandller = new Handler(){


        @Override
        public void handleMessage(Message msg) {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR);
            minutes = calendar.get(Calendar.MINUTE);
            seconds = calendar.get(Calendar.SECOND);
            //刷新界面
            invalidate();
            sendEmptyMessageDelayed(1,1000);
        }

    };
}
