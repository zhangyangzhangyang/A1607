package com.example.day28_custom_view_attrs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 张样 on 2016/10/18.
 * 在java代码中写的代码是px单位
 */
public class Custom extends View {

    private static final String TAG = "android";
    private Paint mPaint;
    private String text;
    private int color;
    private float dimension;

    /**
     * 在java代码中闯将空间的时候，需要new控件，使用这个
     * @param context
     */
    public Custom(Context context) {
        this(context,null);
    }

    /**
     * 在布局资源文件中使用控件是，调用这
     * @param context
     * @param attrs
     */
    public Custom(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     *如果有默认是，调用
     * @param context 上下文对象
     * @param attrs 配置的属性值（属性集合）
     * @param defStyleAttr 默认的属性资源
     */
    public Custom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性的值
        //1值：已经作用于CustomView的属性集合
        //2值。属性id
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.cumtom_attr);
        text = typedArray.getString(R.styleable.cumtom_attr_customText);
        Log.i(TAG, "Custom: "+text);
        color = typedArray.getColor(R.styleable.cumtom_attr_customColor, Color.BLUE);
        dimension = typedArray.getDimension(R.styleable.cumtom_attr_customSize,30);
        mPaint = new Paint();
    }

    /**
     * 测量控件大小
     * @param widthMeasureSpec 控件宽度+模式
     * @param heightMeasureSpec
     * //        MeasureSpec.AT_MOST  wrap_content
     * //        MeasureSpec.EXACTLY  match_parent和具体的值
     * //         MeasureSpec.UNSPECIFIED  不指定控件的大小（适配器视图采用这种模式）
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(TAG, "onMeasure: "+ width);
            //指定自定义的高度和宽度
//        setMeasuredDimension(600,100);
        //宽度的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);

        switch (widthMode){
            case MeasureSpec.EXACTLY:
                setMeasuredDimension(width,height);
                break;
            case MeasureSpec.AT_MOST:
                int length = text.length();
                int totalSize = length * 70;
                setMeasuredDimension(totalSize,70);
                break;
        }
        switch (heighMode){
            case MeasureSpec.EXACTLY:
                setMeasuredDimension(width,height);
                break;
            case MeasureSpec.AT_MOST:
                setMeasuredDimension(width,70);
                break;
        }
    }

    /**
     * 在屏幕上绘制内容
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1.文本   2.文字左边距  3.文字的baseline，在距离顶部30的距离  4.画笔
        mPaint.setColor(color);
        mPaint.setTextSize(dimension);
        canvas.drawColor(Color.BLUE);
        canvas.drawText(text,10,60,mPaint);

    }
}
