package com.example.waimai.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;

/**
 * 支持ListView置顶功能
 */
public class PinnedHeaderListView extends ChildLisView {
	private View mHeaderView;
	private int mMeasuredWidth;
	private int mMeasuredHeight;
	private boolean mDrawFlag = true;
	public void setmDrawFlag(boolean mDrawFlag) {
		this.mDrawFlag = mDrawFlag;
	}

	private boolean isVisiBle = true;
	private PinnedHeaderAdapter mPinnedHeaderAdapter;
	// 判断是变大的还是变小的
	public PinnedHeaderListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOverScrollMode(OVER_SCROLL_NEVER);
	}

	/**
	 * 设置置顶的Header View
	 *
	 * @param pHeader
	 */
	public void setPinnedHeader(View pHeader) {
		mHeaderView = pHeader;

		requestLayout();
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);

		mPinnedHeaderAdapter = (PinnedHeaderAdapter) adapter;
	}

	// 三个覆写方法负责在当前窗口显示inflate创建的Header View

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		if (null != mHeaderView) {
			measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
			mMeasuredWidth = mHeaderView.getMeasuredWidth();
			mMeasuredHeight = mHeaderView.getMeasuredHeight();
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (null != mHeaderView) {
			mHeaderView.layout(0, 0, mMeasuredWidth, mMeasuredHeight);
			controlPinnedHeader(getFirstVisiblePosition());
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);

		if (null != mHeaderView && mDrawFlag) {
			drawChild(canvas, mHeaderView, getDrawingTime());
		}
	}

	/**
	 * HeaderView三种状态的具体处理
	 *
	 * @param position
	 */
	public void controlPinnedHeader(int position) {
		if (null == mHeaderView) {
			return;
		}

		int pinnedHeaderState = mPinnedHeaderAdapter
				.getPinnedHeaderState(position);
		switch (pinnedHeaderState) {
			case PinnedHeaderAdapter.PINNED_HEADER_GONE:
				mDrawFlag = false;
				break;

			case PinnedHeaderAdapter.PINNED_HEADER_VISIBLE:
				mPinnedHeaderAdapter
						.configurePinnedHeader(mHeaderView, position, 0);
				if (isVisiBle) {
					mDrawFlag = true;

				} else {
					mDrawFlag = false;

				}
				mHeaderView.layout(0, 0, mMeasuredWidth, mMeasuredHeight);
				break;

			case PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP:
				mPinnedHeaderAdapter
						.configurePinnedHeader(mHeaderView, position, 0);
				if (isVisiBle) {
					mDrawFlag = true;

				} else {
					mDrawFlag = false;

				}

				// 移动位置
				View topItem = getChildAt(0);

				if (null != topItem) {
					int bottom = topItem.getBottom();
					int height = mHeaderView.getHeight();

					int y;
					if (bottom < height) {
						y = bottom - height;
					} else {
						y = 0;
						// 级联左边的listView
					}

					if (mHeaderView.getTop() != y) {
						mHeaderView.layout(0, y, mMeasuredWidth, mMeasuredHeight
								+ y);
					}

				}
				break;
		}

	}



	public interface PinnedHeaderAdapter {

		public static final int PINNED_HEADER_GONE = 0;

		public static final int PINNED_HEADER_VISIBLE = 1;

		public static final int PINNED_HEADER_PUSHED_UP = 2;

		int getPinnedHeaderState(int position);

		void configurePinnedHeader(View headerView, int position, int alpaha);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		/**
		 * 滑到顶部时画笔不显示了
		 */
		if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() > 0) {
			isVisiBle = false;
		} else {
			isVisiBle = true;

		}
		return super.onTouchEvent(event);
	}


}
