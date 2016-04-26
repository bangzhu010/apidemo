package com.haihong.testdemo.view.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.haihong.testdemo.R;

/**
 * Created by LiangWenchao on 2016/1/28.
 */
public class MyCirclePercentView extends View {
    private int mPercent;
    private int mSmallColor;
    private int mBigColor;
    private int mCenterTextSize;
    private int mRadius;
    private float mStripeWidth;
    private int mWidth;
    private int mHeight;
    private int x;
    private int y;
    private int mCurPercent;

    public MyCirclePercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /*
        *  <attr name="stripeWidth" format="dimension"/>
        <attr name="percent" format="integer"/>
        <attr name="smallColor" format="color" />
        <attr name="bigColor" format="color"/>
        <attr name="centerTextSize" format="integer" />
        <attr name="radius" format="integer"/>
        * */

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
        //获取色带的宽度
        mStripeWidth = ta.getDimension(R.styleable.CirclePercentView_stripeWidth, 5);
        mPercent = ta.getInteger(R.styleable.CirclePercentView_percent, 0);
        //获取小园的颜色
        mSmallColor = ta.getColor(R.styleable.CirclePercentView_smallColor, 0xffff0000);
        //获取大圆的颜色
        mBigColor = ta.getColor(R.styleable.CirclePercentView_bigColor, 0xff00ff00);
        //获取中心文字的大小
        mCenterTextSize = ta.getDimensionPixelSize(R.styleable.CirclePercentView_centerTextSize, 20);
        //获取园的半径
        mRadius = ta.getDimensionPixelSize(R.styleable.CirclePercentView_radius, 200);
        ta.recycle();
    }

    public MyCirclePercentView(Context context) {
        this(context, null);
    }

    public MyCirclePercentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = widthSize / 2;

            mWidth = widthSize;
            mHeight = heightSize;

            x = widthSize / 2;
            y = heightSize / 2;
        }
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            x = mRadius;
            y = mRadius;
            mWidth = (int) (mRadius * 2);
            mHeight = (int) (mRadius * 2);
        }

        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint p = new Paint();
        p.setAntiAlias(true);

        //画大圆
        p.setColor(mBigColor);
        canvas.drawCircle(x, y, mRadius, p);

        //画扇形
        RectF rf = new RectF(0, 0, mWidth, mHeight);
        p.setColor(mSmallColor);
        canvas.drawArc(rf, 270, mCurPercent, true, p);

        //画小圆
        p.setColor(mBigColor);
        canvas.drawCircle(x, y, mRadius - mStripeWidth, p);
        //画文字
        String text = mCurPercent + "%";
        p.setTextSize(mCenterTextSize);
        float textLength = p.measureText(text);
        p.setColor(Color.WHITE);
        canvas.drawText(text, x - textLength / 2, y, p);
    }

    public void setPercent(int percent) {
    //百分比不可能超过100 如果超过100则抛出异常
        if (percent > 100) {
            throw new IllegalArgumentException("percent must less than 100!");
        }
        setCurPercent(percent);

    }

    //内部设置百分比 用于动画效果
    private void setCurPercent(int percent) {

        mPercent = percent;

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= mPercent; i++) {
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurPercent = i;
                    MyCirclePercentView.this.postInvalidate();
                }
            }

        }).start();
    }
}
