package com.haihong.testdemo.view.linechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

/**
 * Created by LiangWenchao on 2016/1/28.
 */
public class SimpleLineChart extends View {
    //view的宽高
    private int mWidth;
    private int mHeight;
    //Y轴字体大小
    private float mYAxisFontSize = 24;
    //线的颜色
    private int mLineColor = Color.parseColor("#00BCD4");
    //线的宽度
    private float mStrokeWidth = 8.0f;
    //点的集合
    private HashMap<Integer, Integer> mPointMap;
    //点的半径
    private float mPointRadius = 10;
    //X轴的文字
    private String[] mXAxis = {};
    //Y轴的文字
    private String[] mYAxis = {};

    public SimpleLineChart(Context context) {
        this(context, null);
    }

    public SimpleLineChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
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
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = 250;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else if (widthMeasureSpec == MeasureSpec.AT_MOST) {
            mHeight = 250;
        }
        setMeasuredDimension(mWidth, mHeight);
    }


    private String mNoDataMsg = "没有数据";

    @Override
    protected void onDraw(Canvas canvas) {

        Log.i("com.haihong.testdemo", "onDraw()");

        if (mXAxis.length == 0 || mYAxis.length == 0) {
            throw new IllegalArgumentException("X or Y items is null");
        }

        Paint axisPaint = new Paint();
        axisPaint.setTextSize(mYAxisFontSize);
        axisPaint.setColor(Color.parseColor("#3F51B5"));


        if (mPointMap == null || mPointMap.size() == 0) {
            int textLength = (int) axisPaint.measureText(mNoDataMsg);
            canvas.drawText(mNoDataMsg, mWidth / 2 - textLength / 2, mHeight / 2, axisPaint);
        } else {//画Y轴

            //存放每个Y轴的坐标
            int[] yPoints = new int[mYAxis.length];
            //计算Y轴 每个刻度的间距
            int yInterval = (int) ((mHeight - mYAxisFontSize) / (mYAxis.length));
            //测量Y轴文字的高度 用来画第一个数
            Paint.FontMetrics fm = axisPaint.getFontMetrics();
            int yItemHeight = (int) Math.ceil(fm.descent - fm.ascent);

            for (int i = 0; i < mYAxis.length; i++) {
                Log.i("com.haihong.testdemo", "yPoints = " + (mYAxisFontSize + i * yInterval));
                canvas.drawText(mYAxis[i], 0, mYAxisFontSize + i * yInterval, axisPaint);
                yPoints[i] = (int) (mYAxisFontSize + i * yInterval);
            }

            //存放每个Y轴的坐标
            int[] xPoints = new int[mXAxis.length];
            //计算轴开始的原点坐标 X轴的起点要去掉Y轴字体所占长度
            int xItemX = (int) axisPaint.measureText(mYAxis[1]);
            int xItemY = (int) (mYAxisFontSize + mYAxis.length * yInterval);
            //X轴的间距
            int xInterval = (int) ((mWidth - xItemX) / mXAxis.length);

            for (int i = 0; i < mXAxis.length; i++) {
                canvas.drawText(mXAxis[i], i * xInterval + xItemX, xItemY - yInterval, axisPaint);
                xPoints[i] = (int) (i * xInterval + xItemX + axisPaint.measureText(mXAxis[i]) / 2);
//            Log.e("wing", xPoints[i] + "");
            }
            //画点
            Paint pointPaint = new Paint();
            pointPaint.setColor(mLineColor);

            Paint linePaint = new Paint();
            linePaint.setColor(mLineColor);
            linePaint.setAntiAlias(true);

            //设置线条宽度
            linePaint.setStrokeWidth(mStrokeWidth);
            pointPaint.setStyle(Paint.Style.FILL);
            for (int i = 0; i < mXAxis.length; i++) {
                if (mPointMap.get(i) == null) {
                    throw new IllegalArgumentException("PointMap has incomplete data!");
                }
                //画点
                canvas.drawCircle(xPoints[i], yPoints[mPointMap.get(i)], mPointRadius, pointPaint);
                if (i > 0) {
                    canvas.drawLine(xPoints[i - 1], yPoints[mPointMap.get(i - 1)], xPoints[i], yPoints[mPointMap.get(i)], linePaint);
                }
            }
        }
    }

    /**
     * 设置map数据
     *
     * @param data
     */
    public void setData(HashMap<Integer, Integer> data) {
        mPointMap = data;
        invalidate();
    }

    /**
     * 设置Y轴文字
     *
     * @param yItem
     */
    public void setYItem(String[] yItem) {
        mYAxis = yItem;
        invalidate();
    }

    /**
     * 设置X轴文字
     *
     * @param xItem
     */
    public void setXItem(String[] xItem) {
        mXAxis = xItem;
        invalidate();
    }

    public void setLineColor(int color) {
        mLineColor = color;
        invalidate();
        invalidate();
    }


}
