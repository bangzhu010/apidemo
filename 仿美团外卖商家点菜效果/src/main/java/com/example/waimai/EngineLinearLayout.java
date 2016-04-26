package com.example.waimai;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * viewpager的引擎
 *
 * @author zp
 *
 */
public class EngineLinearLayout extends LinearLayout {
	private ViewPager pager;
	// 画笔画滚动条
	private Paint paint;
	// 子view的宽度
	private int childWidth;
	// 选中的颜色
	private static final int SELECTED_COLOR = Color.RED;
	// 缺省的color
	private static final int DEFAULT_COLOR = Color.BLACK;
	// 子view高度
	private int childHeight;
	// 当前viewpager的索引
	private int postition;

	public EngineLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		// 这里偷懒了没用onlayout重新为子view布局，因为布局文件用了比重平分了屏幕
		View view = getChildAt(0);
		childWidth = view.getMeasuredWidth();
		childHeight = view.getMeasuredHeight();
	}

	/**
	 * 初始化
	 */
	private void init() {
		// TODO Auto-generated method stub
		setOrientation(HORIZONTAL);
		paint = new Paint();
		// 开启抗锯齿
		paint.setAntiAlias(true);
		// 画红色
		paint.setColor(SELECTED_COLOR);
		// 采用填充
		paint.setStyle(Style.STROKE);
		//设置线条宽度
		paint.setStrokeWidth(15);
	}

	/**
	 * 重画子view
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.dispatchDraw(canvas);
		canvas.drawLine(postition * childWidth, getMeasuredHeight(), postition
				* childWidth + childWidth,  getMeasuredHeight(), paint);

	}

	public void setPager(ViewPager pager) {
		this.pager = pager;
		// 监听viewpager的滑动
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				postition = arg0;
				// 刷新红色划线
				invalidate();
				// 改变字体颜色
				changeChildColor(arg0);
			}

			/**
			 * 在手指滚动时也想滚动条滚动在这写
			 */
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 改变字体颜色
	 *
	 * @param arg0
	 */
	private void changeChildColor(int arg0) {
		// TODO Auto-generated method stub
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View view = getChildAt(i);
			if (view instanceof TextView) {
				if (arg0 == i) {
					((TextView) view).setTextColor(SELECTED_COLOR);
				} else {
					((TextView) view).setTextColor(DEFAULT_COLOR);
				}
			} else {
				break;
			}
		}

	}
}
