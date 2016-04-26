package com.haihong.testdemo.view.waveview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.haihong.testdemo.R;
import com.haihong.testdemo.view.utils.PxUtils;

/**
 * Created by LiangWenchao on 2016/2/1.
 */
public class WaveView extends View {
    private static final int MODE_TRIANGLE = -1;
    private final int mWaveCount;
    private final int mWaveWidth;
    private int mMode;
    private int mColor;
    private Context mContext;
    private int mWaveHeight;
    private float mRadius;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WaveView, defStyleAttr, 0);
        mWaveCount = a.getInt(R.styleable.WaveView_waveCount, 10);
        mWaveWidth = a.getInt(R.styleable.WaveView_waveWidth, 20);
        mMode = a.getInteger(R.styleable.WaveView_mode, -2);
        mColor = a.getColor(R.styleable.WaveView_android_color, Color.parseColor("#2C97DE"));

    }

    private int mWidth;
    private int mHeight;
    private float mRectWidth;
    private float mRectHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //矩形宽度为view的80%
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
            mRectWidth = (float) (mWidth * 0.8);

            //如果是wrap_content 直接给一个定值
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = PxUtils.dpToPx(300, mContext);
            mRectWidth = (float) (mWidth * 0.8);
        }
        //矩形高度为view的80%
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
            mRectHeight = (float) (mHeight * 0.8);

            //如果是wrap_content 直接给一个定值
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = PxUtils.dpToPx(200, mContext);
            mRectHeight = (float) (mHeight * 0.8);
        }

        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(mColor);

        mWaveHeight = (int) (mRectWidth / mWaveCount);
        //计算padding
        float padding = ((mWidth - mRectWidth) / 2);

        //画矩形
        canvas.drawRect(padding, padding, mRectWidth + padding, mRectHeight + padding, p);

        if (mMode == MODE_TRIANGLE) {
            //画左边波浪
            Path path = new Path();
            float startX = padding;
            float startY = padding;

            path.moveTo(startX, startY);
            for (int i = 0; i < mWaveCount; i++) {
                path.lineTo(startX - mWaveWidth, startY + i * mWaveHeight + (mWaveHeight / 2));
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path, p);

            //画右边波浪
            startX = mRectWidth + padding;
            startY = padding;
            path.moveTo(mRectWidth + padding, padding);
            for (int i = 0; i < mWaveCount; i++) {
                path.lineTo(startX + mWaveWidth, startY + mWaveHeight / 2 + i * mWaveHeight);
                path.lineTo(startX, startY + mWaveHeight * (i + 1));
            }
            path.close();
            canvas.drawPath(path, p);
        } else {
            mRadius = mRectHeight / mWaveCount/2;
            //绘制右边波浪 
            float startX = padding + mRectWidth;
            float startY = padding;
            for (int i = 0; i < mWaveCount; i++) {
                canvas.drawCircle(startX, startY + i * mRadius * 2 + mRadius, mRadius, p);
            }


            //绘制坐标波浪 
            startX = padding;
            startY = padding;
            for (int i = 0; i < mWaveCount; i++) {
                canvas.drawCircle(startX, startY + i * mRadius * 2 + mRadius, mRadius, p);
            }

        }
    }
}
