package com.example.waimai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.example.waimai.util.DisplayUtil;
import com.nineoldandroids.view.ViewHelper;

public class RootScroller extends ScrollView {
	private int screenHeight;
	// 带图片的整体view
	private View imgView;
	// 标题栏view
	private View tittleView;

	public void setTittleView(View tittleView) {
		this.tittleView = tittleView;
	}

	private boolean isOne;

	// 平均进度
	private float progressApha;

	public RootScroller(final Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager w = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		screenHeight = w.getDefaultDisplay().getHeight();
		// TODO Auto-generated constructor stub
		// 重新定义viewpager的高度,因为scrollview嵌套viewpager测量的viewpager高度为0
		getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// TODO Auto-generated method stub
						if (!isOne) {
							ViewGroup v = (ViewGroup) getChildAt(0);
							View v1 = v.getChildAt(2);
							android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) v1
									.getLayoutParams();
							params.height = screenHeight
									- v.getChildAt(0).getHeight()
									- v.getChildAt(1).getHeight()
									+ DisplayUtil.dip2px(context, 40);
							v1.setLayoutParams(params);
							// 初始位置确定
							isOne = true;
						}

					}
				});
	}

	/*
	 * @Override protected void onLayout(boolean changed, int l, int t, int r,
	 * int b) { // TODO Auto-generated method stub super.onLayout(changed, l, t,
	 * r, b); //添加viewPager高度时重新布局会让滚动触发所以让她回位0 scrollTo(0, 0);
	 * 
	 * }
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		imgView = ((ViewGroup) getChildAt(0)).getChildAt(0);
		progressApha = 1.0f / (imgView.getMeasuredHeight() - tittleView
				.getMeasuredHeight());

	}

	/*@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		*//**
	 * 滑动时会重新加载这个方法故加判断方式不应该的滚动
	 *//*
		if (getScrollY() > 0 && ViewHelper.getAlpha(tittleView) <= 0) {
			scrollTo(0, 0);
		}
	}*/

	/**
	 * 获取图片层的高度
	 *
	 * @return
	 */
	public int getImgViewHeight() {
		if (imgView == null) {
			throw new RuntimeException("子view不能为空");
		}
		return imgView.getMeasuredHeight();
	}

	/**
	 * 标题栏高度
	 *
	 * @return
	 */
	public int getTittleViewHeight() {
		if (tittleView == null) {
			throw new RuntimeException("子view不能为空");
		}
		return tittleView.getMeasuredHeight();
	}

	/**
	 * 判断父布局是否滑动到头了
	 *
	 * @return
	 */
	public boolean isInTop() {
		if (getScrollY() >= getImgViewHeight() - getTittleViewHeight()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断父布局是否滑动到底了
	 *
	 * @return
	 */
	public boolean isInBottom() {
		if (getScrollY() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 滑动父view
	 *
	 * @param disX
	 * @param disY
	 *            向上滚为正数
	 */
	public void scrollByFather(int disX, int disY) {
		disY=getScrollY()+disY>=getImgViewHeight() - getTittleViewHeight()?getImgViewHeight() - getTittleViewHeight()-getScrollY():disY;
		disY=getScrollY()+disY<=0?0-getScrollY():disY;
		smoothScrollBy(disX, disY);
		// 改变透明度
		changeAlpha(getScrollY());

	}

	/**
	 *
	 * @param disY
	 */
	private void changeAlpha(int disY) {
		// TODO Auto-generated method stub
		if (disY > 0 && disY < getImgViewHeight() - getTittleViewHeight()) {
			float imgViewAlpha = 1 - (disY * progressApha);
			setAlpha(imgViewAlpha, 1 - imgViewAlpha);
		} else if (disY == 0) {
			setAlpha(1, 0);
		} else {
			setAlpha(0, 1);
		}
	}

	/**
	 * 改变view的透明度
	 */
	private void setAlpha(float imgViewAlpha, float tittleViewAlpha) {
		// ViewHelper.setAlpha(imgView, imgViewAlpha);
		ViewHelper.setAlpha(tittleView, tittleViewAlpha);
	}

	/**
	 * 销毁掉scrollview的滚动事件要不事件无法发到底部
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub

		return false;
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		changeAlpha(t);
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
