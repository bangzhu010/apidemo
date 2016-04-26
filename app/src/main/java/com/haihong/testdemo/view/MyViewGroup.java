package com.haihong.testdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * Created by admin on 2016/3/9.
 */
public class MyViewGroup  extends ViewGroup{
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getX();
        getY();

        ViewConfiguration.get(getContext()).getScaledTouchSlop();

    }
}
