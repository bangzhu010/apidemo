package com.example.liangwenchao.appdemo.ui.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.liangwenchao.appdemo.R;

/**
 * Created by admin on 2016/3/24.
 */
public class CustomProgressBar extends View {
    private int mFirstColor;
    private int mSecondColor;
    private int mRingWidth;
    private int mSpeed;

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    private int mProgress = 0;

    private boolean isNext = false;

    public CustomProgressBar(Context context) {
        this(context, null);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, defStyleAttr, 0);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);

            switch (attr) {
                case R.styleable.CustomProgressBar_firstColor:

                    mFirstColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomProgressBar_secondColor:
                    mSecondColor = typedArray.getColor(attr, Color.WHITE);
                    break;
                case R.styleable.CustomProgressBar_ringWidth:
                    mRingWidth = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomProgressBar_speed:
                    mSpeed = typedArray.getInteger(attr, 20);
                    break;
            }
        }

        typedArray.recycle();

        mPaint = new Paint();


        new Thread() {
            public void run() {

                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;

                        if (!isNext) {
                            isNext = true;
                        } else {
                            isNext = false;
                        }
                    }
                    postInvalidate();

                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();

        Log.i("lwc","mFirst = " + mFirstColor + ",mSecond = " + mSecondColor + "mRingWidth = " + mRingWidth
        + ", mSpeed = " + mSpeed);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int temp = Math.min(getHeight(), getWidth());
        int center = temp/2;
        int radius = center - mRingWidth/2;

        mPaint.setStrokeWidth(mRingWidth);//设置圆环的宽度
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setStyle(Paint.Style.STROKE);//设置空心

        RectF oval = new RectF(center-radius,center-radius,center+radius,center+radius);

        if(!isNext){
            mPaint.setColor(mFirstColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mSecondColor);
            canvas.drawArc(oval,-90,mProgress,false,mPaint);
        } else{
            mPaint.setColor(mSecondColor);
            canvas.drawCircle(center, center, radius, mPaint);
            mPaint.setColor(mFirstColor);
            canvas.drawArc(oval,-90,mProgress,false,mPaint);
        }
    }
}
