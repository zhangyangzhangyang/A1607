package com.example.day29_custom_viewgroup_flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 张样 on 2016/10/19.
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);

        }
        //计算当前容器的高度
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //流式布局的宽度不用处理 ，等于父容器的宽度
//        switch (widthMode){
//            case MeasureSpec.EXACTLY:
//                //直接指定父容器传入的宽度
//                break;
//            case MeasureSpec.AT_MOST:
//                //需要计算宽度
//                break;
//        }

        switch (heightMode){
            case MeasureSpec.AT_MOST:
                int totalWidth = 0;
                int rowNum = 1;
                int measuredHeight =0;
                for (int i = 0; i < childCount; i++) {
                    View view = getChildAt(i);
                    int measuredWidth = view.getMeasuredWidth();
                    measuredHeight = view.getMeasuredHeight();
                    totalWidth += measuredWidth;
                    if(totalWidth > width){
                        rowNum++;
                        totalWidth = measuredWidth;
                    }
                }
                height = rowNum * measuredHeight;
                break;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        int totalWidth = 0;
        int top = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            //获取view的大小
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            totalWidth += measuredWidth;
            if (totalWidth > r){
                top += measuredHeight;
                totalWidth = measuredWidth;
            }
            //view的位置布局
            view.layout(totalWidth - measuredWidth,top,totalWidth,top+ measuredHeight);
        }
    }
}
