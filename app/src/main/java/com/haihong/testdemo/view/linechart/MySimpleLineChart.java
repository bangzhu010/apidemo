package com.haihong.testdemo.view.linechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/1/29.
 */
public class MySimpleLineChart extends View {
    private static final String NODATA = "没有数据";
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

    public MySimpleLineChart(Context context) {
        super(context);
    }

    public MySimpleLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySimpleLineChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }

        if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = 250;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            mHeight = 250;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mXAxis.length <= 0 || mYAxis.length <= 0) {
            throw new IllegalArgumentException("X or Y items is null");
        }

        Paint axisPaint = new Paint();
        axisPaint.setTextSize(mYAxisFontSize);
        axisPaint.setColor(Color.parseColor("#3F51B5"));

        if (mPointMap == null || mPointMap.size() == 0) {
            int textLenght = (int) axisPaint.measureText(NODATA);
            canvas.drawText(NODATA, mWidth / 2 - textLenght / 2, mHeight / 2, axisPaint);
        } else {

            /*
                画Y轴
             */
            int[] yPoints = new int[mYAxis.length];
            int yInterval = mWidth / mYAxis.length;
            //测量Y轴文字的高度 用来画第一个数
            Paint.FontMetrics fm = axisPaint.getFontMetrics();
            int yItemHeight = (int) Math.ceil(fm.descent - fm.ascent);

            for (int i = 0; i < mYAxis.length; i++) {
                canvas.drawText(mYAxis[i], 0, i * yInterval + yItemHeight, axisPaint);
                yPoints[i] = (int) (yItemHeight + i * yInterval);
                ;
            }

            /*
                画X轴
             */

            int[] xPonits = new int[mXAxis.length];
            int xItemX = (int) (axisPaint.measureText(mYAxis[1]));
            int xItemY = (int) (mYAxis.length * yInterval);

            int xOffset = 50;

            int xInterval = (mWidth - xOffset) / mXAxis.length;

            for (int i = 0; i < mXAxis.length; i++) {
                canvas.drawText(mXAxis[i], xItemX + xOffset + xInterval * i, xItemY, axisPaint);
                xPonits[i] = (int) (xItemX + xOffset + xInterval * i);
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
                canvas.drawCircle(xPonits[i], yPoints[mPointMap.get(i)], mPointRadius, pointPaint);

                if (i > 0) {
                    canvas.drawLine(xPonits[i - 1], yPoints[mPointMap.get(i - 1)], xPonits[i], yPoints[mPointMap.get(i)], linePaint);
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
    }
}
