package com.haihong.testdemo.view.scroll;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by admin on 2016/3/9.
 */
public class MyScrollView extends View {
    private int mLastX;
    private int mLastY;

    private Scroller mScroller;

    public MyScrollView(Context context) {
        super(context);
        mScroller = new Scroller(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    /**
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;

                Log.i("lwc", "down  mLastX = " + mLastX + " , mLastY = " + mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
//                layoutScroll(y, x);
//                offsetScroll(x, y);
//                layoutParamsScroll(x, y);
//                animatorScroll(x, y);
                scroll(x, y);


                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        mLastX = x;
        mLastY = y;

        return super.onTouchEvent(event);
    }

    private void scroll(int x, int y) {
        //计算移动的距离
        int offsetX = x - mLastX;
        int offsetY = y - mLastY;

        ((View) getParent()).scrollBy(-offsetX, -offsetY);
    }

    /**
     * 效果不好
     *
     * @param x
     * @param y
     */
    private void animatorScroll(int x, int y) {
        //计算移动的距离
        int offsetX = x - mLastX;
        int offsetY = y - mLastY;

        ObjectAnimator.ofFloat(this, "translationX", getLeft() + offsetX).setDuration(1).start();
        ObjectAnimator.ofFloat(this, "translationY", getTop() + offsetX).setDuration(1).start();
    }

    private void layoutParamsScroll(int x, int y) {
        //计算移动的距离
        int offsetX = x - mLastX;
        int offsetY = y - mLastY;


        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        layoutParams.leftMargin = getLeft() + offsetX;
        layoutParams.topMargin = getTop() + offsetY;
        setLayoutParams(layoutParams);
    }

    private void offsetScroll(int x, int y) {
        int offsetX = x - mLastX;
        int offsetY = y - mLastY;

        offsetLeftAndRight(offsetX);
        offsetTopAndBottom(offsetY);
    }

    private void layoutScroll(int y, int x) {
        //计算移动的距离
        int offX = x - mLastX;
        int offY = y - mLastY;
        //调用layout方法来重新放置它的位置
        layout(getLeft() + offX, getTop() + offY,
                getRight() + offX, getBottom() + offY);
    }

    public void smoothScrollTo(int destX, int destY) {

        Log.i("lwc==", "smoothScrollTo");

        int scrollX = getScrollX();
        int scrollY = getScrollY();

        int deltaX = destX - scrollX;
        int deltaY = destY - scrollY;

        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 2000);
        invalidate();
    }

    @Override
    public void computeScroll() {

        Log.i("lwc==", "computeScroll");

        if (mScroller.computeScrollOffset()) {

            int scrollX = getScrollX();
            int scrollY = getScrollY();
            Log.i("lwc==", "scrollX =" + scrollX + ",scrollY = " + scrollY);
            Log.i("lwc==", "mScroller.getCurrX() = " + mScroller.getCurrX() + " , mScroller.getCurrY() = " + mScroller.getCurrY());

            ((View)getParent()).scrollTo(-mScroller.getCurrX(), -mScroller.getCurrY());//view边缘-内容边缘,内容
            postInvalidate();
        }
    }


}
