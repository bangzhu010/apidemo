package com.haihong.testdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 2016/3/9.
 */
public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        VelocityTracker tracker = VelocityTracker.obtain();
//        tracker.addMovement(event);
//
//        tracker.computeCurrentVelocity(1000);
//
//        int trackerX = (int) tracker.getXVelocity();
//        int trackerY = (int) tracker.getYVelocity();
//
//        tracker.clear();
//        tracker.recycle();
//
//        Log.i("lwc","trackerX = " + trackerX + " , trackerY = " + trackerY);

        GestureDetector detector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {

                Log.i("lwc","onDown");
                return true;//返回true,调用onScall(e),否则不调用
            }

            @Override
            public void onShowPress(MotionEvent e) {
                Log.i("lwc","onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.i("lwc","onSingleTapUp");//单击行为
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i("lwc","onScroll");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.i("lwc","onLongPress");

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("lwc","onFling");
                return true;
            }
        });
        return detector.onTouchEvent(event);
    }
}
