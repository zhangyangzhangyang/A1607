package com.example.day29_custom_viewgroup_linearlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 张样 on 2016/10/19.
 */
public class CustomLinearLaout extends ViewGroup{
    public CustomLinearLaout(Context context) {
        this(context,null);
    }

    public CustomLinearLaout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomLinearLaout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();//获得所有子控件的个数
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            //通知子控件计算自身的大小
            /**
             * UNSPECIFIED:为指定大小，控件的大小由控件本身自己计算
             */
            view.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
        }
    }

    /**
     * 决定子控件摆放的位置
     * @param changed 当子控件动态发生变化的时候，返回true，否则返回alse
     * @param l left 表示容器的左边的坐标
     * @param t top 表示容器的上边的坐标
     * @param r right 表示右边的坐标
     * @param b botton 表示容器下边的坐标
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取子控件的个数
        int childCount = getChildCount();
        int totalHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            //获取子控件自身的大小
            int measuredHeight = view.getMeasuredHeight();
            int measuredWidth = view.getMeasuredWidth();
            //设置view的位置
            /**
             * 参数：分别设置view的上下左右边的边线坐标
             * 配置控件的位置，不应该使用此方法控制控件的大小
             */
            totalHeight += measuredHeight;
            view.layout(0,totalHeight - measuredHeight,measuredWidth,totalHeight);
//            view.layout(100,);
        }
    }
}
