package com.example.day29_custom_view_danmu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 张样 on 2016/10/19.
 */
public class DanmuView extends View {
    
    private Random random;
    private List<DanBean> danBeanList;
    private Paint mPaint;

    public DanmuView(Context context) {
        this(context,null);
    }

    public DanmuView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DanmuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        random = new Random();
        danBeanList = new ArrayList<>();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int nextInt = random.nextInt(200);
        DanBean danBean = new DanBean("弹幕" + nextInt, 600, nextInt);
        danBeanList.add(danBean);
        for (int i = 0; i < danBeanList.size(); i++) {
            DanBean danBean1 = danBeanList.get(i);
            danBean1.move(canvas,mPaint);
        }
        invalidate();
    }
}
