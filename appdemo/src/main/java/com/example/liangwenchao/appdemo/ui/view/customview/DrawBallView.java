package com.example.liangwenchao.appdemo.ui.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2016/5/9.
 */
public class DrawBallView extends View{
    public DrawBallView(Context context) {
        this(context,null);
    }

    public DrawBallView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawCircle(currentX,currentY,15,paint);
    }
    private int currentX;
    private int currentY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = (int) event.getX();
        currentY = (int) event.getY();
        Log.i("lwc","currentX = " + currentX);
        Log.i("lwc","currentY = " + currentY);

        invalidate();
//        return super.onTouchEvent(event);//返回这个只处理Down事件
        return true;
    }
}
