package com.example.day28_custom_view_clock_test;

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
public class ClockView extends View {
    private Paint mPaint;
    private int width,height,center;
    private int hour = 4;
    private int minutes = 25;
    private int seconds = 20;

    public ClockView(Context context) {
        this(context,null);
    }

    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mHandler.sendEmptyMessage(1);

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
        int size = width > height ?height : width;
        center = size /2;
        setMeasuredDimension(size,size);
    }

    //绘制时钟的表盘
    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);

        //绘制钟表刻度
        for (int i = 0; i < 12; i++) {
            canvas.restore();
            if (i == 0 || i == 3|| i == 6 || i == 9){
                mPaint.setStrokeWidth(2);
                mPaint.setColor(Color.RED);
            }else{
                mPaint.setStrokeWidth(1);
                mPaint.setColor(Color.BLUE);
            }
            canvas.drawCircle(center,center/4,center/40,mPaint);
            canvas.rotate(30,center,center);
            canvas.save();
        }
        //绘制时针
        canvas.save();
        mPaint.setStrokeWidth(10);
        int hourDegree = hour * 30 + minutes / 2;
        canvas.rotate(hourDegree,center,center);
        canvas.drawLine(center,center,center,center* 3/4,mPaint);
        canvas.restore();
        //绘制分针
        canvas.save();
        mPaint.setStrokeWidth(5);
        canvas.rotate(minutes *6,center,center);
        canvas.drawLine(center,center,center,center *3/5,mPaint);
        canvas.restore();
        //绘制秒针
        canvas.save();
        mPaint.setStrokeWidth(1);
        canvas.rotate(seconds *6,center,center);
        canvas.drawLine(center,center,center,center *2/5,mPaint);
        canvas.restore();
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR);
            minutes = calendar.get(Calendar.MINUTE);
            seconds = calendar.get(Calendar.SECOND);
            //刷新画布
            invalidate();
            sendEmptyMessageDelayed(1,1000);
        }
    };
}
