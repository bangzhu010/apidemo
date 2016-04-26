package com.example.waimai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

public class ChildLisView extends ListView {
	/*protected RootScroller header;
	private float firstY;
	private float distanceY;
	public void setHeader(RootScroller header) {
		this.header = header;
	}*/

	public ChildLisView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		/*if(header!=null){
		if(getFirstVisiblePosition()==0&&getChildAt(0).getTop()>=0){
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				firstY = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				distanceY = event.getY() - firstY;

				if (distanceY > 0) {
						if (!header.isInBottom()&&isFramentTop()) {
							header.scrollByFather(0, (int) -distanceY);
							return true;
						}

				} else {
					if (!header.isInTop()&&isFramentTop()) {
						header.scrollByFather(0, (int) -distanceY);
						return true;
					}
				
				} 
					
				
				break;

			}
		}
		}*/
		return super.onTouchEvent(event);
	}
	
}
