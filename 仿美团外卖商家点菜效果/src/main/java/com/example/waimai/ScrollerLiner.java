package com.example.waimai;

import java.util.HashMap;

import com.example.waimai.view.ChildLisView;
import com.example.waimai.view.PinnedHeaderListView;
import com.example.waimai.view.RootScroller;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * ������LinearLayout
 * 
 * @author Administrator
 * 
 */
public class ScrollerLiner extends LinearLayout {
	// �Ӳ��ֵ�viewPager
	//private ViewPager childPager;
	// ����listView
	private HashMap<Integer, ChildLisView> currentListView;
	// ��һҳ��listView
	private ChildLisView listView;
	private float  firstY, distanceY;
	private ViewConfiguration viewConfiguration;
	private RootScroller header;
	// ���ϻ������»�
	//private boolean isFirst;

	// ����ͷ����������ͷ������
	public void setHeader(RootScroller rootliner) {
		this.header = rootliner;

	}

	public ScrollerLiner(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		viewConfiguration = ViewConfiguration.get(context);
		currentListView = new HashMap<Integer, ChildLisView>();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);

	}

	/*@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (!isFirst) {
			//childPager = (ViewPager) getChildAt(0);
			//putListView();
			isFirst = true;
		}
	}*/

	/**
	 * ��listView���뻺��
	 *//*
	private void putListView() {
		View view = GetScrollView(0, 0);
		if (view != null) {
			if (view instanceof ChildLisView) {
				ChildLisView leftListView = (ChildLisView) view;
				// leftListView.setHeader(header);
				currentListView.put(0, leftListView);

			}
		}
		view = GetScrollView(1, 0);
		if (view != null) {
			if (view instanceof ChildLisView) {
				ChildLisView rightListView = (ChildLisView) view;
				// rightListView.setHeader(header);
				currentListView.put(1, rightListView);

			}
		}
	}
*/
	/**
	 * �ж���frament�Ƿ����������Դﵽ����Ч��
	 * 
	 * @return
	 */
	private boolean isFramentTop(int currentItem) {
		if (currentItem == 0) {
			if (listView != null) {
				if (listView.getFirstVisiblePosition() == 0
						&& listView.getChildAt(0).getTop() >= 0) {
					return true;
				}

			}
		} else if (currentItem == 1) {
			return true;

		} else if (currentItem == 2) {
			return true;

		}
		return false;

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			firstY = event.getRawY();
			//initListView(event);
			break;
		case MotionEvent.ACTION_MOVE:
			distanceY = event.getRawY() - firstY;
			Log.i("huoying", "distanceY:"+distanceY);
			firstY = event.getRawY();
			if (Math.abs(distanceY) > 2) {
				// ���»���ʱ
				if (distanceY > 0) {
					// ���ڶ����������¼�

					if (!header.isInBottom()
							) {
						header.scrollByFather(0, (int) -distanceY);
						return true;
					}

				}
				// ���»���ʱ
				else {
					// ���ڵײ������¼�
					if (!header.isInTop()
							) {
						header.scrollByFather(0, (int) -distanceY);
						return true;

					}

				}
			}
			break;

		case MotionEvent.ACTION_UP:

		}
		return super.dispatchTouchEvent(event);
	}

	/**
	 * ��ȫ�����¼���������Ҫʱ�ַ�
	 */
	/*
	 * @Override public boolean onInterceptTouchEvent(MotionEvent event) { //
	 * TODO Auto-generated method stub switch (event.getAction()) { case
	 * MotionEvent.ACTION_DOWN: firstX = event.getX(); firstY = event.getY();
	 * firstY1 = event.getY(); initListView(event); break; case
	 * MotionEvent.ACTION_MOVE: distanceX = event.getX() - firstX; distanceY =
	 * event.getY() - firstY;
	 * 
	 * // �ж��Ƿ��Ǵ�ֱ���� if (Math.abs(distanceY) >
	 * viewConfiguration.getScaledTouchSlop() && Math.abs(distanceY) >
	 * Math.abs(distanceX)) { // ���»���ʱ if (distanceY > 0) { // ���ڶ����������¼�
	 * 
	 * if (!header.isInBottom() && isFramentTop(childPager.getCurrentItem())) {
	 * return true; }
	 * 
	 * } // ���»���ʱ else { // ���ڵײ������¼� if (!header.isInTop()) { return true;
	 * 
	 * } }
	 * 
	 * } else if (Math.abs(distanceX) > viewConfiguration .getScaledTouchSlop()
	 * && Math.abs(distanceX) > Math.abs(distanceY)) { // ˮƽ�����¼�����viewpager
	 * return super.onInterceptTouchEvent(event);
	 * 
	 * } break;
	 * 
	 * case MotionEvent.ACTION_UP: break; } return
	 * super.onInterceptTouchEvent(event); }
	 */

/*	private void initListView(MotionEvent event) {
		if (childPager.getCurrentItem() == 0) {
			ChildLisView leftListView = currentListView.get(0);
			if (event.getX() <= leftListView.getMeasuredWidth()) {
				listView = leftListView;
			} else {
				ChildLisView rightListView = currentListView.get(1);
				listView = rightListView;

			}
		}
	}*/

	/*
	 * @Override public boolean onTouchEvent(MotionEvent event) { // TODO
	 * Auto-generated method stub switch (event.getAction()) { case
	 * MotionEvent.ACTION_DOWN: firstX = event.getX(); firstY = event.getY();
	 * 
	 * break; case MotionEvent.ACTION_MOVE: distanceY = event.getY() - firstY;
	 * 
	 * if (distanceY > 0) { if (!header.isInBottom()) { header.scrollByFather(0,
	 * (int) -distanceY); } else { // �ﵽ����Ч�� linkageEffectChildView(event,
	 * distanceY); return true;
	 * 
	 * } } else { if (!header.isInTop()) { header.scrollByFather(0, (int)
	 * -distanceY); } else { // �ﵽ����Ч�� linkageEffectChildView(event, distanceY);
	 * return true;
	 * 
	 * } } // ������view
	 * 
	 * break;
	 * 
	 * } return true; }
	 */
	/**
	 * �жϵ�ǰ���ĸ�����
	 * 
	 * @param distanceY12
	 */
	/*private void linkageEffectChildView(MotionEvent event, float distanceY12) {
		int currentItem = childPager.getCurrentItem();
		// TODO Auto-generated method stub
		if (currentItem == 0) {
			ChildLisView leftListView = currentListView.get(0);
			
			 * if (leftListView == null) { View view = GetScrollView(0, 0); if
			 * (view != null) { if (view instanceof ChildLisView) { leftListView
			 * = (ChildLisView) view; currentListView.put(0, leftListView); }
			 * else { view = GetScrollView(0, 1); if (view != null) { if (view
			 * instanceof ChildLisView) { leftListView = (ChildLisView) view;
			 * currentListView.put(0, leftListView);
			 * 
			 * } } } } }
			 
			// ��������ߵ�listview
			if (event.getX() <= leftListView.getMeasuredWidth()) {
				leftListView.smoothScrollBy((int) -distanceY12, 0);
				listView = leftListView;

			} else {
				ChildLisView rightListView = currentListView.get(1);
				
				 * if (rightListView == null) { View view = GetScrollView(1, 0);
				 * if (view != null) { if (view instanceof ChildLisView) {
				 * rightListView = (ChildLisView) view; currentListView.put(1,
				 * rightListView);
				 * 
				 * } else { view = GetScrollView(1, 1); if (view != null) { if
				 * (view instanceof ChildLisView) { rightListView =
				 * (ChildLisView) view; currentListView.put(1, rightListView);
				 * 
				 * } } } }
				 
				if (rightListView.getFirstVisiblePosition() == 0
						&& rightListView.getChildAt(0).getTop() >= 0) {
					PinnedHeaderListView listView1 = (PinnedHeaderListView) rightListView;
					listView1.setmDrawFlag(false);
					// listView1.requestLayout();
				} else {
					PinnedHeaderListView listView1 = (PinnedHeaderListView) rightListView;
					listView1.setmDrawFlag(true);
					// listView1.requestLayout();

				}
				rightListView.smoothScrollBy((int) -distanceY12, 0);
				listView = rightListView;

			} 
			 * else { rightListView.smoothScrollBy((int) -distanceY12, 0);
			 * listView = rightListView; // ����ķ���ֻ�ǰ�����listView�ƶ��˲���ȡ //
			 * rightListView.scrollBy(0, (int) -distanceY12);
			 * 
			 * } }
			 

		} else if (currentItem == 1) {

		} else {

		}

	}
*/
	/**
	 * �õ��ӹ�����View
	 * 
	 * @param index
	 * @param i
	 * @return
	 */
	/*private View GetScrollView(int index, int i) {
		View view0 = childPager.getChildAt(i);
		if (view0 == null) {
			return null;
		}
		ViewGroup v = (ViewGroup) view0;
		ViewGroup v1 = (ViewGroup) v.getChildAt(0);
		if (index < v1.getChildCount()) {
			return v1.getChildAt(index);
		}
		return null;
	}*/

	/*
	 * private void setHeadler(int index) { ViewGroup v = (ViewGroup)
	 * childPager.getChildAt(0); ViewGroup v1 = (ViewGroup) v.getChildAt(0); if
	 * (index < v1.getChildCount()) { View v2 = v1.getChildAt(index); if (v2
	 * instanceof ChildLisView) { ((ChildLisView) v2).setHeader(header); } } }
	 */
}
