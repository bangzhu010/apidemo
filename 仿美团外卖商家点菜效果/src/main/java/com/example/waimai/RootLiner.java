package com.example.waimai;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.waimai.util.DisplayUtil;
import com.nineoldandroids.view.ViewHelper;

/**
 * 根布局用来获取父类的滚动距离
 *
 * @author Administrator
 *
 */
public class RootLiner extends RelativeLayout {
	// 带图片的整体view
	private View imgView;
	// 标题栏view
	private View tittleView;

	public void setTittleView(View tittleView) {
		this.tittleView = tittleView;
	}

	// 平均进度
	private float progressApha;
	private int screenHeight;
	private Context context;


	public RootLiner(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		WindowManager w=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		screenHeight=w.getDefaultDisplay().getHeight();
		// TODO Auto-generated constructor stub
		this.context=context;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		imgView = getChildAt(0);
		progressApha = 1.0f / (imgView.getMeasuredHeight() - tittleView
				.getMeasuredHeight());
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		/*int height=0;
		for(int i=0,count=getChildCount();i<count;i++){
			View viewChild=getChildAt(i);
			viewChild.measure(widthMeasureSpec, heightMeasureSpec);
			height+=viewChild.getMeasuredHeight();
		}
		Log.i("huoying", "height1:"+height);
		Log.i("huoying", "widthMeasureSpec:"+widthMeasureSpec);*/
		super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(screenHeight+DisplayUtil.dip2px(context, 100), MeasureSpec.AT_MOST));

		//setMeasuredDimension(widthMeasureSpec, height);
	}

	/**
	 * 获取图片层的高度
	 *
	 * @return
	 */
	public int getImgViewHeight() {
		if (imgView == null) {
			throw new RuntimeException("子view不能为空");
		}
		Log.i("huoying",
				"imgView.getMeasuredHeight():" + imgView.getMeasuredHeight());

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
		Log.i("huoying",
				"tittleView.getMeasuredHeight():" + tittleView.getHeight());
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
		Log.i("huoying", "disY" + disY);

		scrollBy(disX, disY);
		/**
		 * 修正移动误差
		 */
		if (getScrollY() <= 0) {
			scrollTo(0, 0);
		} else if (getScrollY() >= getImgViewHeight() - getTittleViewHeight()) {
			scrollTo(0, getImgViewHeight() - getTittleViewHeight());

		}
		// 改变透明度
		changeAlpha(getScrollY());

	}

	/**
	 *
	 * @param disY
	 */
	private void changeAlpha(int disY) {
		// TODO Auto-generated method stub
		Log.i("huoying", "progressApha:" + progressApha);
		if (disY > 0 && disY < getImgViewHeight() - getTittleViewHeight()) {
			float imgViewAlpha =1- (disY * progressApha);
			Log.i("huoying", "imgViewAlpha:" + imgViewAlpha);
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
		ViewHelper.setAlpha(imgView, imgViewAlpha);
		ViewHelper.setAlpha(tittleView, tittleViewAlpha);
	}
}
