package com.haihong.testdemo.view.drawermenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;

/**
 * Created by LiangWenchao on 2016/1/28.
 */
public class MyDrawerMenu extends ViewGroup implements View.OnClickListener {

    public MyDrawerMenu(Context context) {
        this(context, null);
    }

    public MyDrawerMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    private boolean mIsChange = true;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        if(mIsChange){
            layoutButtom();
            int count = getChildCount();
            for (int i = 0; i < count-1; i++) {

                View view = getChildAt(i+1);

                int viewHeight = view.getMeasuredHeight();

                mUsedHeigth += viewHeight;

                view.layout(l,mHeight-mUsedHeigth,mWidth,mHeight-mUsedHeigth+viewHeight);

                view.setVisibility(GONE);
            }
        }


    }
    private View view;
    private int mWidth;
    private int mHeight;
    private int l;

    private int mUsedHeigth;

    private void layoutButtom() {

        view = getChildAt(0);

        l = 0;
        mUsedHeigth = view.getMeasuredHeight();
        mWidth = view.getMeasuredWidth();
        mHeight = getMeasuredHeight();

        view.layout(l,mHeight-mUsedHeigth,mWidth,mHeight);
        view.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        int count = getChildCount();

        if(mIsChange){

            for (int i = 0; i < count-1; i++) {
                View view = getChildAt(i+1);

                view.setVisibility(VISIBLE);
                TranslateAnimation ta = new TranslateAnimation(-view.getMeasuredWidth(),0,0,0);
                ta.setDuration(1000 + 100 *i);

                view.startAnimation(ta);
            }
            mIsChange = false;
        } else {
            for (int i = 0; i < count-1; i++) {

                View view = getChildAt(i+1);

                TranslateAnimation ta = new TranslateAnimation(0,-view.getMeasuredWidth(),0,0);
                ta.setDuration(1000 + 100 *i);
                view.startAnimation(ta);

                view.setVisibility(GONE);
            }
            mIsChange = true;

        }

    }
}
