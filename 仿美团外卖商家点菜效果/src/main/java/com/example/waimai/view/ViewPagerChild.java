package com.example.waimai.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class ViewPagerChild extends ViewPager {
	private GestureDetector mGesture = null;

	public ViewPagerChild(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mGesture =new GestureDetector(context,new GestureListener());
	}


	class GestureListener extends SimpleOnGestureListener
	{

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
							   float velocityY)
		{
			// TODO Auto-generated method stub
			return super.onFling(e1, e2, velocityX, velocityY);
		}



		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
								float distanceX, float distanceY)
		{
			// TODO Auto-generated method stub
			return (Math.abs(distanceY) > Math.abs(distanceX));
		}


	}
}
/**
 * 由于滚动事件被viewPager拦截所以必须重写
 */
/*@Override
public boolean onInterceptTouchEvent(MotionEvent event) {
	// TODO Auto-generated method stub
	
	return super.onInterceptTouchEvent(event) && mGesture.onTouchEvent(event);}
}*/
