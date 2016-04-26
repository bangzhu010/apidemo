package com.example.liangwenchao.appdemo.ui.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.liangwenchao.appdemo.R;

/**
 * Created by admin on 2016/3/23.
 */
public class CustomImageView extends View {
    private static final int IMAGE_SCALE_FITXY = 0;

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //    <attr name="mtitleText" format="string" />
//    <attr name="mtitleTextColor" format="color" />
//    <attr name="mtitleTextSize" format="dimension" />
//    <attr name="image" format="reference"/>
//    <attr name="imageScaleType" >
//    <enum name ="fillXY" value="0"/>
//    <enum name="center" value="1"/>
//    </attr>
    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private Bitmap mImage;
    private int mImageScale;

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomIamgeView, defStyleAttr, 0);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomIamgeView_mtitleText:
                    mTitleText = typedArray.getString(attr);
                    break;
                case R.styleable.CustomIamgeView_mtitleTextColor:
                    mTitleTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomIamgeView_mtitleTextSize:
                    mTitleTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomIamgeView_imageTitle:
                    mImage = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                case R.styleable.CustomIamgeView_imageScaleType:
                    mImageScale = typedArray.getInt(attr, 0);
                    break;

            }
        }
        typedArray.recycle();

        mPaint = new Paint();
        mRect = new Rect();
        mTextBound = new Rect();

        mPaint.setTextSize(mTitleTextSize);
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTextBound);


    }

    private Paint mPaint;
    private Rect mRect;
    private Rect mTextBound;

    private int mWidth;
    private int mHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        //设置宽度
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            int desireByImg = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
            int desireByTitle = getPaddingLeft() + getPaddingRight() + mTextBound.width();

            if (widthMode == MeasureSpec.AT_MOST) {
                int desire = Math.max(desireByImg, desireByTitle);

                mWidth = Math.min(desire, widthSize);
            }


        }

        //设置高度
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mTextBound.height();
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(desire, heightSize);
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTitleTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        if (mTextBound.width() > mWidth) {


            TextPaint paint = new TextPaint();
            String msg = TextUtils.ellipsize(mTitleText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(), TextUtils.TruncateAt.END).toString();
            String mag = TextUtils.ellipsize(mTitleText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(), TextUtils.TruncateAt.END).toString();
//            Log.i("lwc", "msg = " + msg);
            canvas.drawText(mag, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);
        } else {
            canvas.drawText(mTitleText, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }


        mRect.bottom -= mTextBound.height();

        if (mImageScale == IMAGE_SCALE_FITXY) {

            canvas.drawBitmap(mImage, null, mRect, mPaint);

        } else {

            mRect.left = mWidth / 2 - mImage.getWidth() / 2;
            mRect.right = mWidth / 2 + mImage.getWidth() / 2;
            mRect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
            mRect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;
            canvas.drawBitmap(mImage, null, mRect, mPaint);
        }


    }
}
