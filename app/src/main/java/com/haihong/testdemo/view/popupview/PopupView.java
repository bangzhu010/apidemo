package com.haihong.testdemo.view.popupview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/2/1.
 */
public class PopupView extends TextView {
    private int mWidth;
    private int mHeight;
    private float mRectPercent = 0.8f;
    private int mRectWidth;
    private int mRectHeight;

    public PopupView(Context context) {
        super(context);
    }

    public PopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
            mRectWidth = (int) (mWidth * 1.0f);//计算矩形的大小 这里是直接给的初值为0.8 也就是说矩形是view大小的0.8倍 下同
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = 250;
            mRectWidth = (int) (mWidth * 1.0f);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
            mRectHeight = (int) (mHeight * mRectPercent + 0.1);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = 250;
            mRectHeight = (int) (mHeight * mRectPercent + 0.1);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint p = new Paint();
        p.setColor(Color.parseColor("#2C97DE"));
        p.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(new RectF(0, 0, mRectWidth, mRectHeight), 10, 10, p);

        Path path = new Path();
        path.moveTo(mRectWidth / 2 - 30, mRectHeight);
        path.lineTo(mRectWidth / 2, mRectHeight + 20);
        path.lineTo(mRectWidth / 2 + 30, mRectHeight);
        path.close();
        canvas.drawPath(path, p);
        super.onDraw(canvas);
    }
}
