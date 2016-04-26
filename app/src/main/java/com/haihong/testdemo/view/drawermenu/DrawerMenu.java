package com.haihong.testdemo.view.drawermenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

/**
 * Created by LiangWenchao on 2016/1/28.
 */
public class DrawerMenu extends ViewGroup implements View.OnClickListener {
    private int mButtonY;
    private boolean mIsChanged = true;

    public DrawerMenu(Context context) {
        this(context, null);
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (mIsChanged) {
            layoutBottom();
            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                Log.i("lwc","childHeight = " + childHeight);

                Log.i("lwc","l = 0");
                Log.i("lwc","t = " + mButtonY +" - " +mHeight_button_buttom * (i + 1)*2 +" = " + (mButtonY - mHeight_button_buttom * (i + 1)*2));
                Log.i("lwc","r = " + childWidth);
                Log.i("lwc", "b = " + getMeasuredHeight());
                child.layout(0, mButtonY - mHeight_button_buttom * (i + 1) * 1, childWidth, mButtonY - mHeight_button_buttom * (i + 1) * 1 +childHeight);
//                child.setVisibility(GONE);

                Log.i("lwc", "____________________" + (i + 2) + "_________________");

            }

        }

    }

    private View mButton_buttom;
    private int mWidth_button_buttom;
    private int mHeight_button_buttom;
    private int mButtonX;

    private void layoutBottom() {
        mButton_buttom = getChildAt(0);
        mButton_buttom.setOnClickListener(this);
        mWidth_button_buttom = mButton_buttom.getMeasuredWidth();
        mHeight_button_buttom = mButton_buttom.getMeasuredHeight();
        mButtonX = 0;
        mButtonY = getMeasuredHeight() - mHeight_button_buttom;
        Log.i("lwc","l = 0");
        Log.i("lwc","t = " +getMeasuredHeight() +" - " +mHeight_button_buttom +" = " + mButtonY);
        Log.i("lwc","r = " + mWidth_button_buttom);
        Log.i("lwc","b = " + getMeasuredHeight());
        mButton_buttom.layout(mButtonX, mButtonY, mWidth_button_buttom, getMeasuredHeight());
        Log.i("lwc", "__________________1___________________");
    }


    @Override
    public void onClick(View v) {
//        toggleMenu();
    }

    private void toggleMenu() {

        if (mIsChanged) {
            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                TranslateAnimation ta = new TranslateAnimation(-child.getMeasuredWidth(), 0, 0, 0);
                ta.setDuration(1000 + i * 100);
                child.startAnimation(ta);
                child.setVisibility(VISIBLE);

                mIsChanged = false;
            }
        } else {

            int count = getChildCount();
            for (int i = 0; i < count - 1; i++) {
                View child = getChildAt(i + 1);
                TranslateAnimation ta = new TranslateAnimation(0, -child.getMeasuredWidth(), 0, 0);
                ta.setDuration(1000 + i * 100);
                child.startAnimation(ta);
                child.setVisibility(GONE);

                mIsChanged = true;
            }
        }

    }
}