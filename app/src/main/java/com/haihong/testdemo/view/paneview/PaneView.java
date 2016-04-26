package com.haihong.testdemo.view.paneview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.haihong.testdemo.R;
import com.haihong.testdemo.view.utils.PxUtils;

/**
 * Created by LiangWenchao on 2016/2/1.
 */
public class PaneView extends View {

    private int mWidth;
    private int mHeight;
    private int mPercent;
    //刻度宽度
    private float mTikeWidth;
    //第二个弧的宽度
    private int mScendArcWidth;
    //最小圆的半径
    private int mMinCircleRadius;
    //文字矩形的宽
    private int mRectWidth;
    //文字矩形的高
    private int mRectHeight;
    //文字内容
    private String mText = "";
    //文字的大小
    private int mTextSize;
    //设置文字颜色
    private int mTextColor;
    private int mArcColor;
    //小圆和指针颜色
    private int mMinCircleColor;
    //刻度的个数
    private int mTikeCount;
    private Context mContext;

    public PaneView(Context context) {
        this(context, null);
    }

    public PaneView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PanelView, defStyleAttr, 0);
        mArcColor = a.getColor(R.styleable.PanelView_arcColor, Color.parseColor("#5FB1ED"));
        mMinCircleColor = a.getColor(R.styleable.PanelView_pointerColor, Color.parseColor("#C9DEEE"));
        mTikeCount = a.getInt(R.styleable.PanelView_tikeCount, 12);
        mTextSize = a.getDimensionPixelSize(PxUtils.spToPx(R.styleable.PanelView_android_textSize, mContext), 24);
        mText = a.getString(R.styleable.PanelView_android_text);
        mScendArcWidth = 60;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = PxUtils.dpToPx(200, mContext);
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = PxUtils.dpToPx(200, mContext);
        }
        Log.e("wing", mWidth + "");
        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("lwc", "onDraw");

        //画外面大圆弧
        int strokeWidth = 5;

        Paint p = new Paint();
        p.setStrokeWidth(strokeWidth);
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(mArcColor);

        canvas.drawArc(new RectF(strokeWidth, strokeWidth, mWidth - strokeWidth, mHeight - strokeWidth), 145, 250, false, p);

        //画粗弧
        RectF secondRectF = new RectF(strokeWidth + 100, strokeWidth + 100, mWidth - strokeWidth - 100, mHeight - strokeWidth - 100);

        float secondRectWidth = mWidth - strokeWidth - 100 - (strokeWidth + 100);
        float secondRectHeight = mHeight - strokeWidth - 100 - (strokeWidth + 100);


        p.setStrokeWidth(mScendArcWidth);

        float percent = mPercent / 100f;
        Log.i("lwc", "percent = " + percent);


        //充满的圆弧的度数    -5是大小弧的偏差
        float fill = 250 * percent;

        Log.i("lwc", "fill = " + fill);

        //空的圆弧的度数
        float empty = 250 - fill;
        Log.i("lwc", "empty = " + empty);

        if (percent == 0) {
            p.setColor(Color.WHITE);
        }
        //画粗弧突出部分左端
        canvas.drawArc(secondRectF, 135, 11, false, p);

        Log.i("lwc", " fill = " + fill);

        p.setColor(Color.WHITE);

        if (percent != 0) {
            canvas.drawArc(secondRectF, 145, fill, false, p);
        }

        p.setColor(Color.WHITE);
        //画弧胡的未充满部分
        canvas.drawArc(secondRectF, 145 + fill, empty, false, p);


        //画粗弧突出部分右端
        if (percent == 1) {
            p.setColor(mArcColor);
        }
        canvas.drawArc(secondRectF, 144 + fill + empty, 10, false, p);


        p.setColor(mArcColor);
        //绘制小圆外圈
        p.setStrokeWidth(strokeWidth);
        canvas.drawCircle(mWidth / 2, mHeight / 2, 60, p);


        //绘制小圆内圈
        p.setColor(mMinCircleColor);
        p.setStrokeWidth(15);
        p.setColor(Color.WHITE);
        mMinCircleRadius = 30;
        canvas.drawCircle(mWidth / 2, mHeight / 2, mMinCircleRadius, p);

        //绘制刻度！
        p.setColor(mArcColor);
        //绘制第一条最上面的刻度
        mTikeWidth = 30;
        p.setStrokeWidth(strokeWidth);

        canvas.drawLine(mWidth / 2, strokeWidth, mWidth / 2, mTikeWidth, p);


        float rAngle = 250f / mTikeCount;
        //通过旋转画布 绘制右面的刻度
        for (int i = 0; i < mTikeCount / 2; i++) {
            canvas.rotate(rAngle, mWidth / 2, mHeight / 2);
            canvas.drawLine(mWidth / 2, strokeWidth, mWidth / 2, mTikeWidth, p);
        }

        canvas.rotate(-rAngle * mTikeCount / 2, mWidth / 2, mHeight / 2);

        //通过旋转画布 绘制右面的刻度
        for (int i = 0; i < mTikeCount / 2; i++) {
            canvas.rotate(-rAngle, mWidth / 2, mHeight / 2);
            canvas.drawLine(mWidth / 2, strokeWidth, mWidth / 2, mTikeWidth, p);
        }
        //将画布旋转回来
        canvas.rotate(rAngle * mTikeCount / 2, mWidth / 2, mHeight / 2);

        //绘制指针
        p.setColor(Color.WHITE);
        p.setStrokeWidth(4);
        //按照百分比绘制刻度
        canvas.rotate((250 * percent - 250 / 2), mWidth / 2, mHeight / 2);
        canvas.drawLine(mWidth / 2, (mHeight / 2 - secondRectHeight / 2) + mScendArcWidth / 2 + 2, mWidth / 2, mHeight / 2 - mMinCircleRadius, p);
        //将画布旋转回来
        canvas.rotate(-(250 * percent - 250 / 2), mWidth / 2, mHeight / 2);


        p.setStyle(Paint.Style.FILL);
        p.setColor(mArcColor);
        mRectWidth = 80;
        mRectHeight = 30;

        //文字矩形的最底部坐标
        float rectBottomY = mHeight / 2 + secondRectHeight / 3 + mRectHeight;
        canvas.drawRect(mWidth / 2 - mRectWidth / 2, mHeight / 2 + secondRectHeight / 3, mWidth / 2 + mRectWidth / 2, rectBottomY, p);


        p.setTextSize(mTextSize);
        mTextColor = Color.WHITE;
        p.setColor(mTextColor);
        if(mPercent==100){
            mText = "已经完成";
        }else{
            mText = mPercent + "%";
        }
        float txtLength = p.measureText(mText);
        canvas.drawText(mText, (mWidth - txtLength) / 2, rectBottomY + 40, p);
//
        super.onDraw(canvas);
    }


    /**
     * 设置百分比
     *
     * @param percent
     */

    public void setPercent(int percent) {
        mPercent = percent;
        invalidate();
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText(String text) {
        mText = text;
        invalidate();
    }

    /**
     * 设置圆弧颜色
     *
     * @param color
     */

    public void setArcColor(int color) {
        mArcColor = color;

        invalidate();
    }


    /**
     * 设置指针颜色
     *
     * @param color
     */
    public void setPointerColor(int color) {
        mMinCircleColor = color;

        invalidate();
    }

    /**
     * 设置文字大小
     *
     * @param size
     */
    public void setTextSize(int size) {
        mTextSize = size;

        invalidate();
    }

    /**
     * 设置粗弧的宽度
     *
     * @param width
     */
    public void setArcWidth(int width) {
        mScendArcWidth = width;

        invalidate();
    }


}
