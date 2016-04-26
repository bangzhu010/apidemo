package com.haihong.testdemo.view.errorview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by LiangWenchao on 2016/2/22.
 */
public class ErrorView extends View {
    public ErrorView(Context context) {
        this(context, null);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int mWidth;
    private int mHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = 200;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mHeight = 200;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    private int mProgress = 0;

    private int mLineOneX = 0;
    private int mLineOneY = 0;

    private int mLineTwoX = 0;
    private int mLineTwoY = 0;

    private boolean isLineDrawDone = false;


    @Override
    protected void onDraw(Canvas canvas) {

        Paint p = new Paint();
        p.setStrokeWidth(10);
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(0 + 10, 0 + 10, mWidth - 10, mHeight - 10);
        canvas.drawArc(rectF, 180, 360 * mProgress / 100f, false, p);
        mProgress += 5;


        if (mProgress >= 100) {
            if (mLineOneX <= mWidth * 0.5) {
                mLineOneX += 20;
                mLineOneY += 20;
                canvas.drawLine(mWidth / 4, mHeight / 4, mWidth / 4 + mLineOneX, mHeight / 4 + mLineOneY, p);
            } else {

                canvas.drawLine(mWidth / 4, mHeight / 4, mWidth / 4 + mLineOneX, mHeight / 4 + mLineOneY, p);
                if (mLineTwoX < mWidth * 0.5) {
                    mLineTwoX += 20;
                    mLineTwoY += 20;
                    canvas.drawLine(mWidth / 4, (float) (mHeight * 0.75), mWidth / 4 + mLineTwoX, (float) (mHeight * 0.75) - mLineTwoY, p);
                } else {
                    isLineDrawDone = true;
                    canvas.drawLine(mWidth / 4, (float) (mHeight * 0.75), mWidth / 4 + mLineTwoX, (float) (mHeight * 0.75) - mLineTwoY, p);
                }
            }


        }

        if (isLineDrawDone) {
            Log.e("wing", "draw done");

            if (!isDrawDone) {
                if (mOnStopListener != null) {
                    mOnStopListener.onStop(this);
                }
                isDrawDone = true;
            }

        } else {

            postInvalidateDelayed(100);
        }



    }
    private  boolean isDrawDone = false;
    private  OnStopListener mOnStopListener;

    public void setOnStopListener(OnStopListener onStopListener){
        mOnStopListener = onStopListener;
    }

    public interface OnStopListener {
        void onStop(View v);
    }


    public void reSet(){

        mProgress = 0;

        mLineOneX = 0;
        mLineOneY = 0;
        mLineTwoX = 0;
        mLineTwoY = 0;

        isLineDrawDone = false;
        isDrawDone = false;
        invalidate();



    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
