package com.haihong.testdemo.view.drawermenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2016/1/28.
 */
public class MyDrawerMenuTest extends ViewGroup implements View.OnClickListener {
    public MyDrawerMenuTest(Context context) {
        super(context);
    }

    public MyDrawerMenuTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyDrawerMenuTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private boolean mIsChange = true;
    private int mUsedHeight;
    private int mHeight;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(mIsChange){
            int count = getChildCount();
            mHeight = getMeasuredHeight();
            mUsedHeight = 0;//防止onLayout连续调用两次
            for (int i = 0; i < count; i++) {
                View view = getChildAt(i);
                mUsedHeight  += view.getMeasuredHeight();

                Log.i("lwc","l = 0");
                Log.i("lwc","t = " + mHeight +" - " + mUsedHeight + " = " + (mHeight - mUsedHeight));
                Log.i("lwc","r = " + view.getMeasuredWidth());
                Log.i("lwc", "b = " + (mHeight - mUsedHeight + view.getMeasuredHeight()));
                Log.i("lwc", "============================" + i + "=====================================");

                view.layout(0,mHeight - mUsedHeight,view.getMeasuredWidth(),mHeight-mUsedHeight+view.getMeasuredHeight());

                if( i==0){
                    view.setOnClickListener(this);
                } else {
                    view.setVisibility(GONE);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int count = getChildCount();
        if(mIsChange){
            for (int i = 0; i < count-1; i++) {
                View view = getChildAt(i+1);
                TranslateAnimation ta = new TranslateAnimation(-view.getMeasuredWidth(),0,0,0);
                ta.setDuration(2000 + i * 1000);
                view.startAnimation(ta);
                view.setVisibility(VISIBLE);
            }
            mIsChange = false;
        } else {
            for (int i = 0; i < count-1; i++) {
                View view = getChildAt(i+1);
                TranslateAnimation ta = new TranslateAnimation(0,-view.getMeasuredWidth(),0,0);
                ta.setDuration(2000 + i * 1000);
                view.startAnimation(ta);
                view.setVisibility(GONE);
            }
            mIsChange = true;
        }
    }
}
