package com.haihong.testdemo.view.SuccessView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2016/2/29.
 */
public class SuccessView extends View {

    public SuccessView(Context context) {
        this(context, null);
    }

    public SuccessView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuccessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = 200;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = 200;
        }

        setMeasuredDimension(mWidth, mHeight);

    }

    private int mWidth;
    private int mHeight;

    private int mStrokeWidth = 10;

    @Override
    protected void onDraw(Canvas canvas) {

        Paint p = new Paint();

        Path path = new Path();

        p.setColor(Color.parseColor("#99999999"));
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(mStrokeWidth);
        p.setAntiAlias(true);

        RectF rf = new RectF(mStrokeWidth,mStrokeWidth,mWidth-mStrokeWidth,mHeight-mStrokeWidth);
        canvas.drawArc(rf,180,360,false,p);

        if (canStartDraw) {
                isDrawing = true;
        } else {
            p.setColor(Color.WHITE);

            canvas.drawLine(mWidth / 2, mHeight / 4, mWidth / 2, mHeight * 3 / 4, p);

            path.moveTo(mWidth/4,mHeight/2);
            path.lineTo(mWidth/2,mHeight*3/4);
            path.lineTo(mWidth*3/4,mHeight/2);

            canvas.drawPath(path,p);

        }



    }
    private boolean canStartDraw = false;
    private boolean isDrawing = false;

}


