package com.example.waimai.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waimai.Food;
import com.example.waimai.R;
import com.example.waimai.view.PinnedHeaderListView.PinnedHeaderAdapter;

import java.util.HashMap;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements OnScrollListener,
		PinnedHeaderAdapter {
	private HashMap<Integer, Integer> hash;
	private static final String TAG = CustomAdapter.class.getSimpleName();
	private ListView leftListView;
	public void setLeftListView(ListView leftListView) {
		this.leftListView = leftListView;
	}

	private Context mContext;
	private List<Food> mData;
	private LayoutInflater mLayoutInflater;

	public CustomAdapter(Context pContext, List<Food> pData) {
		mContext = pContext;
		mData = pData;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 常见的优化ViewHolder
		ViewHolder viewHolder = null;
		if (null == convertView) {
			convertView = mLayoutInflater.inflate(R.layout.right_baseadapter,
					null);

			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// 获取数据
		Food itemEntity = (Food) getItem(position);

		if (needTitle(position)) {
			viewHolder.title.setText(itemEntity.getTittle());
			viewHolder.title.setVisibility(View.VISIBLE);
		} else {
			// 内容项隐藏标题
			viewHolder.title.setVisibility(View.GONE);
		}

		return convertView;
	}

	@Override
	public int getCount() {
		if (null != mData) {
			return mData.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (null != mData && position < getCount()) {
			return mData.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		if (view instanceof PinnedHeaderListView) {
			((PinnedHeaderListView) view).controlPinnedHeader(firstVisibleItem);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public int getPinnedHeaderState(int position) {
		if (getCount() == 0 || position < 0) {
			return PinnedHeaderAdapter.PINNED_HEADER_GONE;
		}

		if (isMove(position) == true) {
			return PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP;
		}

		return PinnedHeaderAdapter.PINNED_HEADER_VISIBLE;
	}

	@Override
	public void configurePinnedHeader(View headerView, int position, int alpaha) {
		// 设置标题的内容
		Food itemEntity = (Food) getItem(position);
		String headerValue = itemEntity.getTittle();
		int type=itemEntity.getType();

		Log.e(TAG, "header = " + headerValue);

		if (!TextUtils.isEmpty(headerValue)) {
			TextView headerTextView = (TextView) headerView
					.findViewById(R.id.header);
			headerTextView.setText(headerValue);
			if(hash!=null){
				if(hash.containsValue(type)){
					changeLeftBackGroud(type);
				}
			}

		}

	}
	private void changeLeftBackGroud(int position) {
		if(leftListView==null){
			return;
		}
		int count = leftListView.getChildCount();
		Log.i("huoying", "currentposition:"+position);

		for (int i = 0; i < count; i++) {
			//向下滚动

			if ( i ==position-1) {

				leftListView.getChildAt(i).setBackgroundColor(
						Color.parseColor("#dcdcdc"));
				Log.i("huoying", "success");
			} else {
				leftListView.getChildAt(i).setBackgroundColor(Color.WHITE);
			}
		}
	}
	/**
	 * 判断是否需要显示标题
	 *
	 * @param position
	 * @return
	 */
	private boolean needTitle(int position) {
		// 第一个肯定是分类
		if (position == 0) {
			return true;
		}

		// 异常处理
		if (position < 0) {
			return false;
		}

		// 当前 // 上一个
		Food currentEntity = (Food) getItem(position);
		Food previousEntity = (Food) getItem(position - 1);
		if (null == currentEntity || null == previousEntity) {
			return false;
		}

		int currentTitle = currentEntity.getType();
		int previousTitle = previousEntity.getType();
		if (0 == previousTitle || 0 == currentTitle) {
			return false;
		}

		// 当前item分类名和上一个item分类名不同，则表示两item属于不同分类
		if (currentTitle == previousTitle) {
			return false;
		}

		return true;
	}

	private boolean isMove(int position) {
		// 获取当前与下一项
		Food currentEntity = (Food) getItem(position);
		Food nextEntity = (Food) getItem(position + 1);
		if (null == currentEntity || null == nextEntity) {
			return false;
		}

		// 获取两项header内容
		int currentTitle = currentEntity.getType();
		int nextTitle = nextEntity.getType();
		if (0 == currentTitle || 0 == nextTitle) {
			return false;
		}

		// 当前不等于下一项header，当前项需要移动了
		if (currentTitle != nextTitle) {
			return true;
		}

		return false;
	}

	private class ViewHolder {
		TextView title;
	}
	public void setLeftListView(ListView lv_Left, HashMap<Integer, Integer> hash) {
		this.leftListView = lv_Left;
		this.hash = hash;
	}
}
