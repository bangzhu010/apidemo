package com.example.waimai;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class TextChange extends TextView {
	private List<String> list;
	// private String contentText;
	private Handler handler;
	private boolean isOne;

	public TextChange(Context context, AttributeSet attrs) {
		super(context, attrs);
		list = new ArrayList<String>();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).equals(getText() + "")) {
						if (i < list.size() - 1) {
							setText(list.get(i + 1));
							break;
						} else {
							setText(list.get(0));
							break;

						}
					}
				}
				handler.sendEmptyMessageDelayed(0, 3000);
			}
		};
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		if (!isOne) {
			/*
			 * resize(); isOne=true;
			 */
			// contentText = getText() + "";
			/* setTheContent(); */
			segmentationTxt();
			isOne = true;
		}

	}

	/**
	 * 设置内容
	 */
	/*
	 * private void setTheContent() {
	 * 
	 * String content = null; content = subStringContent(contentText); if
	 * (list.size() > 0) { setText(list.get(0)); if (list.size() > 1) {
	 * handler.sendEmptyMessageDelayed(0, 3000); } } }
	 */

	/**
	 * 截取content
	 * 
	 * @param h1
	 * @param content
	 * @return
	 */
	/*
	 * private String subStringContent(String content) { String h = null; float
	 * width = getPaint().measureText(content); Log.i("huoying",
	 * " content.length:" + content.length());
	 * 
	 * if (width > getMeasuredWidth()) { char[] c = content.toCharArray(); float
	 * h1 = 0; for (int i = 0; i < c.length; i++) { String c1 =
	 * String.valueOf(c[i]); h1 += getPaint().measureText(c1); if (h1 >=
	 * getMeasuredWidth()) { h = content.substring(0, i-1); list.add(h);
	 * subStringContent(content.substring( i, content.length() - 1));
	 * Log.i("huoying", "hahah"); break; } } } else { list.add(content); }
	 * return h; }
	 */
	/**
	 * 分割过长的字符串
	 */
	private void segmentationTxt() {
		// 没有文字或视图不显示，没有必要加特效了

		if (getVisibility() == GONE || getText() == null
				|| "".equals(getText().toString())) {
			return;
		}
		String txt = getText().toString();
		// 字符总长度,因为总共就一行
		int characterTotalLength = getLayout().getLineEnd(0) + 1;
		// 可见字符总长度
		int characterVisibleLength = getLayout().getEllipsisStart(0)+1;
		Log.i("huoying", "getLayout().getEllipsisStart(0):"
				+ getLayout().getEllipsisStart(0));
		Log.i("huoying", "lineShijiCount:" + characterTotalLength);

		int linehang = characterTotalLength % characterVisibleLength == 0 ? characterTotalLength
				/ characterVisibleLength
				: characterTotalLength / characterVisibleLength + 1;
		for (int i = 0; i < linehang; i++) {
			if (i * characterVisibleLength + characterVisibleLength > characterTotalLength - 1) {
				list.add(txt.substring(i * characterVisibleLength,
						characterTotalLength - 1));
			} else {
				list.add(txt.substring(i * characterVisibleLength, i
						* characterVisibleLength + characterVisibleLength));
			}
		}
		if (list.size() > 0) {
			setText(list.get(0));
			if (list.size() > 1) {
				handler.sendEmptyMessageDelayed(0, 2500);
			}
		}
	}

}
