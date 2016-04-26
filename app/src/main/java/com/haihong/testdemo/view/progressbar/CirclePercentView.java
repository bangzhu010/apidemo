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
public class CirclePercentView extends View {


    public CirclePercentView(Context context) {
        this(context, null);
    }

    public CirclePercentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private float mStripeWidth;//色带宽度
    private int mPercent;//
    private int mBigColor;
    private int mRadius;
    private
    int mSmallColor;
    private int mCenterTextSize;

    public CirclePercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CirclePercentView, defStyleAttr, 0);
        //获取色带的宽度
        mStripeWidth = ta.getDimension(R.styleable.CirclePercentView_stripeWidth, 50);
        mPercent = ta.getInteger(R.styleable.CirclePercentView_percent, 0);
        //获取小园的颜色
        mSmallColor = ta.getColor(R.styleable.CirclePercentView_smallColor, 0xffafb4db);
        //获取大圆的颜色
        mBigColor = ta.getColor(R.styleable.CirclePercentView_bigColor, 0xff6950a1);
        //获取中心文字的大小
        mCenterTextSize = ta.getDimensionPixelSize(R.styleable.CirclePercentView_centerTextSize, 20);
        //获取园的半径
        mRadius = ta.getDimensionPixelSize(R.styleable.CirclePercentView_radius, 200);
        ta.recycle();
    }

    private int x;
    private int y;
    private int mWidth;
    private int mHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        }
        if(widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            mWidth = (int) (mRadius*2);
            mHeight = (int) (mRadius*2);
            x = mRadius;
            y = mRadius;
        }
        setMeasuredDimension(mWidth,mHeight);
    }
    private int mCurPercent;
    private int mEndAngle;

    @Override
    protected void onDraw(Canvas canvas) {

        mEndAngle = (int)(mCurPercent * 3.6);

        //画大圆
        Paint bigCirclePaint = new Paint();
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setColor(mBigColor);

        canvas.drawCircle(x, y, mRadius, bigCirclePaint);

        //画扇型，大小同圆一样
        Paint sectorPaint = new Paint();
        sectorPaint.setAntiAlias(true);
        sectorPaint.setColor(mSmallColor);

        RectF rf = new RectF(0,0,mWidth,mHeight);

        canvas.drawArc(rf,270,mCurPercent,true,sectorPaint);

        //画小圆覆盖
        Paint smallCirclePaint = new Paint();
        smallCirclePaint.setColor(mBigColor);
        smallCirclePaint.setAntiAlias(true);

        canvas.drawCircle(x,y,mRadius-mStripeWidth,smallCirclePaint);

        //画文本
        Paint textPaint = new Paint();
        String text = mCurPercent + "%";
        textPaint.setTextSize(mCenterTextSize);

        //测量字符串长度
        float textLength = textPaint.measureText(text);

        textPaint.setColor(Color.WHITE);
        //把文本画在圆心居中
        canvas.drawText(text, x - textLength / 2, y, textPaint);
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
                for(int i =0;i<=mPercent;i++){
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mCurPercent = i;
                    CirclePercentView.this.postInvalidate();
                }
            }

        }).start();

    }
}
